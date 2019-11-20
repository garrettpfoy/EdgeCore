package me.picklez.edgecore.tasks;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.picklez.edgecore.utils.Common;

public class ClanChecker extends BukkitRunnable {


	public ClanChecker(List<String> messages) {

	}

	@Override
	public void run() {

		for(final Player player : Bukkit.getOnlinePlayers()) {

			if(!(player.hasPermission("clan.dwarf") || player.hasPermission("clan.wizard") || player.hasPermission("clan.reaper")) ) {
				Common.tell(player, "&8[&cALERT&8] &7You haven't chosen a clan yet, you must do this! Follow the banners and jump into the portal!");
			}
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

}
