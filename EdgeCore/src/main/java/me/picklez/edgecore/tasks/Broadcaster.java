package me.picklez.edgecore.tasks;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.picklez.edgecore.utils.Common;

public class Broadcaster extends BukkitRunnable {

	private static Random rand = new Random();

	private final List<String> messages;


	public Broadcaster(List<String> messages) {
		this.messages = messages;

	}

	@Override
	public void run() {

		for(final Player player : Bukkit.getOnlinePlayers()) {
			final String prefix = "&8[&9TIP&8] ";
			final String message = messages.get(rand.nextInt(messages.size()));

			Common.tell(player, prefix + message);


		}
	}

}
