package tk.caledonian.hybridsmp.commands.admin;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

public class StaffList implements CommandHandler {
    public static ArrayList<Player> hide = new ArrayList<>();

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            final List<Player> staffs = new ArrayList<Player>();
            if(args.length == 0){
                for (Player pls : Bukkit.getOnlinePlayers()) {
                    if (pls.hasPermission(Files.perms.getString("staff")) && !Vanish.vanished.contains(pls) && !hide.contains(pls)) {
                        staffs.add(pls);
                    }
                }
                if (!staffs.isEmpty()) {
                    for (String header1 : Files.msgs.getStringList("stafflist.format.header")){
                        header1 = header1.replace("&", "§");
                        p.sendMessage(header1);
                    }
                    for(int i = 0; i != staffs.size(); ++i) {
                        final Player player = staffs.get(i);
                        final TextComponent tp = new TextComponent(Utils.chat(Files.msgs.getString("stafflist.staff").replace("%player%", player.getName()).replace("%server%",
                                Files.config.getString("server-name"))));

                        String hover1 = Files.config.getString("staff.stafflist.events.hover");
                        hover1 = hover1.replace("&", "§");
                        hover1 = hover1.replace("%player%", player.getName());
                        hover1 = hover1.replace("%server%", Files.config.getString("server-name"));
                        tp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover1).create()));

                        if (Files.config.getString("staff.stafflist.events.action").equalsIgnoreCase("suggest")) {
                            tp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Files.config.getString("staff.stafflist.events.click").replace("%player%", p.getName())));
                            p.spigot().sendMessage(tp);
                        } else if (Files.config.getString("staff.stafflist.events.action").equalsIgnoreCase("command")) {
                            tp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Files.config.getString("staff.stafflist.events.click").replace("%player%", p.getName())));
                            p.spigot().sendMessage(tp);
                        } else if (Files.config.getString("staff.stafflist.events.action").equalsIgnoreCase("url")) {
                            tp.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Files.config.getString("staff.stafflist.events.click").replace("%player%", p.getName())));
                            p.spigot().sendMessage(tp);
                        } else {
                            Logger.log(Logger.LogLevel.ERROR, "The click event type is not valid in the config.yml");
                        }
                    }
                    for (String footer1 : Files.msgs.getStringList("stafflist.format.footer")){
                        footer1 = footer1.replace("&", "§");
                        p.sendMessage(footer1);
                    }
                } else {
                    p.sendMessage(Utils.chat(Files.msgs.getString("stafflist.format.empty")).replace("%prefix%", Utils.chat(Files.msgs.getString("prefix"))));
                }
            }else if(args.length == 1 && args[0].equalsIgnoreCase("hide")){
                String prefix = Utils.chat(Files.msgs.getString("prefix"));
                if(hide.contains(p)){
                    String status = Files.msgs.getString("names.showed");
                    p.sendMessage(Utils.chat(Files.msgs.getString("stafflist.hide").replace("%prefix%", prefix).replace("%status%", status)));
                    hide.remove(p);
                    // Log
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission(Files.perms.getString("logs.staff-hide"))) {
                            if(player.getName() != p.getName()){
                                player.sendMessage(Utils.chat(Files.msgs.getString("logs.staff-hide").replace("%prefix%", prefix
                                ).replace("%player%", p.getName()).replace("%status%", status)));
                            }
                        }else{return;}
                    }
                }else{
                    String status = Files.msgs.getString("names.hid");
                    p.sendMessage(Utils.chat(Files.msgs.getString("stafflist.hide").replace("%prefix%", prefix).replace("%status%", status)));
                    hide.add(p);
                    // Log
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission(Files.perms.getString("logs.staff-hide"))) {
                            if(player.getName() != p.getName()){
                                player.sendMessage(Utils.chat(Files.msgs.getString("logs.staff-hide").replace("%prefix%", prefix
                                ).replace("%player%", p.getName()).replace("%status%", status)));
                            }
                        }else{return;}
                    }
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(Utils.chat(Files.msgs.getString("console-error")));
        }
    }
}
