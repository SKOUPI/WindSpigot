package org.bukkit.craftbukkit.entity;

import org.apache.commons.lang3.Validate;import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.LivingEntity;
import org.bukkit.projectiles.ProjectileSource;

import net.minecraft.server.BlockPosition;
import net.minecraft.server.EntityFishingHook;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.MathHelper;

public class CraftFish extends AbstractProjectile implements Fish {
	private double biteChance = -1;

	public CraftFish(CraftServer server, EntityFishingHook entity) {
		super(server, entity);
	}

	@Override
	public ProjectileSource getShooter() {
		if (getHandle().owner != null) {
			return getHandle().owner.getBukkitEntity();
		}

		return null;
	}

	@Override
	public void setShooter(ProjectileSource shooter) {
		if (shooter instanceof CraftHumanEntity) {
			getHandle().owner = (EntityHuman) ((CraftHumanEntity) shooter).entity;
		}
	}

	@Override
	public EntityFishingHook getHandle() {
		return (EntityFishingHook) entity;
	}

	@Override
	public String toString() {
		return "CraftFish";
	}

	@Override
	public EntityType getType() {
		return EntityType.FISHING_HOOK;
	}

	@Override
	public double getBiteChance() {
		EntityFishingHook hook = getHandle();

		if (this.biteChance == -1) {
			if (hook.world.isRainingAt(new BlockPosition(MathHelper.floor(hook.locX), MathHelper.floor(hook.locY) + 1,
					MathHelper.floor(hook.locZ)))) {
				return 1 / 300.0;
			}
			return 1 / 500.0;
		}
		return this.biteChance;
	}

	@Override
	public void setBiteChance(double chance) {
		Validate.isTrue(chance >= 0 && chance <= 1, "The bite chance must be between 0 and 1.");
		this.biteChance = chance;
	}

	@Override
	@Deprecated
	public LivingEntity _INVALID_getShooter() {
		return (LivingEntity) getShooter();
	}

	@Override
	@Deprecated
	public void _INVALID_setShooter(LivingEntity shooter) {
		setShooter(shooter);
	}
}
