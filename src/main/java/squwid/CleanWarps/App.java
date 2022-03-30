package squwid.CleanWarps;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandManager.getInstance().setup(this);
        this.getCommand("go").setExecutor(CommandManager.getInstance());
    }
    @Override
    public void onDisable() {
        CommandManager.getInstance().shutdown();
    }
}
