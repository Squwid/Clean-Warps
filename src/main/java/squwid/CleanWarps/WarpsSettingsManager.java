package squwid.CleanWarps;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import squwid.CleanWarps.util.MessageManager;

public class WarpsSettingsManager {
    private static WarpsSettingsManager instance = new WarpsSettingsManager();

    private Plugin p;
    private FileConfiguration warps;
    private File warpFile;

    public void setup(Plugin p) {
        this.p = p;
        if (!this.p.getDataFolder().exists()) {
            this.p.getDataFolder().mkdir();
        }

        this.warpFile = new File(p.getDataFolder(), "warps.yaml");
        this.warps = YamlConfiguration.loadConfiguration(this.warpFile);
        if (!this.warpFile.exists()) {
            try {
                this.warpFile.createNewFile();
                Bukkit.getServer().getLogger().info("Created new warps file");
            }
            catch (Exception e) {
                Bukkit.getServer().getLogger().severe("Could not create warps file");
            }
        }
    }

    public static WarpsSettingsManager getInstance() {
        return instance;
    }

    public Warp getWarp(Player p, String warpName) {
        warpName = warpName.toLowerCase();
        String uuid = p.getUniqueId().toString().replaceAll("[-]", "");
        
        if (this.warps.getString(uuid+'.'+warpName) == null) {
            return null;
        }
        double x = this.warps.getDouble(uuid + "." + warpName + ".x");
        double y = this.warps.getDouble(uuid + "." + warpName + ".y");
        double z = this.warps.getDouble(uuid + "." + warpName + ".z");
        World world = Bukkit.getServer().getWorld(this.warps.getString(uuid + "." + warpName + ".world"));
        float yaw = this.warps.getInt(uuid + "." + warpName + ".yaw");
        float pitch = this.warps.getInt(uuid + "." + warpName + ".pitch");
        return new Warp(x, y, z, world, yaw, pitch);
    }

    public void setWarp(Warp warp) {
        String uuid = warp.getPlayer().getUniqueId().toString().replaceAll("[-]", "");
        this.warps.set(uuid + "." + warp.getWarpName() + ".x", Double.valueOf(warp.getX()));
        this.warps.set(uuid + "." + warp.getWarpName() + ".y", Double.valueOf(warp.getY()));
        this.warps.set(uuid + "." + warp.getWarpName() + ".z", Double.valueOf(warp.getZ()));
        this.warps.set(uuid + "." + warp.getWarpName() + ".yaw", Float.valueOf(warp.getYaw()));
        this.warps.set(uuid + "." + warp.getWarpName() + ".pitch", Float.valueOf(warp.getPitch()));
        this.warps.set(uuid + "." + warp.getWarpName() + ".world", warp.getWorld().getName());

        List<String> warpList = this.warps.getStringList(uuid + ".warplist");
        if (!warpList.contains(warp.getWarpName())){
            warpList.add(warp.getWarpName());
            this.warps.set(uuid + ".warplist", warpList);
        }
        this.saveData();
    }

    public void delWarp(Player p, String warpName) {
        String uuid = p.getUniqueId().toString().replaceAll("[-]", "");
        this.warps.set(uuid + "." + warpName, null);
        
        List<String> warpList = this.warps.getStringList(uuid + ".warplist");
        warpList.remove(warpName);
        this.warps.set(uuid + ".warplist", warpList);
        this.saveData();
    }

    public List<String> getPlayerWarpList(String uuid) {
        uuid = uuid.replaceAll("[-]", "");
        List<String> ls = this.warps.getStringList(uuid + ".warplist");
        return ls;
    }

    public void saveData() {
        try {
            this.warps.save(this.warpFile);
            MessageManager.log("Saved warps file");
        }
        catch (Exception e) {
            Bukkit.getServer().getLogger().severe("Could not save warps file");
        }
    }
}
