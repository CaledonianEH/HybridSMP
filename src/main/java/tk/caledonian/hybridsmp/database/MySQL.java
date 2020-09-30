package tk.caledonian.hybridsmp.database;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.utils.Files;
import tk.caledonian.hybridsmp.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class MySQL {

    private String host = Files.config.getString("mysql.login.host");
    private String database = Files.config.getString("mysql.login.database");
    private String username = Files.config.getString("mysql.login.username");
    private String password = Files.config.getString("mysql.login.password");
    private Integer port = 3306;

    private Connection connection;

    public boolean isConnected(){return (connection == null ? false : true);}

    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()){ connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useSSL=false", this.username, this.password); }
    }
    public void disconnect(){
        if(isConnected()){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public Connection getConnection(){
        return connection;
    }

    public static void printSQL(){
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&a-------------------------------------------"));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&a[Apotheosis] Successfully connected to SQL"));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&aHost: &f" + Files.config.getString("host")));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&aPort: &f" + Files.config.getInt("port")));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&aDatabase: &f" + Files.config.getString("database")));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&aUsername: &f" + Files.config.getString("username")));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&aPassword: &f**********"));
        Bukkit.getConsoleSender().sendMessage(Utils.chat(" "));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("&7To disable this, set 'print-sql' to false in config.yml"));
        Bukkit.getConsoleSender().sendMessage(Utils.chat("------------------------------------------"));

    }
    public void createPlayer(Player p){
        try{
            UUID uuid = p.getUniqueId();
                PreparedStatement ps2 = connection.prepareStatement("INSERT INFO caldabest (NAME,UUID,CALPOINTS) VALUES (?,?,?)");
                ps2.setString(1, p.getName());
                ps2.setString(2, uuid.toString());
                ps2.setInt(3, 1);
                ps2.executeUpdate();

                return;

        }catch (SQLException e){e.printStackTrace();}
    }
}
