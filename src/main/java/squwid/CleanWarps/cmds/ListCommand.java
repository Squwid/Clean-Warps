package squwid.CleanWarps.cmds;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import squwid.CleanWarps.settings.SettingsInterface;

public class ListCommand implements CommandInterface {
    private SettingsInterface settings;
    
    public ListCommand(SettingsInterface settings) {
        this.settings = settings;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        List<String> warps = this.settings.listWarps(p);
        
        int warpCount = warps.size();

        p.sendMessage(ChatColor.GRAY + "Warp Count: " + warpCount);
        p.sendMessage(ChatColor.GRAY + "Warps: " + warps);
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
