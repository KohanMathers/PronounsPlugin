package com.quietterminal.pronounsplugin;

import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

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
        if (stored == null || stored.isEmpty()) {
            if (identifier.startsWith("or:")) return identifier.substring(3);
            if (identifier.startsWith("raw_or:")) return identifier.substring(7);
            if (identifier.startsWith("fullraw_or:")) return identifier.substring(11);
            return "";
        }

        if (stored.startsWith("GRADIENT:")) {
            return handleGradientPronouns(stored.substring(9), identifier);
        } else {
            return handleRegularPronouns(stored, identifier);
        }
    }

    private String handleGradientPronouns(String gradientText, String identifier) {
        if (gradientText.isEmpty()) {
            return "";
        }

        String plainText = stripAllColors(gradientText);
        
        String brackets = "[" + gradientText + "§r]§r";
        String colorless = "§f[" + plainText + "]§r";
        String raw = gradientText + "§r";
        String fullraw = plainText;
        String lower = "[" + gradientText.toLowerCase() + "§r]§r";
        String upper = "[" + gradientText.toUpperCase() + "§r]§r";
        String cap = "[" + capitalizeText(plainText) + "§r]§r";
        String shortForm = plainText.contains("/") ? plainText.split("/")[0] : plainText;
        String parentheses = "(" + raw + ")";
        String parenthesesColorless = "(" + fullraw + ")";

        if (identifier.equals("")) return brackets;
        if (identifier.equals("raw")) return raw;
        if (identifier.equals("colorless")) return colorless;
        if (identifier.equals("fullraw")) return fullraw;
        if (identifier.equals("lowercase")) return lower;
        if (identifier.equals("uppercase")) return upper;
        if (identifier.equals("capitalized")) return cap;
        if (identifier.equals("short")) return shortForm;
        if (identifier.equals("parentheses")) return parentheses;
        if (identifier.equals("parentheses_colorless")) return parenthesesColorless;

        if (identifier.startsWith("or:")) return brackets.isEmpty() ? identifier.substring(3) : brackets;
        if (identifier.startsWith("raw_or:")) return raw.isEmpty() ? identifier.substring(7) : raw;
        if (identifier.startsWith("fullraw_or:")) return fullraw.isEmpty() ? identifier.substring(11) : fullraw;
        if (identifier.startsWith("then_")) return brackets.isEmpty() ? "" : brackets + identifier.substring(5).replace("_", " ");

        return null;
    }

    private String handleRegularPronouns(String stored, String identifier) {
        String colorCode = "§7";
        String pronouns = "";
        if (stored.contains(":")) {
            String[] parts = stored.split(":", 2);
            colorCode = getColorCode(parts[0]);
            pronouns = parts[1];
        }

        if (pronouns.isEmpty()) {
            return "";
        }

        String brackets = colorCode + "[" + pronouns + "]§r";
        String colorless = "§f[" + pronouns + "]§r";
        String raw = colorCode + pronouns + "§r";
        String fullraw = pronouns;
        String lower = brackets.toLowerCase();
        String upper = brackets.toUpperCase();
        String cap = capitalizePronouns(pronouns, colorCode);
        String shortForm = pronouns.contains("/") ? pronouns.split("/")[0] : pronouns;
        String parentheses = "(" + raw + ")";
        String parenthesesColorless = "(" + fullraw + ")";

        if (identifier.equals("")) return brackets;
        if (identifier.equals("raw")) return raw;
        if (identifier.equals("colorless")) return colorless;
        if (identifier.equals("fullraw")) return fullraw;
        if (identifier.equals("lowercase")) return lower;
        if (identifier.equals("uppercase")) return upper;
        if (identifier.equals("capitalized")) return cap;
        if (identifier.equals("short")) return shortForm;
        if (identifier.equals("parentheses")) return parentheses;
        if (identifier.equals("parentheses_colorless")) return parenthesesColorless;

        if (identifier.startsWith("or:")) return brackets.isEmpty() ? identifier.substring(3) : brackets;
        if (identifier.startsWith("raw_or:")) return raw.isEmpty() ? identifier.substring(7) : raw;
        if (identifier.startsWith("fullraw_or:")) return fullraw.isEmpty() ? identifier.substring(11) : fullraw;
        if (identifier.startsWith("then_")) return brackets.isEmpty() ? "" : brackets + identifier.substring(5).replace("_", " ");

        return null;
    }

    private String getColorCode(String colorName) {
        return switch (colorName.toUpperCase()) {
            case "BLACK" -> "§0";
            case "DARK_BLUE" -> "§1";
            case "DARK_GREEN" -> "§2";
            case "DARK_AQUA" -> "§3";
            case "DARK_RED" -> "§4";
            case "DARK_PURPLE" -> "§5";
            case "GOLD" -> "§6";
            case "GRAY" -> "§7";
            case "DARK_GRAY" -> "§8";
            case "BLUE" -> "§9";
            case "GREEN" -> "§a";
            case "AQUA" -> "§b";
            case "RED" -> "§c";
            case "LIGHT_PURPLE" -> "§d";
            case "YELLOW" -> "§e";
            case "WHITE" -> "§f";
            default -> "§7";
        };
    }

    private String capitalizePronouns(String pronouns, String colorCode) {
        String[] words = pronouns.split("/");
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
            }
        }
        return colorCode + "[" + String.join("/", words) + "]§r";
    }

    private String capitalizeText(String plainText) {
        String[] words = plainText.split("/");
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
            }
        }
        return String.join("/", words);
    }

    private String stripAllColors(String text) {
        text = text.replaceAll("§x(§[0-9a-f]){6}", "");
        text = text.replaceAll("§[0-9a-fk-or]", "");
        return text;
    }
}
