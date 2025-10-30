# Pronouns – Let Players Show Their True Selves
## Version 3.4.1 | Made by KohanMathers

Give your Minecraft community the power to express themselves authentically. **Pronouns** allows players to set and display their pronouns directly in chat and above their heads, with vibrant colors and gradient effects. Whether you're running a survival server or an inclusive roleplay world, this plugin helps create a welcoming and respectful space for everyone.

---

### ✨ Features

- ✅ Players can set or remove their pronouns with custom colors
- ✅ **Gradient pronouns** with preset color schemes (trans, lesbian, bi, pan, etc.)
- ✅ Display pronouns in chat, above player heads, and in tab list
- ✅ PlaceholderAPI integration (%pronounsplugin_pronouns%)
- ✅ Admin support to manage pronouns for others
- ✅ Fully configurable messages and display options
- ✅ Lightweight and optimized for performance
- ✅ Works on both **Spigot and Paper** (1.16+)
- ✅ bStats metrics integration

---

### 🎨 Commands

| Command | Description |
|---------|-------------|
| `/pronouns set <color> <pronouns>` | Set your pronouns with a specific color |
| `/pronouns set gradient <preset\|colors> <pronouns>` | Set gradient pronouns using presets or custom colors |
| `/pronouns remove` | Remove your pronouns |
| `/pronouns remove <player>` | *(Admin)* Remove another player's pronouns |
| `/pronouns reload` | *(Admin)* Reload the plugin configuration |

**Aliases:** `/pronoun`

**Examples:**
- `/pronouns set white they/them`
- `/pronouns set gradient trans they/them`
- `/pronouns set gradient #ff0000 #0000ff she/her`
- `/pronouns set gradient red blue green he/him`

---

### 🌈 Gradient Presets

The plugin comes with built-in gradient presets for pride flags:
- `trans` - Transgender flag colors
- `lesbian` - Lesbian flag colors
- `bi` - Bisexual flag colors
- `pan` - Pansexual flag colors
- `ace` - Asexual flag colors
- `nonbinary` - Non-binary flag colors
- `rainbow` - Classic rainbow pride colors

You can also create custom gradients using color names or hex codes!

---

### 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `pronounsplugin.use` | Allows a player to set their own pronouns | ✅ Everyone |
| `pronounsplugin.admin` | Allows managing others' pronouns | ❌ OP only |
| `pronounsplugin.reload` | Allows reloading the configuration | ❌ OP only |
| `pronouns.gradient-limit-bypass` | Bypass the gradient color limit | ❌ OP only |

---

### 📊 PlaceholderAPI Support

**Requires PlaceholderAPI to be installed!**

Use `%pronounsplugin_pronouns%` anywhere you want to show a player's pronouns — in nametags, tablist, chat formats, scoreboards, and more!

**Example (with a chat plugin):**
```
[%pronounsplugin_pronouns%] %player%: %message%
```

---

### ⚙️ Configuration

The plugin is highly configurable via `config.yml`:

**General Settings:**
- `update-display-names` - Whether to update player display names
- `max-pronoun-length` - Maximum character limit for pronouns
- `auto-save` - Enable automatic saving
- `save-delay` - Delay before saving (in seconds)

**Gradient Settings:**
- `color-limit` - Maximum colors allowed in gradients
- `show-preview` - Show gradient preview when set
- `presets` - Custom gradient presets

**Display Settings:**
- `show-above-head` - Show pronouns above player heads
- `show-in-tab-list` - Show pronouns in the tab list

**Messages:**
All messages are fully customizable with MiniMessage and legacy color code support!

---

### 🔧 Installation

1. Drop the plugin JAR into your server's `plugins/` folder
2. *(Recommended)* Install [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)
3. Restart the server
4. Configure via `plugins/PronounsPlugin/config.yml`
5. Set up your chat plugin to use `%pronounsplugin_pronouns%`

---

### 📝 Notes

- **Spigot/Paper only** (1.16+). Does not currently support BungeeCord networks
- Pronouns are stored in `plugins/PronounsPlugin/pronouns.json`
- PlaceholderAPI is **highly recommended** for chat integration
- The plugin automatically detects Paper vs Spigot and uses appropriate APIs
- Gradient effects use Minecraft's hex color system (1.16+)

---

### 🚀 Planned Features

- 🔗 MySQL support for shared pronouns across BungeeCord servers
- 🖱️ GUI for setting pronouns
- 💡 Auto-suggestions for common pronouns
- 🎨 More gradient presets
- 📱 Per-world pronoun display settings

---

### 🐛 Bug Reports & Support

Found a bug? Have a suggestion?
- Open an issue on the project page
- Join the support Discord
- Message KohanMathers directly

---

### ❤️ Support Development

If you love this plugin and want to support continued development:
- ⭐ Leave a review on SpigotMC
- 💬 Share with your server community
- ☕ Buy me a coffee (link)

---

*Let's make Minecraft more inclusive, one chat message at a time.* 🏳️‍🌈
