package net.minecraft.server;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Spigot start
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
// Spigot end
import com.mojang.authlib.properties.Property;

public class TileEntitySkull extends TileEntity {

	private int a;
	private int rotation;
	private GameProfile g = null;
	// Spigot start
	public static final Executor executor = Executors.newFixedThreadPool(6, new ThreadFactoryBuilder().setNameFormat("Head Conversion Thread - %1$d").build());
	public static final LoadingCache<String, GameProfile> skinCache = CacheBuilder.newBuilder().maximumSize(5000).expireAfterAccess(60, TimeUnit.MINUTES).build(new CacheLoader<String, GameProfile>() {
		@Override
		public GameProfile load (String key)
		{
			final GameProfile[] profiles = new GameProfile[1];
			ProfileLookupCallback gameProfileLookup = new ProfileLookupCallback() {

				@Override
				public void onProfileLookupSucceeded (GameProfile gp)
				{
					profiles[0] = gp;
				}

				@Override
				public void onProfileLookupFailed (GameProfile gp, Exception excptn)
				{
					profiles[0] = gp;
				}
			};

			MinecraftServer.getServer().getGameProfileRepository().findProfilesByNames(new String[]{key}, Agent.MINECRAFT, gameProfileLookup);

			GameProfile profile = profiles[0];
			if (profile == null)
			{
				UUID uuid = EntityHuman.a(new GameProfile(null, key));
				profile = new GameProfile(uuid, key);

				gameProfileLookup.onProfileLookupSucceeded(profile);
			} else
			{

				Property property = Iterables.getFirst(profile.getProperties().get("textures"), null);

				if (property == null)
				{
					profile = MinecraftServer.getServer().aD().fillProfileProperties(profile, true);
				}
			}

			return profile;
		}
	});

	// Spigot end

	public TileEntitySkull ()
	{
	}

	@Override
	public void b (NBTTagCompound nbttagcompound)
	{
		super.b(nbttagcompound);
		nbttagcompound.setByte("SkullType", (byte) (this.a & 255));
		nbttagcompound.setByte("Rot", (byte) (this.rotation & 255));
		if (this.g != null)
		{
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();

			GameProfileSerializer.serialize(nbttagcompound1, this.g);
			nbttagcompound.set("Owner", nbttagcompound1);
		}

	}

	@Override
	public void a (NBTTagCompound nbttagcompound)
	{
		super.a(nbttagcompound);
		this.a = nbttagcompound.getByte("SkullType");
		this.rotation = nbttagcompound.getByte("Rot");
		if (this.a == 3)
		{
			if (nbttagcompound.hasKeyOfType("Owner", 10))
			{
				this.g = GameProfileSerializer.deserialize(nbttagcompound.getCompound("Owner"));
			} else if (nbttagcompound.hasKeyOfType("ExtraType", 8))
			{
				String s = nbttagcompound.getString("ExtraType");

				if (!UtilColor.b(s))
				{
					this.g = new GameProfile(null, s);
					this.e();
				}
			}
		}

	}

	public GameProfile getGameProfile ()
	{
		return this.g;
	}

	@Override
	public Packet getUpdatePacket ()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();

		this.b(nbttagcompound);
		return new PacketPlayOutTileEntityData(this.position, 4, nbttagcompound);
	}

	public void setSkullType (int i)
	{
		this.a = i;
		this.g = null;
	}

	public void setGameProfile (GameProfile gameprofile)
	{
		this.a = 3;
		this.g = gameprofile;
		this.e();
	}

	private void e ()
	{
		// Spigot start
		GameProfile profile = this.g;
		setSkullType(0); // Work around client bug
		b(profile, input -> {
			setSkullType(3); // Work around client bug
			g = input;
			update();
			if (world != null)
			{
				world.notify(position);
			}
			return false;
		});
		// Spigot end
	}

	// Spigot start - Support async lookups
	public static void b (final GameProfile gameprofile, final Predicate<GameProfile> callback)
	{
		if (gameprofile != null && !UtilColor.b(gameprofile.getName()))
		{
			if (gameprofile.isComplete() && gameprofile.getProperties().containsKey("textures"))
			{
				callback.apply(gameprofile);
			} else if (MinecraftServer.getServer() == null)
			{
				callback.apply(gameprofile);
			} else
			{
				GameProfile profile = skinCache.getIfPresent(gameprofile.getName());
				if (profile != null && Iterables.getFirst(profile.getProperties().get("textures"), (Object) null) != null)
				{
					callback.apply(profile);
				} else
				{
					executor.execute(() -> {
						try
						{
							final GameProfile profile1 = skinCache.get(gameprofile.getName().toLowerCase());
							MinecraftServer.getServer().processQueue.add(() -> {
								if (profile1 == null)
								{
									callback.apply(gameprofile);
								} else
								{
									callback.apply(profile1);
								}
							});
						} catch (ExecutionException ex)
						{
							ex.printStackTrace();
						}
					});
				}
			}
		} else
		{
			callback.apply(gameprofile);
		}
	}
	// Spigot end

	public int getSkullType ()
	{
		return this.a;
	}

	public void setRotation (int i)
	{
		this.rotation = i;
	}

	// CraftBukkit start - add method
	public int getRotation ()
	{
		return this.rotation;
	}
	// CraftBukkit end
}
