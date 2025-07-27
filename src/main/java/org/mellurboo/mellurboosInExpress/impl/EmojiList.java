package org.mellurboo.mellurboosInExpress.impl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mellurboo.mellurboosInExpress.MellurboosEmoijiExpress;

import java.util.*;

public class EmojiList {
    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
    BookMeta meta = (BookMeta) book.getItemMeta();

    private MellurboosEmoijiExpress plugin;
    public EmojiList(MellurboosEmoijiExpress plugin) { this.plugin = plugin; }

    public void openEmojiBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        meta.setTitle("Emoji List");
        meta.setAuthor("Mellurboo");

        LinkedHashMap<String, String> combined = plugin.getEmojiLookup().getCombinedEmojiTable();

        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, String> entry : combined.entrySet()) {
            String emoji = ChatColor.stripColor(entry.getValue());
            lines.add(ChatColor.DARK_GRAY + ":" + entry.getKey() + ": " + emoji);
        }

        List<String> pages = new ArrayList<>();
        StringBuilder currentPage = new StringBuilder();

        for (String line : lines) {
            if (currentPage.length() + line.length() >= 250) {
                pages.add(currentPage.toString());
                currentPage = new StringBuilder();
            }
            currentPage.append(line).append("\n");
        }

        if (currentPage.length() > 0) {
            pages.add(currentPage.toString());
        }

        meta.setPages(pages);
        book.setItemMeta(meta);

        player.openBook(book);
    }
}
