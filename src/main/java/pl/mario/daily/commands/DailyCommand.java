package pl.mario.daily.commands;

import com.samjakob.spigui.SpiGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mario.daily.configuration.DailyConfiguration;
import pl.mario.daily.manager.TaskManager;
import pl.mario.daily.menu.DailyMenu;

public class DailyCommand implements CommandExecutor {
    private DailyMenu dailyMenu;

    public DailyCommand(DailyMenu dailyMenu) {
        this.dailyMenu = dailyMenu;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
dailyMenu.openGui(p);


        return false;

    }

}
