package de.villigi.wandoriainventorys;

import de.villigi.wandoriainventorys.commands.EnderChestCMD;
import de.villigi.wandoriainventorys.commands.InvseeCMD;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Inventorys extends JavaPlugin {

    private File file;
    private FileConfiguration configuration;

    public static String prefix = "";

    private static Inventorys instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        file = new File("plugins/Wandoria-Inventorys", "config.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String prefixRaw = configuration.getString("prefix");
        prefix = ChatColor.translateAlternateColorCodes('&', prefixRaw);
        getCommand("invsee").setExecutor(new InvseeCMD());
        getCommand("enderchest").setExecutor(new EnderChestCMD());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Inventorys getInstance() {
        return instance;
    }



    public FileConfiguration getConfiguration() {
        return configuration;
    }
}
