package pl.mario.daily;

import com.samjakob.spigui.SpiGUI;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mario.daily.commands.DailyCommand;
import pl.mario.daily.configuration.DailyConfiguration;
import pl.mario.daily.listeners.TaskListener;
import pl.mario.daily.manager.TaskManager;
import pl.mario.daily.menu.DailyMenu;

import java.io.File;
import java.util.logging.Level;

public class DailyTaskPlugin extends JavaPlugin {





    @Getter
    private static SpiGUI spiGUI;

    private TaskManager taskManager;

    private DailyConfiguration configuration;


    private DailyMenu dailyMenu;
    @Override
    public void onEnable() {
        spiGUI = new SpiGUI(this);
        initializeConfig();
        taskManager = new TaskManager(this, configuration);
        dailyMenu = new DailyMenu(configuration ,spiGUI, taskManager);
        registerListeners();
        registerCommands();
    }
    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents((Listener) new TaskListener(taskManager, dailyMenu), (Plugin) this);

    }

    public void registerCommands() {
        getCommand("daily").setExecutor((CommandExecutor) new DailyCommand(dailyMenu));
    }
    public void initializeConfig() {

        try {
            this.configuration = ConfigManager.create(DailyConfiguration.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(this.getDataFolder(), "daily.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Error loading config.yml", exception);
            this.getPluginLoader().disablePlugin(this);
            return;
        }
    }
}