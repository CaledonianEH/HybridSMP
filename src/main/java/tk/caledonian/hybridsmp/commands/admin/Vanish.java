package tk.caledonian.hybridsmp.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Vanish implements CommandHandler {
    public static ArrayList<Player> vanished = new ArrayList<>();

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        String prefix = Utils.chat(Files.msgs.getString("prefix"));
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                if(p.hasPermission(Files.perms.getString("admin.vanish.self"))){
                    if(vanished.contains(p)){
                        String status = Files.msgs.getString("names.disabled");
                        vanished.remove(p);
                        for (Player player : Bukkit.getOnlinePlayers()){player.showPlayer(p);}
                        p.sendMessage(Utils.chat(Files.msgs.getString("vanish.self")).replace("%prefix%", prefix)
                                .replace("%status%", status));
                        // Log
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(Files.perms.getString("logs.gamemode"))) {
                                if(player.getName() != p.getName()){
                                    player.sendMessage(Utils.chat(Files.msgs.getString("logs.vanish.self").replace("%prefix%", prefix
                                    ).replace("%player%", p.getName()).replace("%status%", status)));
                                }
                            }else{return;}
                        }
                    }else{
                        String status = Files.msgs.getString("names.enabled");
                        vanished.add(p);
                        for (Player player : Bukkit.getOnlinePlayers()){
                            if(!player.hasPermission(Files.perms.getString("admin.vanish.see"))){player.hidePlayer(p);}
                        }
                        p.sendMessage(Utils.chat(Files.msgs.getString("vanish.self")).replace("%prefix%", prefix)
                                .replace("%status%", status));
                        // Log
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(Files.perms.getString("logs.gamemode"))) {
                                if(player.getName() != p.getName()){
                                    player.sendMessage(Utils.chat(Files.msgs.getString("logs.vanish.self").replace("%prefix%", prefix
                                    ).replace("%player%", p.getName()).replace("%status%", status)));
                                }
                            }else{return;}
                        }
                    }

                }else{p.sendMessage(Utils.chat(Files.msgs.getString("no-permission").replace("%prefix%", prefix)));}
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("help")) {
                    List<String> help = Files.msgs.getStringList("vanish.usage.help");
                    for (String x : help) {
                        sender.sendMessage(Utils.chat(x).replace("%prefix%", prefix));
                    }
                }else if(args.length == 1){
                    if(p.hasPermission(Files.perms.getString("admin.vanish.other"))){
                        Player t = Bukkit.getPlayer(args[0]);
                        if(t instanceof Player){
                            if(vanished.contains(t)){
                                String status = Files.msgs.getString("names.disabled");
                                vanished.remove(t);
                                for (Player player : Bukkit.getOnlinePlayers()){player.showPlayer(t);}
                                t.sendMessage(Utils.chat(Files.msgs.getString("vanish.other.other")).replace("%prefix%", prefix)
                                        .replace("%status%", status).replace("%player%", p.getName()));
                                p.sendMessage(Utils.chat(Files.msgs.getString("vanish.other.sender")).replace("%prefix%", prefix)
                                        .replace("%status%", status).replace("%player%", t.getName()));
                                // Log
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (player.hasPermission(Files.perms.getString("logs.gamemode"))) {
                                        if(player.getName() != p.getName() && (player.getName() != t.getName())){
                                            player.sendMessage(Utils.chat(Files.msgs.getString("logs.vanish.other").replace("%prefix%", prefix
                                            ).replace("%player%", p.getName()).replace("%status%", status)).replace("%target%", t.getName()));
                                        }
                                    }else{return;}
                                }
                            }else{
                                String status = Files.msgs.getString("names.enabled");
                                vanished.add(t);
                                for (Player player : Bukkit.getOnlinePlayers()){
                                    if(!player.hasPermission(Files.perms.getString("admin.vanish.see"))){player.hidePlayer(p);}
                                }
                                t.sendMessage(Utils.chat(Files.msgs.getString("vanish.other.other")).replace("%prefix%", prefix)
                                        .replace("%status%", status).replace("%player%", p.getName()));
                                p.sendMessage(Utils.chat(Files.msgs.getString("vanish.other.sender")).replace("%prefix%", prefix)
                                        .replace("%status%", status).replace("%player%", t.getName()));
                                // Log
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (player.hasPermission(Files.perms.getString("logs.gamemode"))) {
                                        if(player.getName() != p.getName() && (player.getName() != t.getName())){
                                            player.sendMessage(Utils.chat(Files.msgs.getString("logs.vanish.other").replace("%prefix%", prefix
                                            ).replace("%player%", p.getName()).replace("%status%", status)).replace("%target%", t.getName()));
                                        }
                                    }else{return;}
                                }
                            }
                        }else{p.sendMessage(Utils.chat(Files.msgs.getString("player-not-found").replace("%prefix%", prefix)).replace("%player%", args[0].toString()));}
                    }else{p.sendMessage(Utils.chat(Files.msgs.getString("no-permission").replace("%prefix%", prefix)));}
                }else{p.sendMessage(Utils.chat(Files.msgs.getString("vanish.usage.usage").replace("%prefix%", prefix)));}
            }else{p.sendMessage(Utils.chat(Files.msgs.getString("vanish.usage.usage").replace("%prefix%", prefix)));}
        }else{Bukkit.getConsoleSender().sendMessage(Files.msgs.getString("console-error"));}
    }
}
