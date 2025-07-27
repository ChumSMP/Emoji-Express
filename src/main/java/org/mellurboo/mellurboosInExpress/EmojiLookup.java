package org.mellurboo.mellurboosInExpress;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiLookup {
    public final Map<String, String> featuredEmojiTable = new LinkedHashMap<>();
    public final Map<String, String> emojiTable = new LinkedHashMap<>();
    private final Pattern emojiPattern = Pattern.compile(":(\\w+):");

    public EmojiLookup(JavaPlugin plugin){
        FileConfiguration config = plugin.getConfig();  // get the new raw input to map it

        if (config.isConfigurationSection("featuredEmojis")) {
            for (String key : config.getConfigurationSection("featuredEmojis").getKeys(false)) {
                Bukkit.getLogger().info(ChatColor.GOLD + "Featured Emoji Key: " + key);
                String emoji = config.getString("featuredEmojis." + key);
                if (emoji != null){
                    emoji = emoji.replace("\uFE0F", "");
                    featuredEmojiTable.put(key, emoji);
                }
            }
        }

        if (config.isConfigurationSection("emojis")) {
            for (String key : config.getConfigurationSection("emojis").getKeys(false)) {
                Bukkit.getLogger().info(ChatColor.GOLD + "Emoji Key: " + key);
                String emoji = config.getString("emojis." + key);
                if (emoji != null){
                    emoji = emoji.replace("\uFE0F", "");
                    emojiTable.put(key, emoji);
                }
            }
        }
    }

    public String addEmojis(String message) {
        Matcher matcher = emojiPattern.matcher(message);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String name = matcher.group(1);
            String emoji = featuredEmojiTable.getOrDefault(name, emojiTable.getOrDefault(name, matcher.group(0)));
            matcher.appendReplacement(sb, emoji);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    public LinkedHashMap<String, String> getCombinedEmojiTable() {
        LinkedHashMap<String, String> combined = new LinkedHashMap<>();
        combined.putAll(featuredEmojiTable);
        combined.putAll(emojiTable);
        return combined;
    }
}
