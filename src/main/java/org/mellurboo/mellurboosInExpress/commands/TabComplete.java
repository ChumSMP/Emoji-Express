package org.mellurboo.mellurboosInExpress.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> Suggestions = new ArrayList<>();
        Suggestions.add("list");

        if(commandSender.hasPermission("emojiexpress.reload")) {
            Suggestions.add("reload");
        }

        return Suggestions;
    }
}
