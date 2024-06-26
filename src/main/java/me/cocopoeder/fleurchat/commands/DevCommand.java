package me.cocopoeder.fleurchat.commands;

import me.cocopoeder.fleurchat.FleurChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class DevCommand implements CommandExecutor {
    private final FleurChat fleurChat;

    public DevCommand(FleurChat fleurChat) {
        this.fleurChat = fleurChat;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Collection<? extends Player> players = commandSender.getServer().getOnlinePlayers();
        for (Player player : players) {
            if (!player.hasPermission("developer.chat")) {
                continue;
            }
            if (this.fleurChat.check(player.getUniqueId())) {
                continue;
            }

            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "[Dev] " + commandSender.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + String.join(" ", args));
        }
        return true;
    }
}