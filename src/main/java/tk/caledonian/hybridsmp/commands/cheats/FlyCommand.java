package tk.caledonian.hybridsmp.commands.cheats;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Utils;

public class FlyCommand implements CommandHandler {
    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if(sender instanceof Player){

        }else{Bukkit.getConsoleSender().sendMessage(Utils.chat(Files.msgs.getString("console-error")));}
    }
}
