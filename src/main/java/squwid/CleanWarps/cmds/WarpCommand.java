package squwid.CleanWarps.cmds;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import squwid.CleanWarps.Warp;
import squwid.CleanWarps.WarpsSettingsManager;
import squwid.CleanWarps.util.WarpsMessageManager;

public class WarpCommand implements CommandInterface {

    @Override
    public void onCommand(Player p, String[] args) {
        WarpsSettingsManager sm = WarpsSettingsManager.getInstance();

        if (args.length < 2) {
            WarpsMessageManager.msg(p, "Invalid command");
            return;
        }

        String warpName = Warp.CleanWarpName(args[1]);
    
        Warp warp = sm.getWarp(p, warpName);
        if (warp == null) {
            WarpsMessageManager.msg(p, "Warp " + warpName + " was not found");
            return;
        }

        Location loc = new Location(warp.getWorld(), warp.getX() , warp.getY(), warp.getZ(), warp.getYaw(), warp.getPitch());
        try {
            p.teleport(loc);
        } catch (Exception e) {
            WarpsMessageManager.error(p, e.getMessage());
        }
    }

    @Override
    public String name() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String usage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String desc() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
