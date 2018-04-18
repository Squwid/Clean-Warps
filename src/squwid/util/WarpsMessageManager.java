package squwid.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;

/**
 * Created by Ben on 3/30/2018.
 */
public class WarpsMessageManager {
    
    // This max warps variable is only for more simple messaging to the user
    static WarpsMessageManager instance = new WarpsMessageManager();
    public static WarpsMessageManager getInstance() {
        return instance;
    }
    
    
    // Messages
    public void msg(Player p, String message){ p.sendMessage(ChatColor.GRAY + message);}
    public void error(Player p, String message){
        p.sendMessage(ChatColor.DARK_RED + "ERROR: " + message);
    }
    public void msg(Player p, String message, boolean urgent) {
        p.sendMessage(ChatColor.GOLD + message );
    }
    public void msg(Player p, int warps){ p.sendMessage(ChatColor.GOLD + "You have " + ChatColor.GRAY+ warps + ChatColor.GOLD + "/" + WarpsSettingsManager.getMaxWarps(p) + " warps currently set"); }
    public void log(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }
    public void admin(Player p, String message){
        p.sendMessage(ChatColor.RED + message);
    }
    public void broadcast(String message) {
        Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + message);
    }
}
