package squwid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import squwid.builders.Warp;


public class WarpsSettingsManager {
    
    static WarpsSettingsManager instance = new WarpsSettingsManager();
    Plugin p;
    FileConfiguration warps;
    File warpfile;
    private static int maxWarps = 15;
    
    
    public static int getMaxWarps(Player p){
        if(p.hasPermission("cleanwarps.admin")){
            return 100;
        }
        for (int i=0; i < 100; i++){
            if(p.hasPermission("cleanwarps.limit."+i)) {
                return i;
            }
        }
        return maxWarps;
    } 
    
    public static int getMaxWarps(){
        return maxWarps;
    }

    public static WarpsSettingsManager getInstance(){
        return instance;
    }

    public void setup(Plugin p){
        this.p = p;
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        this.gitPrint();
        this.warpfile = new File(p.getDataFolder(), "warps.yml");
        this.warps = YamlConfiguration.loadConfiguration(this.warpfile);
        if (!this.warpfile.exists()) {
            try {
                this.warpfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe("Could not create warps file");
            }
        }
    }
    public FileConfiguration getData() {
        return this.warps;
    }
    
    public Warp getWarp(String uuid, int warpIndex){
        String player = uuid.replaceAll("[-]", "");
        List<String> ls = getPlayerWarpList(player);
        String warpName = ls.get(warpIndex).toString().toLowerCase();
        
        Warp w = getWarp(player, warpName);
        return w;
    }
    
    public Warp getWarp(String uuid, String warpName){
        uuid = uuid.replaceAll("[-]", "");
        warpName = warpName.toLowerCase();
        if (this.getData().getString(uuid + '.' + warpName) == null ){
            return null;
        }
        World world = Bukkit.getServer().getWorld(this.getData().getString(uuid + "." + warpName + ".world"));
        double x = this.getData().getDouble(uuid + "." + warpName + ".x");
        double y = this.getData().getDouble(uuid + "." + warpName + ".y");
        double z = this.getData().getDouble(uuid + "." + warpName + ".z");
        float yaw = this.getData().getInt(uuid + "." + warpName + ".yaw");
        float pitch = this.getData().getInt(uuid + "." + warpName + ".pitch");
        Warp w = new Warp("", uuid, warpName, world, x, y, z, yaw, pitch);
        //public Warp(String playerName, String warpName, World world, double x, double y, double z, float yaw, float pitch)
        return w;
    }
    
    public Warp getWarp(Player p, String warpName) {
        warpName = warpName.toLowerCase();
        String player = p.getUniqueId().toString().replaceAll("[-]", "");
        // if the warp does not exist
        Warp w = getWarp(player, warpName);
        if (w == null) {
            return null;
        }
        w.setPlayer(p);
        return w;
    }
    
    public void setWarp(Warp warp){
        //String playerName = warp.getPlayerName().toLowerCase();
        String player = warp.getUUID();
        String warpName = warp.getWarpName().toLowerCase();
        this.getData().set(player + "." + warpName + ".x", Double.valueOf(warp.getX()));
        this.getData().set(player + "." + warpName + ".y", Double.valueOf(warp.getY()));
        this.getData().set(player + "." + warpName + ".z", Double.valueOf(warp.getZ()));
        this.getData().set(player + "." + warpName + ".yaw", Float.valueOf(warp.getYaw()));
        this.getData().set(player + "." + warpName + ".pitch", Float.valueOf(warp.getPitch()));
        this.getData().set(player + "." + warpName + ".world", warp.getWorld().getName());

        List<String> warpList = this.getData().getStringList(player + ".warplist");
        if (!warpList.contains(warpName)){
            warpList.add(warpName);
            this.getData().set(player + ".warplist", warpList);
        }
        this.saveData();
    }
    public void gitPrint(){
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Go to github.com/Squwid for any errors or ideas for future patches");
        return ;
    }
    
    public int CountWarps(Player p){
        String player = p.getUniqueId().toString().replaceAll("[-]", "");
        if (!this.getData().contains(player)){
            List<String> blankList = new ArrayList<String>();
            this.getData().set(player + ".warplist", blankList);
        }
        List<String>warplist = this.getData().getStringList(player + ".warplist");
        return warplist.size();
    }
    
    public List<String> getPlayerWarpList(Player p){
        String player = p.getUniqueId().toString().replaceAll("[-]", "");
        List<String> ls = this.getData().getStringList(player + ".warplist");
        return ls;
    }
    
    public List<String> getPlayerWarpList(String uuid){
        String player = uuid.replaceAll("[-]", "");
        List<String> ls = this.getData().getStringList(player + ".warplist");
        return ls;
    }
    
    public void saveData() {
        try {
            this.warps.save(this.warpfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe("Could not create warps file");
        }
    }
    
    public Player getOnlinePlayer(String pName){
        for (Object en : Bukkit.getServer().getOnlinePlayers()){
            if (!(en instanceof Player)){
                continue;
            }
            if (en != null){
                if(((Player) en).getName().equalsIgnoreCase(pName)){
                    return (Player)en;
                }
            }
        }
        return null;
    }
    
    public PluginDescriptionFile getDescription() {
        return this.p.getDescription();
    }




}
