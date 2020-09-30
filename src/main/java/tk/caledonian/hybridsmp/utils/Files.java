package tk.caledonian.hybridsmp.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import tk.caledonian.hybridsmp.HybridSMP;

import java.io.File;
import java.io.IOException;

public class Files {

    public static File configFile;
    public static FileConfiguration config;

    public static File msgsFile;
    public static FileConfiguration msgs;

    public static File permFile;
    public static FileConfiguration perms;

    public static void base(HybridSMP m) {
        if (!m.getDataFolder().exists()) {
            m.getDataFolder().mkdirs();
        }
        // config.yml
        configFile = new File(m.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            Logger.log(Logger.LogLevel.INFO, "File config.yml doesn't exist, creating one...");
            m.saveResource("config.yml", false);
            Logger.log(Logger.LogLevel.SUCCESS, "File config.yml was created.");
        }
        // msgs.yml
        msgsFile = new File(m.getDataFolder(), "msgs.yml");
        if (!msgsFile.exists()) {
            Logger.log(Logger.LogLevel.INFO, "File msgs.yml doesn't exist, creating one...");
            m.saveResource("msgs.yml", false);
            Logger.log(Logger.LogLevel.SUCCESS, "File msgs.yml was created.");
        }
        // perms.yml
        permFile = new File(m.getDataFolder(), "perms.yml");
        if (!permFile.exists()) {
            Logger.log(Logger.LogLevel.INFO, "File perms.yml doesn't exist, creating one...");
            m.saveResource("perms.yml", false);
            Logger.log(Logger.LogLevel.SUCCESS, "File perms.yml was created.");
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        Logger.log(Logger.LogLevel.SUCCESS, "File config.yml was loaded");
        msgs = YamlConfiguration.loadConfiguration(msgsFile);
        Logger.log(Logger.LogLevel.SUCCESS, "File msgs.yml was loaded");
        perms = YamlConfiguration.loadConfiguration(permFile);
        Logger.log(Logger.LogLevel.SUCCESS, "File perms.yml was loaded");
    }
    public static void reloadConfig() {config = YamlConfiguration.loadConfiguration(configFile);}
    public static void reloadMsgs() {config = YamlConfiguration.loadConfiguration(msgsFile);}
    public static void reloadPerms() {config = YamlConfiguration.loadConfiguration(permFile);}

    public static void saveConfig() throws IOException {config.save(configFile);}
    public static void saveMsgs() throws IOException {msgs.save(msgsFile);}
    public static void savePerms() throws IOException {perms.save(permFile);}
}
