package squwid.CleanWarps.cmds;

import org.bukkit.entity.Player;

import squwid.CleanWarps.Warp;
import squwid.CleanWarps.WarpsSettingsManager;
import squwid.CleanWarps.util.MessageManager;

public class DeleteCommand implements CommandInterface {
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();

    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2) {
            MessageManager.msg(p, "Usage: " + this.usage());
            return;
        }

        String warpName = Warp.CleanWarpName(args[1]);

        Warp warp = this.sm.getWarp(p, warpName);
        if (warp == null) {
            MessageManager.msg(p, "Warp " + args[1] + " does not exist");
            return;
        }

        this.sm.delWarp(p, warpName);
        MessageManager.msg(p, "Warp " + args[1] + " has been deleted");
    }

    @Override
    public String name() {
        return "del";
    }

    @Override
    public String usage() {
        // TODO Auto-generated method stub
        return "/go del <warp>";
    }

    @Override
    public String desc() {
        // TODO Auto-generated method stub
        return "Delete a warp";
    }

}
