package squwid.CleanWarps.cmds;

import org.bukkit.entity.Player;
import squwid.CleanWarps.util.WarpsMessageManager;

public class SetCommand implements CommandInterface {
    @Override
    public void onCommand(Player p, String[] args) {
        WarpsMessageManager.msg(p, "Ran command!");
    }

    @Override
    public String name() {
        return "set";
    }

    @Override
    public String usage() {
        return "this is the set command";
    }

    @Override
    public String desc() {
        return "set warps";
    }
}
