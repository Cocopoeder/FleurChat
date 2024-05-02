package me.cocopoeder.fleurchat;

import me.cocopoeder.fleurchat.commands.DevCommand;
import me.cocopoeder.fleurchat.commands.DumCommand;
import me.cocopoeder.fleurchat.commands.StaffCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FleurChat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getLogger().info("Plugin enabled");
        this.getCommand("staff").setExecutor(new StaffCommand());
        this.getCommand("developer").setExecutor(new DevCommand());
        this.getCommand("Dummie").setExecutor(new DumCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getLogger().info("Plugin disabled");
    }
}
