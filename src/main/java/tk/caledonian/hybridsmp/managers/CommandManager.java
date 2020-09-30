package tk.caledonian.hybridsmp.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.commands.Cords;
import tk.caledonian.hybridsmp.commands.HybridCore;
import tk.caledonian.hybridsmp.commands.admin.StaffChat;
import tk.caledonian.hybridsmp.commands.admin.StaffList;
import tk.caledonian.hybridsmp.commands.admin.Vanish;
import tk.caledonian.hybridsmp.commands.cheats.gamemodes.Adventure;
import tk.caledonian.hybridsmp.commands.cheats.gamemodes.Creative;
import tk.caledonian.hybridsmp.commands.cheats.gamemodes.Spectator;
import tk.caledonian.hybridsmp.commands.cheats.gamemodes.Survival;
import tk.caledonian.hybridsmp.commands.reactions.*;
import tk.caledonian.hybridsmp.commands.cheats.developer.Points;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {
    private Map<String, CommandHandler> commands = new HashMap<>();
    private JavaPlugin javaPlugin;
    public CommandManager(JavaPlugin javaPlugin){
        this.javaPlugin = javaPlugin;
        initCommands();
    }
    private void initCommands(){
        // Core
        commands.put("hybridcore", new HybridCore(javaPlugin));
        // Admin
        commands.put("staff", new StaffList());
        commands.put("sc", new StaffChat());
        commands.put("v", new Vanish());
        // Gamemodes
        commands.put("a", new Adventure());
        commands.put("c", new Creative());
        commands.put("sp", new Spectator());
        commands.put("s", new Survival());
        // Reactions
        commands.put("bruh", new Bruh());
        commands.put("burn", new BurntKetchup());
        commands.put("doubt", new Doubt());
        commands.put("epic", new EpicGamerMoment());
        commands.put("f", new F());
        commands.put("gg", new GoodGame());
        commands.put("nf", new NoF());
        commands.put("rip", new Rip());
        commands.put("sleep", new Sleep());
        commands.put("swamp", new Swamp());
        commands.put("ugly", new Ugly());
        // Developer Commands
        commands.put("points", new Points(javaPlugin));
        commands.put("cords", new Cords());
        registerCommands();
    }
    private void registerCommands() { commands.forEach((s, c) -> javaPlugin.getCommand(s).setExecutor(this));}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmdname = command.getName();
        CommandHandler commandHandler = commands.get(cmdname);
        if(commandHandler != null) commandHandler.execute(sender, command,args);
        return false;
    }
}
