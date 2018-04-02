package squwid.admin;

import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.util.WarpsMessageManager;

/**
 * Created by Ben on 4/1/2018.
 */
public interface AdminCommand {
    
    public static final WarpsMessageManager mm = WarpsMessageManager.getInstance();
    public static final WarpsSettingsManager sm = WarpsSettingsManager.getInstance();
    
    public void onCommand(Player var1, String[] var2);
    public String name();
    public String usage();
    public String alias();
    public String desc();
    
    /*
        ALl of the permissions:
            Admin: warp.admin
            Mod+ : warp.modplus
     */
    
    public String permission();
    
}
