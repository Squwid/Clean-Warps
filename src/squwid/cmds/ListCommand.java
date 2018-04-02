package squwid.cmds;

import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.util.WarpsMessageManager;

/**
 * Created by Ben on 4/1/2018.
 */
public class ListCommand {
    
    //TODO: Logger

    WarpsMessageManager mm = WarpsMessageManager.getInstance();
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();
    
    static ListCommand instance = new ListCommand();
    public static ListCommand getInstance(){
        return instance;
    }
    
    public void onCommand(Player p, String[] args) {
        int warps = sm.CountWarps(p);
        mm.msg(p, warps);
        mm.msg(p, "Warps: " + sm.getPlayerWarpList(p));
        return;
    }
}
