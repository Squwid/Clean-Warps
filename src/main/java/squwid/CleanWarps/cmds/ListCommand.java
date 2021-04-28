package squwid.CleanWarps.cmds;

import java.util.List;

import org.bukkit.entity.Player;

import squwid.CleanWarps.WarpsSettingsManager;
import squwid.CleanWarps.util.MessageManager;

public class ListCommand implements CommandInterface {

    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();

    @Override
    public void onCommand(Player p, String[] args) {
        List<String> warps = sm.getPlayerWarpList(p.getUniqueId().toString().replaceAll("[-]", ""));
        
        int warpCount = warps.size();
        MessageManager.msg(p, "Warp Count: " + warpCount);
        MessageManager.msg(p, "Warps: "+warps);
    }

    @Override
    public String name() {
        return "list";
    }

    @Override
    public String usage() {
        return "/go list";
    }

    @Override
    public String desc() {
        return "List warps";
    }
    
}
