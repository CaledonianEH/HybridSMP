package tk.caledonian.hybridsmp.database;

import org.bukkit.entity.Player;
import tk.caledonian.hybridsmp.HybridSMP;
import tk.caledonian.hybridsmp.utils.Logger;

import java.sql.*;
import java.util.UUID;

public class SQLGetter {
    private HybridSMP plugin;
    private Connection connection;
    private MySQL SQL;
    public SQLGetter(HybridSMP plugin){
        this.plugin = plugin;
    }
    // Table Creation
    public void createTable(){
        PreparedStatement ps;
        try{
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS caldabest "
            + "(NAME VARCHAR(100),UUID VARCHAR(100),CALPOINTS INT(100),PRIMARY KEY (UUID))");
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void createPlayer(Player p){
        try{
            UUID uuid = p.getUniqueId();
            if(!exists(p.getUniqueId())){
             PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INFO caldabest (NAME,UUID,CALPOINTS) VALUES (?,?,?)");
             ps2.setString(1, p.getName());
             ps2.setString(2, uuid.toString());
             ps2.setInt(3, 1);
             ps2.executeUpdate();

             return;
            }

        }catch (SQLException e){e.printStackTrace();}
    }
    public void workffs(){
        try{
            Logger.log(Logger.LogLevel.ERROR, "Smaller ass error");
            Statement statement = connection.createStatement();
            PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INFO caldabest (UUID) VALUES ('1234')");
            Logger.log(Logger.LogLevel.ERROR, "Smallest ass error");
            ps2.executeUpdate();

            return;
        }catch (SQLException e){e.printStackTrace();
            Logger.log(Logger.LogLevel.ERROR, "Big. Ass. Error");}
    }

    public boolean exists(UUID uuid){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM caldabest WHERE UUID=?");

            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void addPoints(UUID uuid, int points){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE caldabest SET POINTS=? WHERE UUID=?");
            ps.setInt(1, (getPoints(uuid) + points));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void remPoints(UUID uuid, int points){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE caldabest SET POINTS=? WHERE UUID=?");
            ps.setInt(1, (getPoints(uuid) - points));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getPoints(UUID uuid){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT POINTS FROM caldabest WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if(rs.next())
                points = rs.getInt("POINTS");
            return points;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void emptyTable(){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("TRUNCATE caldabest");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void remove(UUID uuid){
        try{
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("DELETE FROM caldabest WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
