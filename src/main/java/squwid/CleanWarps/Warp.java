package squwid.CleanWarps;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Warp {

    // Values of a warp
    private Player player;
    private String warpName;
    private double x;
    private double y;
    private double z;
    private World world;
    private float yaw;
    private float pitch;

    public Warp(Player p, String warpName) {
        this.player = p;
        this.warpName = warpName.toLowerCase();

        Location loc = this.player.getLocation();

        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
        this.world = loc.getWorld();
        this.yaw = loc.getYaw();
        this.pitch = loc.getPitch();
    }

    public Warp(double x, double y, double z, World world, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static String CleanWarpName(String warpName) {
        return warpName.toLowerCase().replaceAll("[-!@#$%^&*()?|}{,.]", "");
    }

    public Player getPlayer() {return this.player;}
    public String getWarpName() {return this.warpName;}
    public double getX() {return this.x;}
    public double getY() {return this.y;}
    public double getZ() {return this.z;}
    public World getWorld() {return this.world;}
    public float getYaw() {return this.yaw;}
    public float getPitch() {return this.pitch;}
}
