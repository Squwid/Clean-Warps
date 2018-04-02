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
    
    public Warp getWarp(String playerName, int warpIndex){
        playerName = playerName.toLowerCase();
        List<String> ls = getPlayerWarpList(playerName);
        String warpName = ls.get(warpIndex).toString().toLowerCase();
        
        Warp w = getWarp(playerName, warpName);
        return w;
    }
    
    public Warp getWarp(String playerName, String warpName){
        playerName = playerName.toLowerCase();
        warpName = warpName.toLowerCase();
        if (this.getData().getString(playerName + '.' + warpName) == null ){
            return null;
        }
        World world = Bukkit.getServer().getWorld(this.getData().getString(playerName + "." + warpName + ".world"));
        double x = this.getData().getDouble(playerName + "." + warpName + ".x");
        double y = this.getData().getDouble(playerName + "." + warpName + ".y");
        double z = this.getData().getDouble(playerName + "." + warpName + ".z");
        float yaw = this.getData().getInt(playerName + "." + warpName + ".yaw");
        float pitch = this.getData().getInt(playerName + "." + warpName + ".pitch");
        Warp w = new Warp(playerName, warpName, world, x, y, z, yaw, pitch);
        //public Warp(String playerName, String warpName, World world, double x, double y, double z, float yaw, float pitch)
        return w;
    }
    
    public Warp getWarp(Player p, String warpName) {
        warpName = warpName.toLowerCase();
        String playerName = p.getName().toLowerCase();
        // if the warp does not exist
        Warp w = getWarp(playerName, warpName);
        if (w == null) {
            return null;
        }
        w.setPlayer(p);
        return w;
    }
    
    public void setWarp(Warp warp){
        String playerName = warp.getPlayerName().toLowerCase();
        String warpName = warp.getWarpName().toLowerCase();
        this.getData().set(playerName + "." + warpName + ".x", Double.valueOf(warp.getX()));
        this.getData().set(playerName + "." + warpName + ".y", Double.valueOf(warp.getY()));
        this.getData().set(playerName + "." + warpName + ".z", Double.valueOf(warp.getZ()));
        this.getData().set(playerName + "." + warpName + ".yaw", Float.valueOf(warp.getYaw()));
        this.getData().set(playerName + "." + warpName + ".pitch", Float.valueOf(warp.getPitch()));
        this.getData().set(playerName + "." + warpName + ".world", warp.getWorld().getName());

        List<String> warpList = this.getData().getStringList(playerName + ".warplist");
        if (!warpList.contains(warpName)){
            warpList.add(warpName);
            this.getData().set(playerName + ".warplist", warpList);
        }
        this.saveData();
    }
    public void gitPrint(){
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Check out github.com/Squwid for source code and usages!");
        return ;
    }
    
    public int CountWarps(Player p){
        String playerName = p.getName().toLowerCase();
        if (!this.getData().contains(playerName)){
            List<String> blankList = new ArrayList<String>();
            this.getData().set(playerName + ".warplist", blankList);
        }
        List<String>warplist = this.getData().getStringList(playerName + ".warplist");
        return warplist.size();
    }
    
    public List<String> getPlayerWarpList(Player p){
        String playerName = p.getName().toLowerCase();
        List<String> ls = this.getData().getStringList(playerName + ".warplist");
        return ls;
    }
    
    public List<String> getPlayerWarpList(String playerName){
        playerName = playerName.toLowerCase();
        List<String> ls = this.getData().getStringList(playerName + ".warplist");
        return ls;
    }
    
    /*
    if (!warpList.contains(warpName)){
            warpList.add(warpName);
            //sm.getData().set(playerName + ".warplist", warpList);
            //sm.setWarp(warp);
        }
     */
    
    /*
        sm.getData().set(playerName, warpName);
        sm.getData().set(playerName + "." + warpName + ".x", Double.valueOf(p.getLocation().getX()));
        sm.getData().set(playerName + "." + warpName + ".y", Double.valueOf(p.getLocation().getY()));
        sm.getData().set(playerName + "." + warpName + ".z", Double.valueOf(p.getLocation().getZ()));
        sm.getData().set(playerName + "." + warpName + ".yaw", Float.valueOf(p.getLocation().getYaw()));
        sm.getData().set(playerName + "." + warpName + ".pitch", Float.valueOf(p.getLocation().getPitch()));
        sm.getData().set(playerName + "." + warpName + ".world", p.getWorld().getName());
     */
    
    
    /*
        CONSTRUCTOR FOR WARP:
        public Warp(Player p, String warpName,World world, double x, double y, double z, float yaw, float pitch)
     */
    
    /*
        World world = Bukkit.getServer().getWorld(sm.getData().getString(playerName + "." + warpName + "." + "world"));
        double x = sm.getData().getDouble(playerName + "." + warpName + ".x");
        double y = sm.getData().getDouble(playerName + "." + warpName + ".y");
        double z = sm.getData().getDouble(playerName + "." + warpName + ".z");
        float yaw = sm.getData().getInt(playerName + "." + warpName + ".yaw");
        float pitch = sm.getData().getInt(playerName + "." + warpName + ".pitch");
     */
    
    public void saveData() {
        try {
            this.warps.save(this.warpfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe("Could not create warps file");
        }
    }
    public PluginDescriptionFile getDescription() {
        return this.p.getDescription();
    }




}
