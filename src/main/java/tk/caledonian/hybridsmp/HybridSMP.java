package tk.caledonian.hybridsmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.database.MySQL;
import tk.caledonian.hybridsmp.database.SQLGetter;
import tk.caledonian.hybridsmp.listener.*;
import tk.caledonian.hybridsmp.managers.CommandManager;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Logger;
import tk.caledonian.hybridsmp.utils.UpdateChecker;

import java.sql.SQLException;

public final class HybridSMP extends JavaPlugin {
    private static HybridSMP plugin;
    public static HybridSMP instance;
    public MySQL SQL;
    public SQLGetter data;

    @Override
    public void onEnable() {
        Logger.log(Logger.LogLevel.INFO, "Starting plugin HybridSMP v1.0-BETA by Caledonian EH");
        // Important
        instance = this;
        Files.base(this);
        new CommandManager(this);

        // Events
        Logger.log(Logger.LogLevel.INFO, "Loading events for HybridSMP...");
        Bukkit.getServer().getPluginManager().registerEvents(new Development(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChatFormatting(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new HeightLimit(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CreeperExplosion(), this);

        // SQL
        SQL = new MySQL();
        data = new SQLGetter(this);

        try {
            SQL.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(SQL.isConnected()){
            Logger.log(Logger.LogLevel.SUCCESS, "SQL Connected, give yourself a gold star now.");
            MySQL.printSQL();
            if(SQL.isConnected() == true){
                data.createTable();
                System.out.println("works");
            }

        }

        Logger.log(Logger.LogLevel.SUCCESS, "HybridSMP by Caledonian EH is fully enabled.");
    }

    @Override
    public void onDisable() {
        instance = null;
        SQL.disconnect();
    }


    public static HybridSMP getPlugin() {
        return plugin;
    }
    public static Plugin getInstance() {
        return instance;
    }
}
