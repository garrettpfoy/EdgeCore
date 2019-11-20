package me.picklez.edgecore.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.picklez.edgecore.EdgeCore.Main;
import me.picklez.edgecore.EdgeCore.PlayerCache;
import me.picklez.edgecore.utils.ChatUtils;
import me.picklez.edgecore.utils.Common;
import net.md_5.bungee.api.ChatColor;

public class PlayerListener implements Listener{

	@EventHandler
	public void onPlayerLogin(AsyncPlayerPreLoginEvent e) {


		final UUID id = e.getUniqueId();
		final PlayerCache cache = Main.getCache(id);

		final long lastLoginTime = cache.getLastLoginTime();
		final long difference = System.currentTimeMillis() - lastLoginTime;

		if (lastLoginTime != 0 && difference < (10 * 1000)) {
			e.disallow(Result.KICK_OTHER, ChatColor.RED + "Please wait " + ChatColor.YELLOW + (5 - (difference / 1000)) + ChatColor.RED + " seconds before logging in again.");
		}
		else {
			cache.setLastLoginTime(System.currentTimeMillis());
		}
	}

	@EventHandler
	public void onPlayerMessage(AsyncPlayerChatEvent e) {
		final Player pl = e.getPlayer();
		final String msg = e.getMessage();

		final PlayerCache cache = Main.getCache(pl.getUniqueId());

		if(cache.getLastChatMessages().equalsIgnoreCase(msg)) {
			Common.tell(pl, "&c&l(!) &7You just typed that message, change it up a bit!");

			e.setCancelled(true);
		}
		else {
			cache.setLastChatMessages(msg);

		}

		ChatUtils.write("server.log", pl.getName(), msg);

	}


	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Main.getCache(e.getPlayer().getUniqueId()).save();

	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player player = e.getPlayer();


		Common.tell(player, "&8&m-----------[&r &bEdge&fGaming &8&m]------------\n\n&a&lFORUMS &7www.edge-gamers.com\n&b&lTEAMSPEAK &7ts.edge-gamers.com\n&d&lVOTE &7/vote\n\n&8&m----------------------------");

		final PotionEffect speed = PotionEffectType.SPEED.createEffect(999999999, 0);
		if(player.hasPermission("clan.reaper")) {
			player.addPotionEffect(speed);
		}

		final PotionEffect haste = PotionEffectType.FAST_DIGGING.createEffect(99999999, 0);
		final PotionEffect nightVision = PotionEffectType.NIGHT_VISION.createEffect(999999999, 0);
		if(player.hasPermission("clan.dwarf")) {
			player.addPotionEffect(haste);
			player.addPotionEffect(nightVision);
		}

		final PotionEffect healthBoost = PotionEffectType.HEALTH_BOOST.createEffect(999999999, 1);
		final PotionEffect waterBreathing = PotionEffectType.WATER_BREATHING.createEffect(999999999, 0);
		if(player.hasPermission("clan.wizard")) {
			player.addPotionEffect(healthBoost);
			player.addPotionEffect(waterBreathing);
		}
	}




}
