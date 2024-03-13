package pl.mario.daily.menu;

import com.samjakob.spigui.SpiGUI;
import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import pl.mario.daily.configuration.DailyConfiguration;
import pl.mario.daily.manager.TaskManager;
import pl.mario.daily.utils.ChatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyMenu {
    private DailyConfiguration dailyConfiguration;
    private SpiGUI spiGUI;
    private TaskManager taskManager;

    public DailyMenu(DailyConfiguration dailyConfiguration, SpiGUI spiGUI, TaskManager taskManager) {
        this.dailyConfiguration = dailyConfiguration;
        this.spiGUI = spiGUI;
        this.taskManager = taskManager;
    }
    public void openGui(Player player) {
        SGMenu settingsGui = spiGUI.create("&6&lEventy", 5);
        dailyConfiguration.getGui_view().forEach((slot, material) -> {
            settingsGui.setButton(slot, new SGButton(new ItemBuilder(material).build()));
        });


        settingsGui.setAutomaticPaginationEnabled(false);

        List<String> tasks = taskManager.getTasks(player);
        if (tasks != null && tasks.size() >= 2) {
            settingsGui.setButton(21, createTaskButton(tasks.get(0), player));
            settingsGui.setButton(23, createTaskButton(tasks.get(1), player));
        }

        player.openInventory(settingsGui.getInventory());


    }
    private SGButton createTaskButton(String task,Player player) {
        int progress = taskManager.getTaskProgress(player).getOrDefault(task, 0);
        List<String> lore = new ArrayList<>();
        lore.add("&7Postęp: &f" + progress + "&8/&f" + dailyConfiguration.getGoals().get(task));
        lore.addAll(dailyConfiguration.getAddLore());
        return new SGButton(
                new ItemBuilder(dailyConfiguration.getLogo().get(task))
                        .name("&6&l" + task)
                        .lore(lore).build()
        ).withListener((InventoryClickEvent event) -> {
           player.sendMessage(ChatUtil.fixColor(taskManager.completeTask(player, task, true)));
        });
    }
}
