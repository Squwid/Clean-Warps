package squwid.admin;

import org.bukkit.entity.Player;
import squwid.builders.Warp;

/**
 * Created by Ben on 4/1/2018.
 */
public class AdminSet implements AdminCommand {
    
    @Override
    public void onCommand(Player p, String[] args) {
        if (args.length < 2){
            mm.msg(p, "Usage: /g set <player> <warp>");
            return;
        }
        String playerName = args[0].toLowerCase();
        String warpName = args[1].toLowerCase();
        //  public Warp(Player p, String warpName,World world, double x, double y, double z, float yaw, float pitch){
        // Warp(String playerName, String warpName, World world, double x, double y, double z, float yaw, float pitch)
        Warp warp = new Warp(playerName, warpName, p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(),p.getLocation().getPitch());
        sm.setWarp(warp);
        mm.msg(p, "Set " + args[1] + " for " + args[0]);
        return;
    }

    @Override
    public String name() {
        return "set";
    }

    @Override
    public String usage() {
        return "<player> <warpName>";
    }

    @Override
    public String alias() {
        return null;
    }

    @Override
    public String desc() {
        return "Set a warp for another player";
    }

    @Override
    public String permission() {
        return "cleanwarps.admin";
    }
}
