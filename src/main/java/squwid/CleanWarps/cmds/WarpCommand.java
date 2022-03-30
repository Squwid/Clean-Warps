package squwid.CleanWarps.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import squwid.CleanWarps.Warp;
import squwid.CleanWarps.settings.SettingsInterface;

public class WarpCommand implements CommandInterface {
    private SettingsInterface settings;

    public WarpCommand(SettingsInterface settings) {
        this.settings = settings;
    }

    @Override
    public void onCommand(Player p, String[] args) {

        if (args.length < 2) {
            p.sendMessage(ChatColor.GRAY + "Usage: " + this.usage());
            return;
        }

        String warpName = Warp.CleanWarpName(args[1]);
    
        Warp warp = this.settings.getWarp(p, warpName);
        if (warp == null) {
            p.sendMessage(ChatColor.GRAY + "Warp " + warpName + " was not found");
            return;
        }

        Location loc = new Location(warp.getWorld(), warp.getX() , warp.getY(), warp.getZ(), warp.getYaw(), warp.getPitch());
        try {
            p.teleport(loc);
        } catch (Exception e) {
            Bukkit.getServer().getLogger().severe("Error teleporting user " + p.getDisplayName() + ": " + e.getMessage());
        }
    }

    @Override
    public String name() {
        return "to";
    }

    @Override
    public String usage() {
        return "/go to <warp>";
    }

    @Override
    public String desc() {
        return "Teleport to a warp";
    }
    
}
