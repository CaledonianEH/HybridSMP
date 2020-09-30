package tk.caledonian.hybridsmp.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.commands.admin.StaffChat;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Logger;
import tk.caledonian.hybridsmp.utils.Utils;

import java.util.Iterator;
import java.util.List;

public class ChatFormatting implements Listener {
    private JavaPlugin plugin;
    public ChatFormatting(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){
           if(Files.config.getBoolean("chat-formatting.enable") == true) {
               e.getFormat();
               Player p = e.getPlayer();
               e.setCancelled(true);

               String chat = PlaceholderAPI.setPlaceholders(p, Utils.chat(Files.config.getString("chat-formatting.format").replace("%player%", p.getName().replace("%displayname%", p.getDisplayName()))));
               TextComponent format = new TextComponent(chat);
               TextComponent msg = new TextComponent(Utils.chat(e.getMessage()));

               // Hover
               format.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.chat(Files.config.getString("chat-formatting.hover").replace("%player%", p.getName()))).create()));
               msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.chat(Files.config.getString("chat-formatting.hover").replace("%player%", p.getName()))).create()));
               // Click
               if (Files.config.getString("chat-formatting.click.action").equalsIgnoreCase("suggest")) {
                   format.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, PlaceholderAPI.setPlaceholders(p, Files.config.getString("chat-formatting.click.command").replace("%player%", p.getName()))));
                   msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, PlaceholderAPI.setPlaceholders(p, Files.config.getString("chat-formatting.click.command").replace("%player%", p.getName()))));
               } else if (Files.config.getString("chat-formatting.click.action").equalsIgnoreCase("command")) {
                   format.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, PlaceholderAPI.setPlaceholders(p, Files.config.getString("chat-formatting.click.command").replace("%player%", p.getName()))));
                   msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, PlaceholderAPI.setPlaceholders(p, Files.config.getString("chat-formatting.click.command").replace("%player%", p.getName()))));
               } else if (Files.config.getString("chat-formatting.click.action").equalsIgnoreCase("url")) {
                   format.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, PlaceholderAPI.setPlaceholders(p, Files.config.getString("chat-formatting.click.command").replace("%player%", p.getName()))));
                   msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, PlaceholderAPI.setPlaceholders(p, Files.config.getString("chat-formatting.click.command").replace("%player%", p.getName()))));
               } else {
                   Logger.log(Logger.LogLevel.ERROR, "The click event type is not valid in the config.yml");
               }
               for (Player player : Bukkit.getOnlinePlayers()) {
                   if (!StaffChat.sc.contains(player)) {
                       player.spigot().sendMessage(format, msg);
                   }else{return;}
               }
           }
        }
    @EventHandler
    void staffChat(AsyncPlayerChatEvent e){
        if(Files.config.getBoolean("staff.staffchat") == true){
            Player p = e.getPlayer();
            if(p.hasPermission(Files.perms.getString("admin.staffchat")) && StaffChat.sc.contains(p)){
                e.setCancelled(true);
                String chat = Utils.chat(e.getMessage());
                Iterator var = Bukkit.getOnlinePlayers().iterator();

                while(var.hasNext()){
                    Player player = (Player)var.next();

                    String getter1 = Utils.chat(Files.msgs.getString("staffchat.format"));
                    String getter2 = getter1.replaceAll("%player%", e.getPlayer().getDisplayName());
                    String getter3 = getter2.replaceAll("%server%", Files.config.getString("server-name"));
                    String getter4 = getter3.replaceAll("%chat%", chat);
                    player.sendMessage(Utils.chat(getter4));
                }
            }
        }
    }
    }
