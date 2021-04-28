package squwid.CleanWarps;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import squwid.CleanWarps.cmds.CommandInterface;
import squwid.CleanWarps.cmds.DeleteCommand;
import squwid.CleanWarps.cmds.ListCommand;
import squwid.CleanWarps.cmds.SetCommand;
import squwid.CleanWarps.cmds.WarpCommand;
import squwid.CleanWarps.util.MessageManager;

public class CommandManager implements CommandExecutor {
    private static CommandManager instance = new CommandManager();
    private TreeMap<String, CommandInterface> commands = new TreeMap<>();
    private Plugin plugin;

    public void setup(Plugin p) {
        this.plugin = p;

        this.commands.put("set", new SetCommand());
        this.commands.put("to", new WarpCommand());
        this.commands.put("del", new DeleteCommand());
        this.commands.put("list", new ListCommand());
    }

    public static CommandManager getInstance() {return instance;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.log("Command sender must be player");
            return true;
        }

        Player p = (Player) sender;
        
        if (args.length == 0) {
            MessageManager.msg(p, "HELP MENU");
            this.displayHelp(p);
            return true;
        }
        Vector<String> l = new Vector<String>();
        l.addAll(Arrays.asList(args));
        // l.remove(0);
        args = l.toArray(new String[0]);

        

        String warpCmd = args[0];
        MessageManager.log("Warp Command " + warpCmd);
        CommandInterface command = commands.get(warpCmd);
        if (command == null) {
            MessageManager.msg(p, "command not found");
            // TODO: Should this return false
            return true;
        }

        command.onCommand(p, args);
        return true;
    }

    private void displayHelp(Player p) {
        for (Map.Entry<String, CommandInterface> entry: this.commands.entrySet()) {
            CommandInterface cmd = entry.getValue();
            MessageManager.msg(p, "" + cmd.name() + " - " + cmd.desc());
        }
    }
}
