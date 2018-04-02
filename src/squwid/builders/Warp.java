package squwid.builders;

import org.bukkit.World;
import org.bukkit.entity.Player;
import squwid.WarpsSettingsManager;

import java.util.UUID;

/**
 * Created by Ben on 3/30/2018.
 */
public class Warp {
    
    private WarpsSettingsManager settings = WarpsSettingsManager.getInstance();
    
    // Values of a warp
    private Player player;
    private UUID uuid;
    private String id;
    private String name;
    private String warpName;
    private double x;
    private double y;
    private double z;
    private World world;
    private float yaw;
    private float pitch;
    
    
    //TODO:::
    
    public Warp(String playerName, String warpName, World world, double x, double y, double z, float yaw, float pitch){
        this.name = playerName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
        this.warpName = warpName;
    }
    
    public Warp(Player p, String warpName, World world, double x, double y, double z, float yaw, float pitch){
        this.player = p;
        this.uuid = p.getUniqueId();
        this.id = uuid.toString();
        this.name = p.getName();
        this.warpName = warpName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }
    
    public Warp(Player player, String warpName){
        this.player = player;
        this.uuid = player.getUniqueId();
        this.id = uuid.toString();
        this.name = player.getName();
        this.warpName = warpName;
        this.x = player.getLocation().getX();
        this.y = player.getLocation().getY();
        this.z = player.getLocation().getZ();
        this.world = player.getLocation().getWorld();
        this.yaw = player.getLocation().getYaw();
        this.pitch = player.getLocation().getPitch();
    }
    
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.id = uuid.toString();
    }
    
    public String getPlayerName() {
        return this.name;
    }
    
    public void setPlayerName(String name) {
        this.name = name;
    }
    
    public String getWarpName(){
        return this.warpName;
    }
    
    public void setWarpName(String name) {
        this.warpName = warpName;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setX(double x){
        this.x = x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setZ(double z) {
        this.z = z;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public void setWorld(World world){
        this.world = world;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPitch(float pitch){
        this.pitch = pitch;
    }
    
}
