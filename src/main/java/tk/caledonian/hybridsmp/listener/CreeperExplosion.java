package tk.caledonian.hybridsmp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import tk.caledonian.hybridsmp.utils.Files;

public class CreeperExplosion implements Listener {

    @EventHandler
    public void explode(EntityExplodeEvent e){
        if(Files.config.getBoolean("other.creeper-explosions.enable") == true){
            if(e.getEntity().getType().equals(EntityType.CREEPER)){
                e.setCancelled(true);
                Location loc = e.getLocation();
                for(Player player : Bukkit.getOnlinePlayers()){
                    if(player.getLocation().equals(loc)){

                    }
                }
            }
        }else{return;}
    }
}
