package pl.mario.daily.manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.mario.daily.DailyTaskPlugin;
import pl.mario.daily.configuration.DailyConfiguration;

import java.util.*;

public class TaskManager {

    private final DailyTaskPlugin plugin;
    private DailyConfiguration dailyConfiguration;
    private final Map<Player, List<String>> assignedTasks = new HashMap<>();
    private final Map<Player, Map<String, Integer>> taskProgress = new HashMap<>();

    public TaskManager(DailyTaskPlugin plugin, DailyConfiguration dailyConfiguration) {
        this.plugin = plugin;
        this.dailyConfiguration = dailyConfiguration;
    }


    public void assignTasks(Player player) {
        List<String> tasks = new ArrayList<>();
        String firstTask = getRandomTask();
        tasks.add(firstTask);
        tasks.add(getDifferentTask(firstTask));

        for (String task : tasks) {
            taskProgress.computeIfAbsent(player, k -> new HashMap<>()).put(task, 0);
        }

        assignedTasks.put(player, tasks);
    }

    public boolean hasTasks(Player player) {
        return assignedTasks.containsKey(player);
    }

    public List<String> getTasks(Player player) {
        return assignedTasks.get(player);
    }

    public Map<String, Integer> getTaskProgress(Player player) {
        return taskProgress.get(player);
    }

    public String completeTask(Player player, String task, boolean click) {
        Map<String, Integer> progressMap = taskProgress.getOrDefault(player, new HashMap<>());
        int progress = progressMap.getOrDefault(task, 0);
        if(!click) {
            progressMap.put(task, progress + 1);
        }
        if(click) {
            if (progress >= dailyConfiguration.getGoals().get(task)) {
                progressMap.remove(task);
                assignedTasks.get(player).remove(task);
                assignTasks(player);
                for (Map.Entry<String, Integer> entry : dailyConfiguration.getPerms().entrySet()) {
                    String permission = entry.getKey();
                    int amount = entry.getValue();
                    if (player.hasPermission(permission)) {
                        ItemStack itemStack = dailyConfiguration.getItem();

                        itemStack.setAmount(amount);
                        player.getInventory().addItem(itemStack);
                        return dailyConfiguration.getTaskDone();


                }


            }
            } else {
                return dailyConfiguration.getTaskError();
            }
        }
        return "";
    }

    private String getRandomTask() {
        Random random = new Random();
        List<String> allTasks = dailyConfiguration.getTaskList();
        return allTasks.get(random.nextInt(allTasks.size()));
    }

    private String getDifferentTask(String firstTask) {
        String differentTask = getRandomTask();
        while (differentTask.equals(firstTask)) {
            differentTask = getRandomTask();
        }
        return differentTask;
    }
}