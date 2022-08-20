package ga.windpvp.windspigot;

import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import ga.windpvp.windspigot.async.AsyncUtil;
import ga.windpvp.windspigot.async.pathsearch.SearchHandler;
import ga.windpvp.windspigot.async.thread.CombatThread;
import ga.windpvp.windspigot.commands.KnockbackCommand;
import ga.windpvp.windspigot.commands.MobAICommand;
import ga.windpvp.windspigot.commands.PingCommand;
import ga.windpvp.windspigot.commands.SetMaxSlotCommand;
import ga.windpvp.windspigot.commands.SpawnMobCommand;
import ga.windpvp.windspigot.config.WindSpigotConfig;
import ga.windpvp.windspigot.hitdetection.LagCompensator;
import ga.windpvp.windspigot.protocol.MovementListener;
import ga.windpvp.windspigot.protocol.PacketListener;
import net.minecraft.server.MinecraftServer;
import xyz.sculas.nacho.anticrash.AntiCrash;
import xyz.sculas.nacho.async.AsyncExplosions;

public class WindSpigot {


	public static final Logger LOGGER = LogManager.getLogger();
	private static final Logger DEBUG_LOGGER = LogManager.getLogger();
	private static WindSpigot INSTANCE;
	
	private CombatThread knockbackThread;

	private volatile boolean statisticsEnabled = false;
	
	private LagCompensator lagCompensator;
	
	private final Set<PacketListener> packetListeners = Sets.newConcurrentHashSet();
	private final Set<MovementListener> movementListeners = Sets.newConcurrentHashSet();

	public WindSpigot() {
		INSTANCE = this;
		this.init();
	}

	public void reload() {
		this.init();
	}

	private void initCmds() {
		
		SimpleCommandMap commandMap = MinecraftServer.getServer().server.getCommandMap();
		
		if (WindSpigotConfig.mobAiCmd) {
			MobAICommand mobAiCommand = new MobAICommand("mobai");
			commandMap.register(mobAiCommand.getName(), "", mobAiCommand);
		}
		
		if (WindSpigotConfig.pingCmd) {
			PingCommand pingCommand = new PingCommand("ping");
			commandMap.register(pingCommand.getName(), "", pingCommand);
		}
	
		
		
		// NachoSpigot commands
		// TODO: add configuration for all of these
		SetMaxSlotCommand setMaxSlotCommand = new SetMaxSlotCommand("sms"); // [Nacho-0021] Add setMaxPlayers within Bukkit.getServer() and SetMaxSlot Command
		commandMap.register(setMaxSlotCommand.getName(), "ns", setMaxSlotCommand);

		SpawnMobCommand spawnMobCommand = new SpawnMobCommand("spawnmob");
		commandMap.register(spawnMobCommand.getName(), "ns", spawnMobCommand);

		KnockbackCommand knockbackCommand = new KnockbackCommand("kb");
		commandMap.register(knockbackCommand.getName(), "ns", knockbackCommand);
	}


	private void init() {
		initCmds();

		// We do not want to initialize this again after a reload
		if (WindSpigotConfig.asyncPathSearches && SearchHandler.getInstance() == null) {
			new SearchHandler();
		}
		
		if (WindSpigotConfig.asyncKnockback) {
			knockbackThread = new CombatThread("Knockback Thread");
		}
		lagCompensator = new LagCompensator();	
		if (WindSpigotConfig.asyncTnt) {
			AsyncExplosions.initExecutor(WindSpigotConfig.fixedPoolSize);
		}
		if (WindSpigotConfig.enableAntiCrash) {
			registerPacketListener(new AntiCrash());
		}
	}

	public CombatThread getKnockbackThread() {
		return knockbackThread;
	}
	
    public LagCompensator getLagCompensator() {
        return lagCompensator;
    }
    
	public static void debug(String msg) {
		if (WindSpigotConfig.debugMode)
			DEBUG_LOGGER.info(msg);
	}
	
	public void registerPacketListener(PacketListener packetListener) {
		this.packetListeners.add(packetListener);
	}

	public void unregisterPacketListener(PacketListener packetListener) {
		this.packetListeners.remove(packetListener);
	}

	public Set<PacketListener> getPacketListeners() {
		return this.packetListeners;
	}

	public void registerMovementListener(MovementListener movementListener) {
		this.movementListeners.add(movementListener);
	}

	public void unregisterMovementListener(MovementListener movementListener) {
		this.movementListeners.remove(movementListener);
	}

	public Set<MovementListener> getMovementListeners() {
		return this.movementListeners;
	}
	
	public static WindSpigot getInstance() {
		return INSTANCE;
	}
}
