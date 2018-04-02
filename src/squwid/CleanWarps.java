package squwid;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import squwid.cmds.*;
import squwid.util.AdminCommandManager;
import squwid.util.WarpsMessageManager;

/**
 * Created by Ben on 4/2/2018.
 */
public class CleanWarps extends JavaPlugin {

    public void onEnable() {
        WarpsSettingsManager.getInstance().setup(this);
        AdminCommandManager.getInstance().setup();
        this.getCommand("g").setExecutor(AdminCommandManager.getInstance());
        //CommandManager.getInstance().setup();
    }

    public void onDisable() {
        WarpsSettingsManager.getInstance().saveData();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        WarpsMessageManager mm = WarpsMessageManager.getInstance();
        if (!(sender instanceof Player)) {
            mm.log("Unable to use warps in console");
            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("warp") || cmd.getName().equalsIgnoreCase("go")){
            if (args.length==0) {
                HelpCommand h = HelpCommand.getInstance();
                h.onCommand(p);
                return true;
            }
            if(args.length == 1){
                if (args[0].equalsIgnoreCase("list")){
                    ListCommand lw = ListCommand.getInstance();
                    lw.onCommand(p, args);
                    return true;
                }

                if (args[0].equalsIgnoreCase("help")){
                    HelpCommand h = HelpCommand.getInstance();
                    h.onCommand(p);
                    return true;
                }

                WarpCommand wc = WarpCommand.getInstance();
                wc.onCommand(p, args);
                return true;
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")){
                    SetCommand set = SetCommand.getInstance();
                    set.onCommand(p, args);
                    return true;
                }

                if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("delete")){
                    DeleteCommand del = DeleteCommand.getInstance();
                    del.onCommand(p, args);
                    return true;
                }
                invalidCommand(p, args);
                return true;
            }
            invalidCommand(p, args);
            return true;
        }

        return true;
    }

    public void invalidCommand(Player p, String[] args){
        WarpsMessageManager.getInstance().msg(p, "/warp " + args[0] + " is an invalid command. Use /warp help for help");
        return;
    }

}
