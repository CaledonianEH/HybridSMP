package tk.caledonian.hybridsmp.commands.reactions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Utils;

public class Rip implements CommandHandler {
    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            String prefix = Files.msgs.getString("prefix");
            String player = p.getName();
            Bukkit.broadcastMessage(Utils.chat(Files.msgs.getString("reactions.rip").replace("%prefix%", prefix).replace("%player%", player)));

        }else{Bukkit.getConsoleSender().sendMessage(Utils.chat(Files.msgs.getString("console-error")));}
    }
}
