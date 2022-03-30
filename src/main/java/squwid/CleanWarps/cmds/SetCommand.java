package squwid.CleanWarps.cmds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import squwid.CleanWarps.Warp;
import squwid.CleanWarps.settings.SettingsInterface;

public class SetCommand implements CommandInterface {
    private SettingsInterface settings;

    public SetCommand(SettingsInterface settings) {
        this.settings = settings;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.GRAY + "Usage: " + this.usage());
            return;
        }

        // TODO: Check warps count
        String warpName = Warp.CleanWarpName(args[1]);
        
        Warp warp = new Warp(p, warpName);
        this.settings.setWarp(warp);
        p.sendMessage(ChatColor.GRAY + "Warp " + warpName + " has been set");
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
