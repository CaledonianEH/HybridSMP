package tk.caledonian.hybridsmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.caledonian.hybridsmp.HybridSMP;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker implements Listener {
    private JavaPlugin plugin;
    public UpdateChecker(JavaPlugin plugin) {
        this.plugin = plugin;
    }
        private int resourceId;

    public UpdateChecker(HybridSMP plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

        public void getLatestVersion(Consumer<String> consumer) {//
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                Logger.log(Logger.LogLevel.ERROR, "HybridSMP could not get access updates. Reason: " + exception.getMessage());
            }
        }
    }