package org.mellurboo.mellurboosInExpress;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class emojiLookup {
    private final Map<String, String> emojiTable = new HashMap<>();
    private final Pattern emojiPattern = Pattern.compile(":(\\w+):");

    public emojiLookup(JavaPlugin plugin){
        FileConfiguration config = plugin.getConfig();
        for (String key : config.getConfigurationSection("emojis").getKeys(false)) {
            String emoji = config.getString("emojis." + key);
            if (emoji != null){
                emoji = emoji.replace("\uFE0F", ""); // Strip Variation shit
                emojiTable.put(key, emoji);
            }
        }
    }

    public String addEmojis(String message) {
        Matcher matcher = emojiPattern.matcher(message);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String name = matcher.group(1);
            String emoji = emojiTable.getOrDefault(name, matcher.group(0));

            matcher.appendReplacement(sb, emoji);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
