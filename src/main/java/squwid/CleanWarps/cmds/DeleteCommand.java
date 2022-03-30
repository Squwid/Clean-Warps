package squwid.CleanWarps.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import squwid.CleanWarps.Warp;
import squwid.CleanWarps.settings.SettingsInterface;

public class DeleteCommand implements CommandInterface {
    private SettingsInterface settings;
    
    public DeleteCommand(SettingsInterface settings) {
        this.settings = settings;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.GRAY + "Usage: " + this.usage());
            return;
        }

        String warpName = Warp.CleanWarpName(args[1]);

        Warp warp = this.settings.getWarp(p, warpName);
        if (warp == null) {
            p.sendMessage(ChatColor.GRAY + "Warp " + args[1] + " does not exist");
            return;
        }

        this.settings.delWarp(p, warpName);
        p.sendMessage(ChatColor.GRAY + "Warp " + args[1] + " has been deleted");
    }

    @Override
    public String name() {
        return "del";
    }

    @Override
    public String usage() {
        return "/go del <warp>";
    }

    @Override
    public String desc() {
        return "Delete a warp";
    }

}
