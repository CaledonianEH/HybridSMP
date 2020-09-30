package tk.caledonian.hybridsmp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Utils;

import java.util.HashMap;

public class HeightLimit implements Listener {
    HashMap<String, Long> moveTimes = new HashMap<String, Long>();
    HashMap<String, Long> staffLog = new HashMap<String, Long>();

    private JavaPlugin plugin;
    public HeightLimit(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e){
        if(Files.config.getBoolean("patches.height-limit.enable") == true){
            final Player p = e.getPlayer();
            if(!p.hasPermission(Files.config.getString("patches.height-limit.bypass"))){
                String prefix = Files.msgs.getString("prefix");
                Integer h = Files.config.getInt("patches.height-limit.max-height");
                if(e.getTo().getY() > h){
                    // Hashmap message
                    if(moveTimes.containsKey(p.getName())){
                        long oldTime = moveTimes.get(p.getName());
                        long newTime = System.currentTimeMillis();
                        if((newTime - oldTime) > (Files.config.getInt("patches.height-limit.msg-cooldown") * 1000)){
                            moveTimes.remove(p.getName());
                        }
                    }else{
                        moveTimes.put(p.getName(), System.currentTimeMillis());
                        p.sendMessage(Utils.chat(Files.config.getString("patches.height-limit.deny").replace("%prefix%", prefix
                        ).replace("%height%", h.toString())));
                    }

                    if(staffLog.containsKey(p.getName())){
                        long oldTime = staffLog.get(p.getName());
                        long newTime = System.currentTimeMillis();
                        if((newTime - oldTime) > (Files.config.getInt("log-cooldown") * 1000)){
                            staffLog.remove(p.getName());
                        }
                    }else{
                        staffLog.put(p.getName(), System.currentTimeMillis());
                        p.sendMessage(Utils.chat(Files.msgs.getString("logs.height").replace("%prefix%", prefix
                        ).replace("%height%", h.toString()).replace("%player%", p.getName())));
                    }

                    e.setCancelled(true);
                }
            }else{return;}
        }else{return;}
    }
}
