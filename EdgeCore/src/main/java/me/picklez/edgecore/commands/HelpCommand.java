package me.picklez.edgecore.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import me.picklez.edgecore.utils.Common;

public class HelpCommand extends BukkitCommand {

	public HelpCommand() {
		super("edge help");

		setAliases(Arrays.asList("edgehelp", "ehelp"));
		setDescription("Basic help command for EdgeCore plugin");

	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {

		if(!(sender instanceof Player)) {

			Common.tell(sender, "&4&lYou must be a player to use EdgeCore");

			return false;
		}

		if(!(sender.hasPermission("ecore.help"))) {
			Common.tell(sender, "&cYou lack the permission: ecore.help");
		}

		if(sender.hasPermission("ecore.help")) {
			Common.tell(sender, "&8&m------&r&bEdge&fCore Help:&8&m------&r\n&b/help &8- &7Displays this command\n&b/clan &8- &7Displays your clan");
		}


		return true;
	}

}
