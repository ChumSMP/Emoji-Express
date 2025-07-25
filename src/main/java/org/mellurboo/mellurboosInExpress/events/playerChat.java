package org.mellurboo.mellurboosInExpress.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mellurboo.mellurboosInExpress.emojiLookup;

public class playerChat implements Listener {
    private final emojiLookup emojiLookup;
    public playerChat(emojiLookup emojiLookup) { this.emojiLookup = emojiLookup; }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String originalMessage = event.getMessage();
        String replacedMessage = emojiLookup.addEmojis(originalMessage);
        event.setMessage(replacedMessage);
    }

}
