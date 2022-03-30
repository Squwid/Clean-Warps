package squwid.CleanWarps;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import squwid.CleanWarps.settings.LocalSettings;
import squwid.CleanWarps.settings.SettingsInterface;

public class CommandManager implements CommandExecutor {
    private static CommandManager instance = new CommandManager();

    private TreeMap<String, CommandInterface> commands = new TreeMap<>();
    private SettingsInterface settings;

    public void setup(Plugin p) {
        this.settings = new LocalSettings();
        this.settings.onInit(p);

        this.commands.put("set", new SetCommand(this.settings));
        this.commands.put("to", new WarpCommand(this.settings));
        this.commands.put("del", new DeleteCommand(this.settings));
        this.commands.put("list", new ListCommand(this.settings));
    }

    public void shutdown() {
        this.settings.onShutdown();
    }

    public static CommandManager getInstance() {return instance;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getServer().getLogger().info("Command sender must be a player");
            return true;
        }

        Player p = (Player) sender;
        
        if (args.length == 0) {
            this.displayHelp(p);
            return true;
        }

        Vector<String> l = new Vector<String>();
        l.addAll(Arrays.asList(args));
        args = l.toArray(new String[0]);        

        String warpCmd = args[0];
        CommandInterface command = commands.get(warpCmd);
        if (command == null) {
            p.sendMessage(ChatColor.GRAY + "command " + warpCmd + " not found");
            return false;
        }

        Bukkit.getServer().getLogger().info(p.getDisplayName() + " issued warp command " + warpCmd);

        command.onCommand(p, args);
        return true;
    }

    private void displayHelp(Player p) {
        p.sendMessage(ChatColor.GOLD + "===== Warp Help Menu =====");
        for (Map.Entry<String, CommandInterface> entry: this.commands.entrySet()) {
            CommandInterface cmd = entry.getValue();
            p.sendMessage(ChatColor.GRAY + cmd.name() + " - " + cmd.desc());
        }
    }
}
