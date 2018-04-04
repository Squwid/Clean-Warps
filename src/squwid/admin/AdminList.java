package squwid.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Ben on 4/1/2018.
 */
public class AdminList implements AdminCommand {
    
    @Override
    public void onCommand(Player p, String[] args) {
        int size = args.length;
        Bukkit.getServer().getConsoleSender().sendMessage(String.valueOf(size));
        if (args.length == 0) {
            mm.msg(p, "Usage: /g list <player>");
            return;
        }

        String targetPlayerName = args[0].toLowerCase();
        Player targetPlayer = sm.getOnlinePlayer(targetPlayerName);
        if (targetPlayer == null) {
            mm.msg(p, "Could not find somebody online with the name " + args[0]);
            return;
        }
        String targetUUID = targetPlayer.getUniqueId().toString().replaceAll("[-]", "");
        List<String> warpList = sm.getPlayerWarpList(targetUUID);
        mm.msg(p, args[0] + "'s Warps", true);
        for (int i=0; i<warpList.size(); i++){
            mm.msg(p, i+1 + ": " + warpList.get(i));
        }
        return;
    }

    @Override
    public String name() {
        return "list";
    }

    @Override
    public String usage() {
        return "<player>";
    }

    @Override
    public String alias() {
        return null;
    }

    @Override
    public String desc() {
        return "Look at a players warps";
    }

    @Override
    public String permission() {
        return "cleanwarps.admin";
    }
}
