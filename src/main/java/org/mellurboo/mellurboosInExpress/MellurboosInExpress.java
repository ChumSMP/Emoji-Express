package org.mellurboo.mellurboosInExpress;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mellurboo.mellurboosInExpress.events.playerChat;

public final class MellurboosInExpress extends JavaPlugin {
    private emojiLookup emojiLookup;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.emojiLookup = new emojiLookup(this);
        this.getServer().getPluginManager().registerEvents(new playerChat(emojiLookup), this);

        Bukkit.getLogger().info("[MellurboosInExpress] Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[MellurboosInExpress] Disabled");
    }
}
