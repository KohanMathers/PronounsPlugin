package com.quietterminal.pronounsplugin;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class PronounsExpansion extends PlaceholderExpansion {
    private final PronounsPlugin plugin;

    public PronounsExpansion(PronounsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pronouns";
    }

    @Override
    public @NotNull String getAuthor() {
        return "quietterminal";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) return "";

        Map<UUID, String> data = plugin.getPronounsData();
        String stored = data.get(player.getUniqueId());
        if (stored == null) stored = "";

        String colorName = "GRAY";
        String pronouns = "";
        if (!stored.isEmpty() && stored.contains(":")) {
            String[] parts = stored.split(":", 2);
            colorName = parts[0];
            pronouns = parts[1];
        }

        ChatColor color = ChatColor.GRAY;
        try {
            color = ChatColor.valueOf(colorName);
        } catch (IllegalArgumentException ignored) {}

        String brackets = color + "[" + pronouns + "]" + ChatColor.RESET;
        String colorless = ChatColor.WHITE + "[" + pronouns + "]" + ChatColor.RESET;
        String raw = color + pronouns + ChatColor.RESET;
        String fullraw = pronouns;
        String lower = brackets.toLowerCase();
        String upper = brackets.toUpperCase();
        String cap = capitalizePronouns(pronouns, color);
        String shortForm = pronouns.split("/")[0];

        if (identifier.equals("")) return brackets;
        if (identifier.equals("raw")) return raw;
        if (identifier.equals("colorless")) return colorless;
        if (identifier.equals("fullraw")) return fullraw;
        if (identifier.equals("lowercase")) return lower;
        if (identifier.equals("uppercase")) return upper;
        if (identifier.equals("capitalized")) return cap;
        if (identifier.equals("short")) return shortForm;

        if (identifier.startsWith("or:")) return brackets.isEmpty() ? identifier.substring(3) : brackets;
        if (identifier.startsWith("raw_or:")) return raw.isEmpty() ? identifier.substring(7) : raw;
        if (identifier.startsWith("fullraw_or:")) return fullraw.isEmpty() ? identifier.substring(11) : fullraw;

        return null;
    }

    private String capitalizePronouns(String pronouns, ChatColor color) {
        String[] words = pronouns.split("/");
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
            }
        }
        return color + "[" + String.join("/", words) + "]" + ChatColor.RESET;
    }
} 
