package org.mellurboo.mellurboosInExpress.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.mellurboo.mellurboosInExpress.MellurboosEmoijiExpress;
import org.mellurboo.mellurboosInExpress.impl.EmojiList;

public class EmojiExpress implements CommandExecutor {

    private MellurboosEmoijiExpress plugin;
    public EmojiExpress(MellurboosEmoijiExpress plugin) { this.plugin = plugin; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            switch (args[0]){
                case "reload":
                    if (sender.hasPermission("emojiexpress.reload") || (sender instanceof ConsoleCommandSender) || sender.isOp()) {
                        plugin.reloadEmojiTable();
                        sender.sendMessage(ChatColor.GREEN + "Emoji Table Reloaded");
                        return true;
                    }
                    sender.sendMessage(ChatColor.RED + "Insufficient permissions!");
                    return true;
                case "list":
                    if (sender instanceof Player){
                        Player player = (Player) sender;
                        new EmojiList(this.plugin).openEmojiBook(player);
                        return true;
                    }
                    sender.sendMessage(ChatColor.RED + "You Must be a Player to View the emoji list, view or edit the emoji list in the config file!");
                    return true;
            }
        }

        sender.sendMessage(ChatColor.RED + "Usage: /emojiexpress [reload|list]");
        return true;
    }
}
