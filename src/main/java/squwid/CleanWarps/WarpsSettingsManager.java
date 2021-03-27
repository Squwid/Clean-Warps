package squwid.CleanWarps;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class WarpsSettingsManager {
    private static WarpsSettingsManager instance = new WarpsSettingsManager();

    private Plugin p;
    private FileConfiguration warps;
    private File warpFile;
    private static int maxWarps = 15;

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
}
