package com.quietterminal.pronounsplugin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.*;

import java.util.stream.Collectors;

public class PronounsPlugin extends JavaPlugin implements TabExecutor, Listener {
    private Map<UUID, String> pronounsData;
    private File pronounsFile;
    private final Gson gson = new Gson();

    private static final List<String> AVAILABLE_COLORS = Arrays.stream(ChatColor.values())
            .filter(ChatColor::isColor)
            .map(color -> color.name().toLowerCase())
            .collect(Collectors.toList());

    @Override
    public void onEnable() {
        getLogger().info("Enabling PronounsPlugin...");
        pronounsFile = new File(getDataFolder(), "pronouns.json");
        if (!pronounsFile.exists()) {
            try {
                getDataFolder().mkdirs();
                pronounsFile.createNewFile();
                Files.write(pronounsFile.toPath(), "{}".getBytes());
            } catch (IOException e) {
                getLogger().severe("Could not create pronouns.json file!");
                e.printStackTrace();
            }
        }

        try {
            String content = new String(Files.readAllBytes(pronounsFile.toPath()));
            Type type = new TypeToken<Map<UUID, String>>(){}.getType();
            pronounsData = gson.fromJson(content, type);
            if (pronounsData == null) pronounsData = new HashMap<>();
        } catch (IOException e) {
            getLogger().severe("Could not read pronouns.json file!");
            e.printStackTrace();
        }

        if (getCommand("pronouns") == null) {
            getLogger().severe("Failed to register '/pronouns' command. Check plugin.yml.");
        } else {
            getCommand("pronouns").setExecutor(this);
            getCommand("pronouns").setTabCompleter(new PronounsTabCompleter());
        }

        getServer().getPluginManager().registerEvents(this, this);

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PronounsExpansion(this).register();
            getLogger().info("PlaceholderAPI found. Registered %pronounsplugin_pronouns%");
        } else {
            getLogger().warning("PlaceholderAPI not found! Pronoun placeholders will not work.");
        }
    }

    @Override
    public void onDisable() {
        savePronounsData();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        savePronounsData();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        updatePlayerName(event.getPlayer());
    }

    public Map<UUID, String> getPronounsData() {
        return pronounsData;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /pronouns <add|remove> [args]");
            return false;
        }

        String subCommand = args[0].toLowerCase();

        if (subCommand.equals("add")) {
            if (args.length < 3) {
                sender.sendMessage(ChatColor.RED + "Usage: /pronouns add <color> <pronouns>");
                return false;
            }

            if (sender instanceof Player player) {
                String colorName = args[1].toUpperCase();
                ChatColor color;
                try {
                    color = ChatColor.valueOf(colorName);
                } catch (IllegalArgumentException e) {
                    color = ChatColor.GRAY;
                }

                String pronouns = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                pronounsData.put(player.getUniqueId(), color.name() + ":" + pronouns);
                sender.sendMessage(ChatColor.GREEN + "Your pronouns have been set to: " + color + pronouns);
                updatePlayerName(player);
                savePronounsData();
            } else {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            }
        } else if (subCommand.equals("remove")) {
            if (args.length < 2) {
                if (sender instanceof Player player) {
                    if (pronounsData.containsKey(player.getUniqueId())) {
                        pronounsData.remove(player.getUniqueId());
                        sender.sendMessage(ChatColor.GREEN + "Your pronouns have been removed.");
                        updatePlayerName(player);
                        savePronounsData();
                    } else {
                        sender.sendMessage(ChatColor.RED + "You have no pronouns set.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Usage: /pronouns remove <player>");
                }
                return false;
            }

            if (sender.isOp()) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    if (pronounsData.containsKey(target.getUniqueId())) {
                        pronounsData.remove(target.getUniqueId());
                        sender.sendMessage(ChatColor.GREEN + "Removed pronouns for " + target.getName());
                        updatePlayerName(target);
                        savePronounsData();
                    } else {
                        sender.sendMessage(ChatColor.RED + target.getName() + " has no pronouns set.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to remove others' pronouns.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown subcommand. Use /pronouns <add|remove>.");
        }

        return true;
    }

    private void savePronounsData() {
        try {
            String json = gson.toJson(pronounsData);
            Files.write(pronounsFile.toPath(), json.getBytes());
        } catch (IOException e) {
            getLogger().severe("Could not save pronouns.json file!");
            e.printStackTrace();
        }
    }

    private void updatePlayerName(Player player) {
        String storedData = pronounsData.get(player.getUniqueId());
        String playerName = player.getName();

        if (storedData != null) {
            String[] parts = storedData.split(":", 2);
            ChatColor color = ChatColor.GRAY;
            String pronouns = parts[1];
            try {
                color = ChatColor.valueOf(parts[0]);
            } catch (IllegalArgumentException ignored) {}

            String formattedName = color + "[" + pronouns + "] " + ChatColor.RESET + playerName;
            player.setDisplayName(formattedName);
            player.setCustomName(formattedName);
            player.setCustomNameVisible(true);
            player.setPlayerListName(formattedName);
        } else {
            player.setDisplayName(playerName);
            player.setCustomName(playerName);
            player.setCustomNameVisible(false);
            player.setPlayerListName(playerName);
        }
    }

    private class PronounsTabCompleter implements TabCompleter {
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
                return AVAILABLE_COLORS.stream().filter(color -> color.startsWith(args[1].toLowerCase())).collect(Collectors.toList());
            } else if (args.length == 1) {
                return Arrays.asList("add", "remove").stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
            }
            return Collections.emptyList();
        }
    }
}
