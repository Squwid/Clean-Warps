package squwid.cmds;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.builders.Warp;
import squwid.util.WarpsMessageManager;

/**
 * Created by Ben on 4/1/2018.
 */
public class WarpCommand {

    WarpsMessageManager mm = WarpsMessageManager.getInstance();
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();
    
    
    static WarpCommand instance = new WarpCommand();
    public static WarpCommand getInstance(){
        return instance;
    }
    
    public void onCommand(Player p, String[] args){
        if (args.length == 0){
            mm.msg(p, "Usage: /warp <warp>");
            return;
        }
        String warpName = args[0].toLowerCase();
        String playerName = p.getName().toLowerCase();
        // warp was not found
        Warp warp = sm.getWarp(p, warpName);
        
        if (warp == null ){
            mm.msg(p, "Warp " + args[0] + " does not exist");
            return;
        }
        /*
        World world = Bukkit.getServer().getWorld(sm.getData().getString(playerName + "." + warpName + "." + "world"));
        double x = sm.getData().getDouble(playerName + "." + warpName + ".x");
        double y = sm.getData().getDouble(playerName + "." + warpName + ".y");
        double z = sm.getData().getDouble(playerName + "." + warpName + ".z");
        float yaw = sm.getData().getInt(playerName + "." + warpName + ".yaw");
        float pitch = sm.getData().getInt(playerName + "." + warpName + ".pitch");
        Location loc = new Location(world, x, y, z, yaw, pitch);
        */
        Location loc = new Location(warp.getWorld(), warp.getX() , warp.getY(), warp.getZ(), warp.getYaw(), warp.getPitch());
        try {
            p.teleport(loc);
        }
        catch (Exception e){
            mm.error(p, e.getMessage());
            return;
        }
    }
    
    
    /*
        Location Constructor
        public Location(World world, double x, double y, double z, float yaw, float pitch) {
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.pitch = pitch;
            this.yaw = yaw;
        }
     */
}
