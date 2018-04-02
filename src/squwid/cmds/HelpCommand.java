package squwid.cmds;

import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.util.WarpsMessageManager;

/**
 * Created by Ben on 4/1/2018.
 */
public class HelpCommand {

    WarpsMessageManager mm = WarpsMessageManager.getInstance();
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();
    
    static HelpCommand instance = new HelpCommand();
    public static HelpCommand getInstance() {
        return instance;
    }
    
    public void onCommand(Player p) {
        int warps = sm.CountWarps(p);
        
        mm.msg(p, "*** Warp Commands ***", true);
        mm.msg(p, warps);
        mm.msg(p, "/go <warp> - Teleport to a warp");
        mm.msg(p, "/go set <warp> - Set a warp");
        mm.msg(p, "/go del <warp> - Delete a warp");
        mm.msg(p, "/go list - List your warps");
        if (p.hasPermission("warp.admin")){
            mm.admin(p, "*** Admin Warp Commands ***");
            p.performCommand("g");
        }
    }
}
