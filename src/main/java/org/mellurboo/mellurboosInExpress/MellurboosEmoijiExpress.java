package org.mellurboo.mellurboosInExpress;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mellurboo.mellurboosInExpress.commands.EmojiExpress;
import org.mellurboo.mellurboosInExpress.commands.TabComplete;
import org.mellurboo.mellurboosInExpress.events.playerChat;

public final class MellurboosEmoijiExpress extends JavaPlugin {
    private EmojiLookup emojiLookup;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        getCommand("emojiexpress").setExecutor(new EmojiExpress(this));
        getCommand("emojiexpress").setTabCompleter(new TabComplete());

        // shorthand declaration
        getCommand("ee").setExecutor(new EmojiExpress(this));
        getCommand("ee").setTabCompleter(new TabComplete());
        this.emojiLookup = new EmojiLookup(this);

        this.getServer().getPluginManager().registerEvents(new playerChat(() -> this.emojiLookup), this);
        Bukkit.getLogger().info("[MellurboosEmojiExpress] Enabled");
    }

    public void reloadEmojiTable() {
        this.reloadConfig();
        this.emojiLookup = new EmojiLookup(this);

        Bukkit.getLogger().info(ChatColor.YELLOW + "[MellurboosEmojiExpress] Emoji table reloaded from config!");
    }

    public EmojiLookup getEmojiLookup() {
        return this.emojiLookup;
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[MellurboosEmojiExpress] Disabled");
    }
}
