package squwid.admin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.builders.Warp;
import squwid.util.WarpsMessageManager;

/**
 * Created by Ben on 4/1/2018.
 */
public class AdminWarp implements AdminCommand {
    
    WarpsMessageManager mm = WarpsMessageManager.getInstance();
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();
    
    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2){
            mm.msg(p, "Usage: /g tp <player> [warpIndex]");
            return;
        }
        String playerName = args[0].toLowerCase();
        String warpIndexString = args[1];
        int warpIndex;
        try{
            warpIndex = Integer.valueOf(warpIndexString);
        }
        catch(Exception e) {
            //e.printStackTrace();
            mm.log("ERROR: " + warpIndexString + " was used instead of an integer");
            mm.msg(p, "Usage: /g tp <player> [warpIndex]");
            return;
        }
        warpIndex -= 1;
        Warp w = sm.getWarp(playerName, warpIndex);
        String warpName = w.getWarpName().toLowerCase();
        //If the warp does not exist
        if (w == null){
            mm.msg(p, "Warp " + warpName + " does not exist");
            return;
        }
        Location loc = new Location(w.getWorld(), w.getX() , w.getY(), w.getZ(), w.getYaw(), w.getPitch());
        try {
            p.teleport(loc);
        }
        catch (Exception e){
            mm.error(p, e.getMessage());
            return;
        }
        mm.msg(p, "Teleported to " + args[0] + "'s warp " + warpName);
        return;
    }

    @Override
    public String name() {
        return "tp";
    }

    @Override
    public String usage() {
        return "<player> [warpIndex]";
    }

    @Override
    public String alias() {
        return "tele";
    }

    @Override
    public String desc() {
        return "Teleport to another players warp";
    }

    @Override
    public String permission() {
        return "cleanwarps.admin";
    }
}
