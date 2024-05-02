package me.cocopoeder.fleurchat;

import me.cocopoeder.fleurchat.commands.ChatCommand;
import me.cocopoeder.fleurchat.commands.DevCommand;
import me.cocopoeder.fleurchat.commands.DumCommand;
import me.cocopoeder.fleurchat.commands.StaffCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;


public final class FleurChat extends JavaPlugin implements Listener {

    private Map<UUID,Boolean> data = new ConcurrentHashMap(); //hashmap aanmaken met de naam data.
    //we gaan een UUID opslaan en een truefalse waarde (boolean)
private static FleurChat plugin;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        plugin = this;
        this.load();
        // Plugin startup logic
        this.getServer().getLogger().info("Plugin enabled");
        this.getCommand("staff").setExecutor(new StaffCommand(this));
        this.getCommand("developer").setExecutor(new DevCommand(this));
        this.getCommand("Dummie").setExecutor(new DumCommand(this));
        this.getCommand("chat").setExecutor(new ChatCommand(this));
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        plugin = this;
        if (player.hasPermission("chat.toggle")){
            if (this.check(player.getUniqueId())) {
                player.sendMessage(ChatColor.RED + "Chats zijn niet enabled");
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getServer().getLogger().info("Plugin disabled");
    }

    public void save() {

        //we gaan alle opgeslagen gegevens ophalen
        for(UUID uuid : data.keySet()) {
            boolean value = data.get(uuid); //haal de bijbehorende true/false op
            getConfig().set("saved."+uuid.toString(), value); //uuid.toString zodat we de uuid echt kunnen lezen
        }
        saveConfig();
    }

    public void load() {
        data = new ConcurrentHashMap<>(); // nieuwe hashpam
        if(getConfig().getConfigurationSection("saved") != null ) { //controleer of er al wat in de configuratie staat
            Set<String> set = getConfig().getConfigurationSection("saved").getKeys(false);
            for(String uuid : set) {
                boolean value = getConfig().getBoolean(uuid);
                data.put(UUID.fromString(uuid),value);
            }
        }
    }

    public void update(UUID uuid, Boolean trueFalse) {
        this.data.put(uuid,trueFalse); //uuid en truefalse waarde opslaan in hashmap
        this.save();
    }

    public boolean check(UUID uuid) {
        //als de waarde niet aanwezig is in de hashmap , geef dan een false zodat
        if (this.data.get(uuid) == null) {
            return false;
        }

        return this.data.get(uuid); //truefalse waarde ophalen uit hashmap
    }
}
