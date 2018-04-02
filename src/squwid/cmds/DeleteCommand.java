package squwid.cmds;

import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.util.WarpsMessageManager;

import java.util.List;

/**
 * Created by Ben on 4/1/2018.
 */
public class DeleteCommand {

    //TODO: Add logger
    
    WarpsMessageManager mm = WarpsMessageManager.getInstance();
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();
    
    static DeleteCommand instance = new DeleteCommand();
    public static DeleteCommand getInstance() {
        return instance;
    }
    
    public void onCommand(Player p, String[] args) {
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
        List<String> warpList = sm.getPlayerWarpList(p);
        warpList.remove(warpName);
        sm.getData().set(playerName + ".warplist", warpList);
        sm.saveData();
        mm.msg(p, "Warp " + args[1] + " has been deleted");
        return;
    }
}
