package squwid.CleanWarps.settings;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import squwid.CleanWarps.Warp;

public interface SettingsInterface {
    public void onInit(Plugin plugin);
    public Warp getWarp(Player p, String warpName);
    public void setWarp(Warp warp);
    public void delWarp(Player p, String warpName);
    public List<String> listWarps(Player p);
    public void onShutdown();
}
