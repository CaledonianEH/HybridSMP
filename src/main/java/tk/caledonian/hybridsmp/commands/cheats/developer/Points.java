package tk.caledonian.hybridsmp.commands.cheats.developer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.database.SQLGetter;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Logger;
import tk.caledonian.hybridsmp.utils.Utils;

public class Points implements CommandHandler {
    public SQLGetter data;
    private JavaPlugin plugin;
    public Points(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            data.createPlayer(p);
            if(args[0] == "add"){
                data.addPoints(p.getUniqueId(), 1);
            }else if(args[0] == "remove"){
                data.remPoints(p.getUniqueId(), 1);
            }else if(args.length == 0){
                p.sendMessage(Utils.chat("&fYou have &6" + data.getPoints(p.getUniqueId()) + " &fpoints."));
            }

        }else{
            Logger.log(Logger.LogLevel.ERROR, "na m8");
        }
    }
}
