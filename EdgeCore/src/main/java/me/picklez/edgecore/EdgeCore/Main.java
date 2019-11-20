package me.picklez.edgecore.EdgeCore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.picklez.edgecore.commands.ClanCommand;
import me.picklez.edgecore.commands.HelpCommand;
import me.picklez.edgecore.config.SimpleConfig;
import me.picklez.edgecore.events.PlayerListener;
import me.picklez.edgecore.tasks.Broadcaster;
import me.picklez.edgecore.tasks.ClanChecker;
import me.picklez.edgecore.utils.Common;

public class Main extends JavaPlugin {

	private static final Map<UUID, PlayerCache> playerCache = new HashMap<>(); //in a later release, use google Guava to make faster

	private static Main instance;



	private SimpleConfig cfg;

	@Override
	public void onEnable() {
		instance = this;

		getLogger().info("Version 0.0.1 has been enabled. Maintained and created by: PickleZ");
		Common.registerCommand(new HelpCommand());
		Common.registerCommand(new ClanCommand());

		cfg = new SimpleConfig("settings.yml");

		getServer().getPluginManager().registerEvents(new PlayerListener(),  this);

		new Broadcaster(cfg.getStringList("messages")).runTaskTimer(this, 3*20, 120*20);
		new ClanChecker(cfg.getStringList("messages")).runTaskTimer(this, 3*20, 15*20);

		for (final Player online : getServer().getOnlinePlayers()) {
			getCache(online.getUniqueId());
		}
	}

	@Override
	public void onDisable() {
		getLogger().info("Version 0.0.1 has been disabled.");
		instance = null;

		for (final PlayerCache cache : playerCache.values()) {
			cache.save();
		}

	}

	public static PlayerCache getCache(UUID id) {
		PlayerCache cache = playerCache.get(id);

		if(cache == null) {
			cache = new PlayerCache(id);

			playerCache.put(id, cache);
		}

		return cache;
	}

	public static Main getInstance() {
		return instance;
	}

}
