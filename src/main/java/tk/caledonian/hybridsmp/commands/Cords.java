package tk.caledonian.hybridsmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Utils;

public class Cords implements CommandHandler {
    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String prefix = Utils.chat(Files.msgs.getString("prefix"));
            Integer x = p.getLocation().getBlockX();
            Integer y = p.getLocation().getBlockY();
            Integer z = p.getLocation().getBlockZ();
            Bukkit.broadcastMessage(Utils.chat(Files.msgs.getString("other.cords")).replace("%x%", x.toString()).replace("%y%", y.toString()).replace("%z%", z.toString()).replace("%player%", p.getName()).replace("%prefix%", prefix));
        }else{Bukkit.getConsoleSender().sendMessage(Utils.chat(Files.msgs.getString("console-error")));}
    }
}
