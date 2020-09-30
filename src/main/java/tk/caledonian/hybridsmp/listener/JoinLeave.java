package tk.caledonian.hybridsmp.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tk.caledonian.hybridsmp.HybridSMP;
import tk.caledonian.hybridsmp.commands.admin.Vanish;
import tk.caledonian.hybridsmp.database.MySQL;
import tk.caledonian.hybridsmp.database.SQLGetter;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Logger;
import tk.caledonian.hybridsmp.utils.Utils;

import java.util.List;
import java.util.Random;

public class JoinLeave implements Listener {
    private SQLGetter data;
    String prefix = Utils.chat(Files.msgs.getString("prefix"));

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        List<String> list = Files.msgs.getStringList("join-leave.join");
        if(p.hasPermission(Files.perms.getString("other.join"))){
            e.setJoinMessage(Utils.chat(list.get(new Random().nextInt(list.size()))).replace("%prefix%", prefix).replace("%player%", p.getName()));
        }else{return;}
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        List<String> list = Files.msgs.getStringList("join-leave.leave");
        if(p.hasPermission(Files.perms.getString("other.leave"))){
            e.setQuitMessage(Utils.chat(list.get(new Random().nextInt(list.size()))).replace("%prefix%", prefix).replace("%player%", p.getName()));
        }else{return;}
    }
}
