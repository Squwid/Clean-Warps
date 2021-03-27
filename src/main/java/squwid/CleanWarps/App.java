package squwid.CleanWarps;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        WarpsSettingsManager.getInstance().setup(this);
        CommandManager.getInstance().setup(this);
        this.getCommand("warp").setExecutor(CommandManager.getInstance());
        this.getCommand("go").setExecutor(CommandManager.getInstance());
    }
    @Override
    public void onDisable() {
        WarpsSettingsManager.getInstance().saveData();
    }
}
