package tk.caledonian.hybridsmp.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String format(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String chat(String msg) {
        final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        if (Bukkit.getVersion().contains("1.16.1")) {
            Matcher match = pattern.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', msg);
    }



    // Other Strings
    public static String ver = "0.1.2";
}