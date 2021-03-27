package squwid.CleanWarps;

import java.util.TreeMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import squwid.CleanWarps.cmds.CommandInterface;
import squwid.CleanWarps.cmds.SetCommand;
import squwid.CleanWarps.util.WarpsMessageManager;

public class CommandManager implements CommandExecutor {
    private static CommandManager instance = new CommandManager();
    private TreeMap<String, CommandInterface> commands = new TreeMap<>();
    private Plugin p;

    public void setup(Plugin p) {
        this.p = p;

        this.commands.put("set", new SetCommand());
    }

    public static CommandManager getInstance() {return instance;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            WarpsMessageManager.log("Command sender must be a player");
            return true;
        }

        Player p = (Player) sender;
        
        // TODO: send back helps
        if (args.length == 0) {
            WarpsMessageManager.msg(p, "Invalid command");
            return false;
        }

        String warpCmd = args[0];
        CommandInterface command = commands.get(warpCmd);
        if (command == null) {
            WarpsMessageManager.msg(p, "command not found");
            return false;
        }

        WarpsMessageManager.msg(p, command.desc());
        return true;
    }
}
