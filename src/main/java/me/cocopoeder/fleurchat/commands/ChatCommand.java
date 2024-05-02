package me.cocopoeder.fleurchat.commands;

import com.sun.jdi.connect.Connector;
import me.cocopoeder.fleurchat.FleurChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {

    private final FleurChat fleurChat;
    public ChatCommand(FleurChat fleurChat){
        this.fleurChat = fleurChat;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Gebruik: /chat <enable of disable>");
                return true;
            }
            if (args.length == 1) {
                switch(args[0]){
                    case "enable":
                        this.fleurChat.update(player.getUniqueId(), false);
                        player.sendMessage(ChatColor.GREEN + "Chat toggle enabled");
                        break;
                    case "disable":
                        this.fleurChat.update(player.getUniqueId(), true);
                        player.sendMessage(ChatColor.RED + "Chat toggle disabled");
                        break;

                }
            }
        }
        return true;
    }
}
