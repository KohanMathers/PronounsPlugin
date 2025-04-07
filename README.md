# Pronouns – Let Players Show Their True Selves

## Version 2.0.0 | Made by KohanMathers

Give your Minecraft community the power to express themselves authentically. **Pronouns** allows players to set and display their pronouns directly in chat, with integration into PlaceholderAPI. Whether you're running a survival server or an inclusive roleplay world, this plugin helps create a welcoming and respectful space for everyone.

---

### ✨ Features
- ✅ Players can set or remove their pronouns
- ✅ Display pronouns in chat using PlaceholderAPI (%pronouns%)
- ✅ Admin support to manage pronouns for others
- ✅ Fully configurable and easy to use
- ✅ Lightweight and optimized for performance
- ✅ Works out-of-the-box on Spigot and Paper (1.16+)

---

###  Commands
| Command | Description |
|--------|-------------|
| /pronouns add <colour> <pronouns> | Set pronouns for another player |
| /pronouns remove | Remove your pronouns |
| /pronouns remove <player> | *(Admin)* Remove another player's pronouns |

Aliases: /pronoun

---

###  Permissions
| Permission | Description | Default |
|------------|-------------|---------|
| pronounsplugin.use | Allows a player to set their own pronouns | ✅ Everyone |
| pronounsplugin.admin | Allows a player to manage others’ pronouns | ❌ OP only |

---

###  PlaceholderAPI Support
Use %pronouns% anywhere you want to show a player's pronouns — in nametags, tablist, chat formats, scoreboards, and more!

Example (with a chat plugin):
%pronouns%  %player%: %message%


---

### ⚙️ Installation
1. Drop the plugin JAR into your server's plugins/ folder.
2. Make sure [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) is installed.
3. Restart the server.
4. Configure chat format with your preferred chat plugin to include %pronouns%.

---

###  Notes
- This plugin is **Spigot/Paper only**. It does not currently support cross-server setups like BungeeCord.
- Pronouns are stored locally in a YAML config file (plugins/PronounsPlugin/pronouns.yml).
- **PlaceholderAPI and any chat formatter that supports PlaceholderAPI are required**

---

###  Planned Features
-  MySQL support for shared pronouns across BungeeCord servers
- ️ GUI for setting pronouns
-  Auto-suggestions for common pronouns

---

### ❤️ Support & Contributions
Have a suggestion or bug report? Join the project or message me directly!

*Let’s make Minecraft more inclusive, one chat message at a time.*
