package me.cocopoeder.fleurchat.commands;

import me.cocopoeder.fleurchat.FleurChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class StaffCommand implements CommandExecutor {

    private final FleurChat fleurChat;

    public StaffCommand(FleurChat fleurChat) {
        this.fleurChat = fleurChat;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Collection<? extends Player> players = commandSender.getServer().getOnlinePlayers();
        for (Player player : players) {
            if (!player.hasPermission("staff.chat")) {
                continue;
            }
            if (this.fleurChat.check(player.getUniqueId())) {
                continue;
            }

            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Staff] " + commandSender.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + String.join(" ", args));
        }
        return true;
    }
}
