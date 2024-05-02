package me.cocopoeder.fleurchat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class StaffCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Collection<? extends Player> players = commandSender.getServer().getOnlinePlayers();
        for (Player player : players) {
            if (player.hasPermission("staff.chat")) {
                player.sendMessage(ChatColor.RED +""+ ChatColor.BOLD + "[Staff] " + commandSender.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + String.join(" ", args));

            }
        }
        return true;
    }
}
