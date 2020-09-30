package tk.caledonian.hybridsmp.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Utils;

public class Broadcast implements CommandHandler {
    String prefix = Files.msgs.getString("prefix");
    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission(Files.perms.getString("broadcast"))){
                StringBuilder msg = new StringBuilder("");
                for(String part : args){
                    if(!msg.toString().equals(""))
                        msg.append(" ");
                }
                Bukkit.getServer().broadcastMessage(Utils.chat(Files.msgs.getString("broadcast.message").replace("%prefix%", Files.msgs.getString("broadcast.prefix").replace("%message%", msg))));
            }else{p.sendMessage(Files.msgs.getString("no-permission").replace("%prefix%", prefix));}
        }else{Bukkit.getConsoleSender().sendMessage(Files.msgs.getString("console-error"));}
    }
}
