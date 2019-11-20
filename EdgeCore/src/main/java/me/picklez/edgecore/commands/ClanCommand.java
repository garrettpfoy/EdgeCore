package me.picklez.edgecore.commands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import me.picklez.edgecore.utils.Common;

public class ClanCommand extends BukkitCommand {

	public ClanCommand() {
		super("clan");

		setAliases(Arrays.asList("myclan", "clans"));
		setDescription("This command tells you what clan you are in!");

	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {

		if(!(sender instanceof Player)) {
			Common.tell(sender, "&4&lYou must be a player to use EdgeCore");
			return false;
		}

		if(sender.hasPermission("clan.dwarf")) {
			Common.tell(sender,  "&7You are in the &a&lDwarf&7 clan! \n\n&c&lPerks:\n&8- &7Night Vision\n&8- &7Haste");
		}

		if(sender.hasPermission("clan.wizard")) {
			Common.tell(sender,  "&7You are in the &e&lWizard &7clan! \n\n&c&lPerks:\n&8- &7Health Boost");
		}

		if(sender.hasPermission("clan.reaper")) {
			Common.tell(sender, "&7You are in the &3&lReaper&7 clan! \n\n&c&lPerks:\n&8- &Speed\n&8- &7Saturation");
		}






		return true;
	}

}

