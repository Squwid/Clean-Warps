package squwid.cmds;

import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.builders.Warp;
import squwid.util.WarpsMessageManager;

/**
 * Created by Ben on 3/31/2018.
 */
public class SetCommand {

    WarpsMessageManager mm = WarpsMessageManager.getInstance();
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();
    
    static SetCommand instance = new SetCommand();
    public static SetCommand getInstance(){
        return instance;
    }
    
    //TODO: Check length and bad chars in warpname
    public void onCommand(Player p, String[] args) {
        //String playerName = p.getName().toLowerCase();
        // If args are blank
        if (args.length == 1){
            mm.msg(p, "Usage: /warp set <warp>");
            return;
        }
        int warps = sm.CountWarps(p);
        
        // If player is already at max warps
        if (warps >= sm.getMaxWarps() && !p.hasPermission("warp.admin")){
            mm.msg(p, "You are already at max warps, warp was not set");
            return;
        }
        
        String warpName = args[1].toLowerCase().replaceAll("[-!@#$%^&*()?|}{,.]", "");
        //  public Warp(Player p, String warpName,World world, double x, double y, double z, float yaw, float pitch){
        Warp warp = new Warp(p, warpName);
        /*
        sm.getData().set(playerName, warpName);
        sm.getData().set(playerName + "." + warpName + ".x", Double.valueOf(p.getLocation().getX()));
        sm.getData().set(playerName + "." + warpName + ".y", Double.valueOf(p.getLocation().getY()));
        sm.getData().set(playerName + "." + warpName + ".z", Double.valueOf(p.getLocation().getZ()));
        sm.getData().set(playerName + "." + warpName + ".yaw", Float.valueOf(p.getLocation().getYaw()));
        sm.getData().set(playerName + "." + warpName + ".pitch", Float.valueOf(p.getLocation().getPitch()));
        sm.getData().set(playerName + "." + warpName + ".world", p.getWorld().getName());
        
        List<String> warpList = sm.getData().getStringList(playerName + ".warplist");
        if (!warpList.contains(warpName)){
            warpList.add(warpName);
            sm.getData().set(playerName + ".warplist", warpList);
        }
        */
        sm.setWarp(warp);
        sm.saveData();
        mm.msg(p, "Warp " + args[1] + " has been set");
    }
}
