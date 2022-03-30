package squwid.CleanWarps.cmds;

import org.bukkit.entity.Player;

import squwid.CleanWarps.Warp;
import squwid.CleanWarps.WarpsSettingsManager;
import squwid.CleanWarps.util.MessageManager;

public class SetCommand implements CommandInterface {
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();

    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2) {
            MessageManager.msg(p, "Usage: " + this.usage());
            return;
        }

        // TODO: Check warps count
        String warpName = Warp.CleanWarpName(args[1]);
        
        Warp warp = new Warp(p, warpName);
        sm.setWarp(warp);
        MessageManager.msg(p, "Warp " + warpName + " has been set");
    }

    @Override
    public String name() {
        return "set";
    }

    @Override
    public String usage() {
        return "/go set <warp>";
    }

    @Override
    public String desc() {
        return "Set a warp";
    }
}