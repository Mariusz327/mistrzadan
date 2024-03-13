package pl.mario.daily.configuration;

import com.samjakob.spigui.item.ItemBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.bukkit.Material.*;
import static org.bukkit.Material.LIGHT_BLUE_STAINED_GLASS_PANE;

@Getter
@Setter

@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class DailyConfiguration extends OkaeriConfig {


private String taskDone = "&aPoprawnie ukończyłeś zadanie";

private String taskError = "&cNie spełniasz wymagań...";

    private List<String> addLore = new ArrayList<>(Arrays.asList(" ",
            "&6&lNagroda:",
            "&8[&7GRACZ&8] &7- &fx5 &5Smoczych Ddłamków",
            "&8[&eVIP&8] &7- &fx6 &5Smoczych Ddłamków",
            "&8[&6SVIP&8] &7- &fx7 &5Smoczych Ddłamków",
            "&8[&9STAR&8] &7- &fx8 &5Smoczych Ddłamków",
            "&8[&dBOSS8] &7- &fx10 &5Smoczych Ddłamków",
            " ",
            "&aKliknij aby odebrać!"
            ));

    private List<String> taskList = new ArrayList<>(Arrays.asList("Zabojstwa", "Wykopane bloki"));


    private ItemStack item = new ItemBuilder(DIRT).name("nazwa").lore("opis").build();

    private
    HashMap<String, Integer> goals = new HashMap<String, Integer>() {{
        put("Zabojstwa", 15);
        put("Wykopane bloki", 5);


    }};

    private
    HashMap<String, Material> logo = new HashMap<String, Material>() {{
        put("Zabojstwa", NETHERITE_SWORD);
        put("Wykopane bloki", DIAMOND_PICKAXE);


    }};

    private
    HashMap<String, Integer> perms = new HashMap<String, Integer>() {{
        put("daily.gracz", 5);
        put("daily.vip", 6);
        put("daily.svip", 7);
        put("daily.star", 8);
        put("daily.boss", 10);



    }};

    private
    HashMap<Integer, Material> gui_view = new HashMap<Integer, Material>() {{
        put(0, LIGHT_BLUE_STAINED_GLASS_PANE);
        put(1, BLUE_STAINED_GLASS_PANE);
        put(2, WHITE_STAINED_GLASS_PANE);
        put(3, WHITE_STAINED_GLASS_PANE);
        put(5, WHITE_STAINED_GLASS_PANE);
        put(6, WHITE_STAINED_GLASS_PANE);
        put(7, BLUE_STAINED_GLASS_PANE);
        put(8, LIGHT_BLUE_STAINED_GLASS_PANE);
        put(9, BLUE_STAINED_GLASS_PANE);
        put(17, BLUE_STAINED_GLASS_PANE);
        put(18, WHITE_STAINED_GLASS_PANE);
        put(26, WHITE_STAINED_GLASS_PANE);
        put(27, BLUE_STAINED_GLASS_PANE);
        put(35, BLUE_STAINED_GLASS_PANE);
        put(36, LIGHT_BLUE_STAINED_GLASS_PANE);
        put(37, BLUE_STAINED_GLASS_PANE);
        put(38, WHITE_STAINED_GLASS_PANE);
        put(39, WHITE_STAINED_GLASS_PANE);
        put(41, WHITE_STAINED_GLASS_PANE);
        put(42, WHITE_STAINED_GLASS_PANE);
        put(43, BLUE_STAINED_GLASS_PANE);
        put(44, LIGHT_BLUE_STAINED_GLASS_PANE);
    }};

    @Exclude
    private Instant start = Instant.now();
}
