package squwid.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;
import squwid.admin.*;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Created by Ben on 4/1/2018.
 */
public class AdminCommandManager implements CommandExecutor{
    
    static AdminCommandManager instance = new AdminCommandManager();
    TreeMap<String, AdminCommand> commands = new TreeMap();
    WarpsMessageManager mm = WarpsMessageManager.getInstance();
    WarpsSettingsManager sm = WarpsSettingsManager.getInstance();

    private AdminCommandManager() {}
    
    public static AdminCommandManager getInstance() { return instance; }
    
    public void setup(){
        this.commands.put("delete", new AdminDelete());
        this.commands.put("list", new AdminList());
        this.commands.put("tp", new AdminWarp());
        this.commands.put("set", new AdminSet());
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)){
            this.mm.log("Unable to use admin commands in console");
            return true;
        }
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("g")){
            if (!p.hasPermission("cleanwarps.admin")){
                this.mm.msg(p, "You are unable to use this command");
                return true;
            }
            if (args.length == 0){
                //this.mm.msg(p, "*** Admin Warp Commands ***", true);
                for (AdminCommand adminCommand : this.commands.values()){
                    if (p.hasPermission(adminCommand.permission())) {
                        this.mm.admin(p, "/" + cmd.getName() + " " + adminCommand.name() + " " + (adminCommand.usage() != null ? adminCommand.usage() : "") + " - " + adminCommand.desc());
                    }
                }
                return true;
            }
            String sub = args[0];
            Vector<String> l = new Vector<String>();
            l.addAll(Arrays.asList(args));
            l.remove(0);
            args = l.toArray(new String[0]);
            AdminCommand ac = this.getCommand(sub);
            if (ac == null) {
                this.mm.msg(p, "/g " + sub + " is not a valid command. Use /g for help");
                return true;
            }
            try{
                ac.onCommand(p, args);
            }
            catch (Exception e){
                e.printStackTrace();
                if (p.hasPermission("cleanwarps.admin")){
                    this.mm.error(p, e.getMessage());
                } else {
                    this.mm.msg(p, "An internal error has occurred");
                }
            }
            return true;
        }
        
        return true;
    }


    private AdminCommand getCommand(String key) {
        for (AdminCommand ac : this.commands.values()) {
            if (ac.alias() == null && ac.name() != null){
                if (ac.name().equalsIgnoreCase(key)){
                    return ac;
                } else {
                    continue;
                }
            }
            if (ac.name().equalsIgnoreCase(key) || ac.alias().equalsIgnoreCase(key)) {
                return ac;
            }
        }
        return null;
    }
}
