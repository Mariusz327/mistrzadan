package pl.mario.daily.utils;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String fixColor(String text) {
        text = ChatColor.translateAlternateColorCodes('&', text.replace(">>", "»").replace("<<", "«").replace("{o}", "\u2022\u02d8"));
        return text;
    }

}
