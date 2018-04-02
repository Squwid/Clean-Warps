package squwid.admin;

import org.bukkit.entity.Player;
import squwid.builders.Warp;

import java.util.List;

/**
 * Created by Ben on 4/1/2018.
 */
public class AdminDelete implements AdminCommand {
    
    
    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2){
            mm.msg(p, "Usage: /g del <player> [warpIndex]");
            return;
        }
        String playerName = args[0].toLowerCase();
        String warpIndexString = args[1];
        int warpIndex;
        try{
            warpIndex = Integer.valueOf(warpIndexString);
        }
        catch(Exception e) {
            e.printStackTrace();
            mm.msg(p, "Usage: /g del <player> [warpIndex]");
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
        List<String> warpList = sm.getData().getStringList(playerName + ".warplist");
        sm.getData().set(playerName + "." + warpName, null);
        warpList.remove(warpName);
        sm.getData().set(playerName + ".warplist", warpList);
        sm.saveData();
        mm.msg(p, args[0] + "'s warp " + warpName + " was successfully deleted");
        return;
    }
    
    
    
    /*
        String playerName = p.getName().toLowerCase();
        // If a player does not give a warp to delete 
        if (args.length == 1){
            mm.msg(p, "Usage: /warp del <warpName>");
            return;
        }
        
        String warpName = args[1].toLowerCase();
        // If the warp doesn't exist
        if (sm.getWarp(p, warpName) == null ){
            mm.msg(p, "Warp " + args[1] + " does not exist");
            return;
        }
        sm.getData().set(playerName + "." + warpName, null);
        List<String> warpList = sm.getData().getStringList(playerName + ".warplist");
        warpList.remove(warpName);
        sm.getData().set(playerName + ".warplist", warpList);
        sm.saveData();
        mm.msg(p, "Warp " + args[1] + " has been deleted");
        return;
     */
    

    @Override
    public String name() {
        return "del";
    }

    @Override
    public String usage() {
        return "<player> [warpIndex]";
    }

    @Override
    public String alias() {
        return "delete";
    }

    @Override
    public String desc() {
        return "Delete a players warp";
    }

    @Override
    public String permission() {
        return "cleanwarps.admin";
    }
}
