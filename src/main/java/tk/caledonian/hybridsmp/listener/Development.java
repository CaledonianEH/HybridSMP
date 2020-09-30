package tk.caledonian.hybridsmp.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.HybridSMP;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.UpdateChecker;
import tk.caledonian.hybridsmp.utils.Utils;

public class Development implements Listener {
    private JavaPlugin plugin;
    public Development(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void devJoin(PlayerJoinEvent e){
        String update = Utils.chat("&f&o&ncaledonian.tk/hybridcore");
        Player p = e.getPlayer();
        // Update Checker
        Bukkit.getScheduler().runTaskLater(plugin, () ->{
           if(p.hasPermission(Files.perms.getString("update")) && Files.config.getBoolean("update-alert") == true){
               new UpdateChecker(HybridSMP.getPlugin(), 6245).getLatestVersion(version -> {
                   if(!Utils.ver.equalsIgnoreCase(version)){
                       p.sendMessage(Utils.chat("&c*&8&m-----------&c*&8&m------------------&c*&8&m-----------&c*"));
                       p.sendMessage(Utils.chat("&f            &c&lHybridCore-SMP &fis outdated!"));
                       p.sendMessage(Utils.chat("&f "));
                       p.sendMessage(Utils.chat("&f * &cCurrent Version&8: &f%version%").replace("%version%", plugin.getDescription().getVersion()));
                       p.sendMessage(Utils.chat("&f * &cLatest Version&8: &f%version%").replace("%version%", version));
                       p.sendMessage(Utils.chat("&f * &cUpdate Here&8: &f%link%").replace("%link%", update));
                       p.sendMessage(Utils.chat("&c*&8&m-----------&c*&8&m------------------&c*&8&m-----------&c*"));
                   }
               });
           }else{return;}
        }, 40L);
    }
    @EventHandler
    public void cal(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (p.getName().equalsIgnoreCase("Caledonian_EH") || (p.getName().equalsIgnoreCase("Caledonian_LH"))) {
                p.sendMessage(Utils.chat("&c*&8&m-----------&c*&8&m------------------&c*&8&m-----------&c*"));
                p.sendMessage(Utils.chat("&f "));
                p.sendMessage(Utils.chat("&c&l * &fWelcome &c" + p.getName() + "&f!"));
                p.sendMessage(Utils.chat("&c&l * &fThis server is currently running HybridCore &cv" + plugin.getDescription().getVersion() + "&f."));
                p.sendMessage(Utils.chat("&c&l * &fPlugin Name: &c" + plugin.getDescription().getName() + "&f."));
                p.sendMessage(Utils.chat("&c&l * &fAuthor: &c" + plugin.getDescription().getAuthors() + "&7."));
                p.sendMessage(Utils.chat("&f "));
                p.sendMessage(Utils.chat("&7&o(( Your seeing this message due to being a &f&odeveloper &7&o))"));
                p.sendMessage(Utils.chat("&c*&8&m-----------&c*&8&m------------------&c*&8&m-----------&c*"));
            }
        }, 30L);
    }

    @EventHandler
    public void cmd(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        if(!p.hasPermission(Files.perms.getString("admin.command-spy-bypass"))){
            String msg = e.getMessage().toLowerCase();
            String prefix = Utils.chat(Files.msgs.getString("prefix"));
            String server = Files.config.getString("server-name");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission(Files.perms.getString("logs.command-spy"))) {
                    if(player.getName() != p.getName()){
                        player.sendMessage(Utils.chat(Files.msgs.getString("logs.command-spy").replace("%prefix%", prefix
                        ).replace("%player%", p.getName()).replace("%command%", msg)).replace("%server%", server));
                    }
                }else{return;}
            }
        }
    }
}
