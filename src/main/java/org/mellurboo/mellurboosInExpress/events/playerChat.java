package org.mellurboo.mellurboosInExpress.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mellurboo.mellurboosInExpress.EmojiLookup;

import java.util.function.Supplier;

public class playerChat implements Listener {
    private final Supplier<EmojiLookup> emojiLookupSupplier;

    public playerChat(Supplier<EmojiLookup> emojiLookupSupplier) {
        this.emojiLookupSupplier = emojiLookupSupplier;
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String originalMessage = event.getMessage();
        EmojiLookup lookup = emojiLookupSupplier.get();  // Always fetch the current one
        String replacedMessage = lookup.addEmojis(originalMessage);
        event.setMessage(replacedMessage);
    }

}
