# 📘 Pronouns Documentation

Welcome to the official documentation for **Pronouns** – a lightweight, inclusive plugin that lets players set and display pronouns in chat and beyond using [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/).

---

## 🔧 Installation

1. Place the plugin JAR in your plugins/ directory.
2. Install [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) if you haven’t already.
3. Start or restart the server.

---

## 📝 Commands

| Command | Description | Permission |
|--------|-------------|------------|
| /pronouns add <colour> <pronouns> | Set your own pronouns | pronounsplugin.use |
| /pronouns remove | Remove your own pronouns | pronounsplugin.use |
| /pronouns remove <player> | Remove another player's pronouns | pronounsplugin.admin |

Aliases: /pronoun

---

## 🔒 Permissions

| Node | Description | Default |
|------|-------------|---------|
| pronounsplugin.use | Allows a player to set or remove their own pronouns | ✅ True |
| pronounsplugin.admin | Allows managing pronouns for others | ❌ OP only |

---

## 🧩 PlaceholderAPI Placeholders

You can use these placeholders in any plugin that supports [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/). All variants pull the player's saved pronouns.

---

### ✅ Basic Placeholders

| Placeholder | Description |
|-------------|-------------|
| %pronouns% | Returns the default pronouns (bracketed, e.g., (they/them)) |
| %pronouns_raw% | Raw pronouns with no brackets or formatting |
| %pronouns_colorless% | Raw pronouns with no color codes |
| %pronouns_fullraw% | The full raw pronouns including any surrounding formatting (e.g., brackets/colors) |
| %pronouns_lowercase% | Pronouns in all lowercase |
| %pronouns_uppercase% | Pronouns in all uppercase |
| %pronouns_capitalized% | Pronouns with the first letter of each word capitalized |
| %pronouns_short% | The short form of pronouns (e.g., they instead of they/them) |
| %pronouns_parentheses% |	Pronouns in parentheses instead of brackets (e.g., (they/them)) |
| %pronouns_parentheses_colorless% |	Colourless pronouns in parentheses instead of brackets (e.g., (they/them) but colourless) |

---

### 🆘 Fallback Variants

These placeholders provide a default value if the player hasn't set their pronouns.

| Placeholder | Description |
|-------------|-------------|
| %pronouns_or:<fallback>% | Default bracketed pronouns, or <fallback> if not set |
| %pronouns_raw_or:<fallback>% | Raw pronouns or <fallback> |
| %pronouns_fullraw_or:<fallback>% | Full raw pronouns or <fallback> |

**Examples:**

text
%pronouns_or:unspecified%
%pronouns_raw_or:ask me%
%pronouns_fullraw_or:none set%


These will return the stored pronouns if available — otherwise, the fallback string will be used.

---

### 💡 Example Chat Format
text
[ %pronouns_or:no pronouns% ] %player%: %message%


Use this with any chat plugin that supports PlaceholderAPI!

---

## 📁 Configuration

All pronouns are stored in plugins/PronounsPlugin/pronouns.json. The format is:

```json
{
  "player":"colour:pronouns"
}
```

Example:
```json
{
  "fb6647db-ad52-4a54-9c7b-56fff703c299":"GOLD:She/They"
}
```

> ⚠️ The plugin automatically saves to this file. Do not edit while the server is running unless you reload afterward.

---

## 🌐 Compatibility

| Server Type | Status |
|-------------|--------|
| Spigot | ✅ Fully supported |
| Paper | ✅ Fully supported |
| Purpur | ✅ Compatible |
| BungeeCord | ❌ Not supported (local config only) |

---

## 📌 Planned Features

- 🔁 MySQL support for global pronouns across networks
- 🖼️ GUI menu for pronoun selection
- 🧠 Auto-suggest common pronouns
- 🔃 Reload command

---

## ❤️ Contributing & Support

If you'd like to suggest features, report a bug, or contribute code:
- Open an issue or PR on GitHub
- Contact me directly on Spigot

*Let’s make Minecraft more inclusive, one pronoun at a time!*
