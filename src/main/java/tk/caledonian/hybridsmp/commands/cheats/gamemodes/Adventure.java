package tk.caledonian.hybridsmp.commands.cheats.gamemodes;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Logger;
import tk.caledonian.hybridsmp.utils.Utils;

public class Adventure implements CommandHandler {
    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        String prefix = Files.msgs.getString("prefix");
        String gamemode = Files.msgs.getString("gamemodes.names.adventure");
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0){
                // Player
                if(p.hasPermission(Files.perms.getString("adventure.self"))){
                    String self = PlaceholderAPI.setPlaceholders(p, Files.msgs.getString("gamemodes.self").replace("%player%", p.getName()).replace("%prefix%", prefix).replace("%gamemode%", gamemode));
                    p.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(Utils.format(self));

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission(Files.perms.getString("logs.gamemode"))) {
                            if(player.getName() != p.getName()){
                                player.sendMessage(Utils.chat(Files.msgs.getString("logs.gamemode.self").replace("%prefix%", prefix
                                ).replace("%player%", p.getName()).replace("%gamemode%", gamemode)));
                            }
                        }else{return;}
                    }
                }
            }else if(args.length == 1){
                Player t = Bukkit.getPlayer(args[0]);
                if(t instanceof Player){
                if(p.hasPermission(Files.perms.getString("adventure.other"))){
                        String target = PlaceholderAPI.setPlaceholders(p, Files.msgs.getString("gamemodes.target").replace("%prefix%", prefix)
                                .replace("%gamemode%", gamemode).replace("%target%", t.getName()).replace("%player%", p.getName()));
                        String s = PlaceholderAPI.setPlaceholders(t, Files.msgs.getString("gamemodes.sender").replace("%prefix%", prefix)
                                .replace("%gamemode%", gamemode).replace("%target%", t.getName()).replace("%player%", p.getName()));
                        t.setGameMode(GameMode.ADVENTURE);
                        t.sendMessage(Utils.format(target));
                        p.sendMessage(Utils.format(s));

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission(Files.perms.getString("logs.gamemode"))) {
                            if(player.getName() != p.getName()){
                                player.sendMessage(Utils.chat(Files.msgs.getString("logs.gamemode.other").replace("%prefix%", prefix
                                ).replace("%player%", p.getName()).replace("%gamemode%", gamemode).replace("%target%", t.getName())));
                            }
                        }else{return;}
                    }
                    }else{p.sendMessage(Utils.chat(Files.msgs.getString("no-permission").replace("%prefix%", prefix)));}
                }else{p.sendMessage(Utils.chat(Files.msgs.getString("player-not-found").replace("%prefix%", prefix).replace("%player%", args[0])));}
            }else if(args.length > 1){p.sendMessage(Utils.format(Files.msgs.getString("args").replace("%prefix%", prefix)));}
        }else{
            Logger.log(Logger.LogLevel.INFO, Files.msgs.getString("console-error"));
        }
    }
}
