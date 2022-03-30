package squwid.CleanWarps.settings;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import squwid.CleanWarps.Warp;

public interface SettingsInterface {
    public void Init(Plugin plugin);
    public Warp getWarp(Player p, String warpName);
    public void setWarp(Warp warp);
    public void delWarp(Player p, String warpName);
    public List<Warp> listWarps(Player p);
}
