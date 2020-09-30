package tk.caledonian.hybridsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Logger;
import tk.caledonian.hybridsmp.utils.Utils;

import java.util.List;

public class HybridCore implements CommandHandler {
    private JavaPlugin plugin;
    public HybridCore(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public void execute(CommandSender sender, Command command, String[] args){
        String prefix = Utils.chat(Files.msgs.getString("prefix"));
        String ver = plugin.getDescription().getVersion();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (Files.config.getBoolean("enable-core-cmd") == true) {
                    if (p.hasPermission(Files.perms.getString("core-cmd"))) {
                        List<String> msg1 = Files.msgs.getStringList("core-cmd-admin");
                        for (String x : msg1) {
                            x = x.replace("%prefix%", prefix);
                            x = x.replace("%version%", ver);
                            sender.sendMessage(Utils.chat(x));
                        }
                    } else {
                        List<String> msg2 = plugin.getConfig().getStringList("core-cmd-user");
                        for (String x : msg2) {
                            x = x.replace("%prefix%", prefix);
                            x = x.replace("%version%", ver);
                            sender.sendMessage(Utils.chat(x));
                        }
                    }
                }
            } else if (args.length >= 1) {
                if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                    List<String> help1 = Files.msgs.getStringList("help-1");
                    for (String x : help1) {
                        sender.sendMessage(Utils.chat(x));
                    }
                } else if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("2")) {
                    List<String> help2 = Files.msgs.getStringList("help-2");
                    for (String x : help2) {
                        sender.sendMessage(Utils.chat(x));
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (sender instanceof Player) {
                        if (p.hasPermission(Files.perms.getString("reload"))) {
                            p.sendMessage(Utils.chat(Files.msgs.getString("config-reload")).replace("%prefix%", prefix));
                            Logger.log(Logger.LogLevel.INFO, "&b" + p.getName() + " &fjust reloaded all configs.");
                            reload();
                        } else {
                            p.sendMessage(Utils.chat(Files.msgs.getString("no-permission")));
                        }
                    } else {
                        Bukkit.getConsoleSender().sendMessage(Files.msgs.getString("config-reload").replace("%prefix%", prefix));
                        Bukkit.getConsoleSender().sendMessage("The console reloaded all configs.");
                        reload();
                    }
                }else{
                    p.sendMessage(Utils.chat(Files.msgs.getString("404")).replace("%prefix%", prefix));
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(Utils.chat(Files.msgs.getString("console-error")));
        }
    }
    private static void reload() {
        Logger.log(Logger.LogLevel.INFO, "Reloading config.yml...");
        Files.reloadConfig();
        Logger.log(Logger.LogLevel.SUCCESS, "The file config.yml was reloaded");

        Logger.log(Logger.LogLevel.INFO, "Reloading msgs.yml...");
        Files.reloadMsgs();
        Logger.log(Logger.LogLevel.SUCCESS, "The file msgs.yml was reloaded");

        Logger.log(Logger.LogLevel.INFO, "Reloading perms.yml...");
        Files.reloadPerms();
        Logger.log(Logger.LogLevel.SUCCESS, "The file perms.yml was reloaded");
    }
}
