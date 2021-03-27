package squwid.CleanWarps.cmds;

import org.bukkit.entity.Player;

public interface CommandInterface {
    public void onCommand(Player p, String[] args);
    public String name();
    public String usage();
    public String desc();
    
}
