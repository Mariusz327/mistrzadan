package pl.mario.daily.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.mario.daily.manager.TaskManager;
import pl.mario.daily.menu.DailyMenu;

public class TaskListener implements Listener {
    private final TaskManager taskManager;
    private DailyMenu dailyMenu;


    public TaskListener(TaskManager taskManager,DailyMenu dailyMenu) {
        this.taskManager = taskManager;
        this.dailyMenu = dailyMenu;
    }



    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (taskManager.hasTasks(player)) {
            for (String task : taskManager.getTasks(player)) {
                if (task.equals("Wykopane bloki")) {
                    taskManager.completeTask(player, task, false);

                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (taskManager.hasTasks(player)) {
            for (String task : taskManager.getTasks(player)) {
                if (task.equals("Zabójstwa")) {
                    taskManager.completeTask(player, task, false);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!taskManager.hasTasks(player)) {
            taskManager.assignTasks(event.getPlayer());
        }
    }
}
