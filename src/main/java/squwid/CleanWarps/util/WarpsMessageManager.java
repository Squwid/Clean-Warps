package squwid.CleanWarps.util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WarpsMessageManager {
    public static void msg(Player p, String message){ p.sendMessage(ChatColor.GRAY + message);}
    public static void error(Player p, String message){p.sendMessage(ChatColor.DARK_RED + "ERROR: " + message);}
    public static void broadcast(String message) {Bukkit.getServer().broadcastMessage(message);}
    
    public static void log(String message) {Bukkit.getServer().getLogger().info(message);}
}
