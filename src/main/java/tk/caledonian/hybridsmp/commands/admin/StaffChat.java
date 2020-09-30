package tk.caledonian.hybridsmp.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.managers.CommandHandler;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Logger;
import tk.caledonian.hybridsmp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StaffChat implements CommandHandler {
    public static ArrayList<Player> sc = new ArrayList<>();

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        String prefix = Files.msgs.getString("prefix");
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Files.perms.getString("admin.staffchat"))) {
                if (args.length == 0) {
                    if (sc.contains(p)) {
                        sc.remove(p);
                        p.sendMessage(Utils.chat(Files.msgs.getString("staffchat.args.disable").replace("%prefix%", prefix)));
                    } else if (!sc.contains(p)) {
                        sc.add(p);
                        p.sendMessage(Utils.chat(Files.msgs.getString("staffchat.args.enable").replace("%prefix%", prefix)));
                    } else {
                        Logger.log(Logger.LogLevel.ERROR, "Could not get the staffchat array list.");
                    }
                } else if (args.length == 1) {
                    // Enable
                    if (args[0].equalsIgnoreCase("enable")) {
                        sc.add(p);
                        p.sendMessage(Utils.chat(Files.msgs.getString("staffchat.args.enable").replace("%prefix%", prefix)));
                    }
                    // Disable
                    else if (args[0].equalsIgnoreCase("disable")) {
                        sc.remove(p);
                        p.sendMessage(Utils.chat(Files.msgs.getString("staffchat.args.disable").replace("%prefix%", prefix)));
                    }
                    // Help
                    else if (args[0].equalsIgnoreCase("help")) {
                        List<String> help = Files.msgs.getStringList("staffchat.help");
                        for (String x : help) {
                            sender.sendMessage(Utils.chat(x).replace("%prefix%", prefix));
                        }
                    } else {
                        p.sendMessage(Utils.chat(Files.msgs.getString("staffchat.args.usage").replace("%prefix%", prefix)));
                    }
                } else {
                    p.sendMessage(Utils.chat(Files.msgs.getString("args")));
                }
            } else{p.sendMessage(Utils.chat(Files.msgs.getString("no-permission").replace("%prefix%", prefix)));}
        }else {
            Bukkit.getConsoleSender().sendMessage(Utils.chat(Files.msgs.getString("console-error").replace("%prefix%", prefix)));
        }
    }
}
