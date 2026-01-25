# Pronouns – Let Players Show Their True Selves
## Version 3.5.0 | Made by KohanMathers

Give your Minecraft community the power to express themselves authentically. **Pronouns** allows players to set and display their pronouns directly in chat and above their heads, with vibrant colors and gradient effects. Whether you're running a survival server or an inclusive roleplay world, this plugin helps create a welcoming and respectful space for everyone.

---

### Features

- Players can set or remove their pronouns with custom colors
- **Gradient pronouns** with preset color schemes or custom hex colors
- Display pronouns in chat, above player heads, and in tab list
- **Whitelist & blacklist filtering** with regex support
- PlaceholderAPI integration (`%pronouns_<identifier>%`)
- Admin support to manage pronouns for others
- Fully configurable messages and display options
- Lightweight and optimized for performance
- Works on both **Spigot and Paper** (1.21+)
- bStats metrics integration

---

### Commands

| Command | Description |
|---------|-------------|
| `/pronouns set <color> <pronouns>` | Set your pronouns with a specific color |
| `/pronouns set gradient <preset\|colors> <pronouns>` | Set gradient pronouns using presets or custom colors |
| `/pronouns remove` | Remove your pronouns |
| `/pronouns remove <player>` | *(Admin)* Remove another player's pronouns |
| `/pronouns reload` | *(Admin)* Reload the plugin configuration |

**Aliases:** `/pronoun`, `/pn`

**Examples:**
- `/pronouns set white they/them`
- `/pronouns set gradient pride they/them`
- `/pronouns set gradient #ff0000 #0000ff she/her`
- `/pronouns set gradient red blue green he/him`

---

### Gradient Presets

The plugin comes with built-in gradient presets:
- `pride` - Rainbow pride colors
- `galaxy` - Purple galaxy aesthetic
- `pastel` - Soft pastel tones

You can add custom presets in `config.yml` or use color names/hex codes directly!

---

### Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `pronouns.use` | Allows setting your own pronouns | Everyone |
| `pronouns.gradient` | Allows using gradient pronouns | Everyone |
| `pronouns.remove.others` | Allows removing other players' pronouns | OP only |
| `pronouns.gradient-limit-bypass` | Bypass the gradient color limit | OP only |
| `pronouns.bypass-filter` | Bypass whitelist/blacklist filters | OP only |
| `pronouns.reload` | Allows reloading the configuration | OP only |
| `pronouns.admin` | All admin permissions | OP only |

---

### PlaceholderAPI Support

**Requires PlaceholderAPI to be installed!**

| Placeholder | Description |
|-------------|-------------|
| `%pronouns%` | Pronouns with brackets and color `[they/them]` |
| `%pronouns_raw%` | Pronouns with color, no brackets |
| `%pronouns_colorless%` | Pronouns with brackets, white color |
| `%pronouns_fullraw%` | Plain text pronouns only |
| `%pronouns_lowercase%` | Lowercase with brackets |
| `%pronouns_uppercase%` | Uppercase with brackets |
| `%pronouns_capitalized%` | Capitalized (They/Them) |
| `%pronouns_short%` | First pronoun only (they) |
| `%pronouns_parentheses%` | With parentheses instead of brackets |
| `%pronouns_or:<fallback>%` | Returns fallback if no pronouns set |

**Example (with a chat plugin):**
```
[%pronouns%] %player%: %message%
```

---

### Configuration

The plugin is highly configurable via `config.yml`:

**General Settings:**
- `max-pronoun-length` - Maximum character limit for pronouns (default: 20)
- `update-display-names` - Whether to update player display names
- `auto-save` - Enable automatic saving
- `save-delay` - Delay before saving (in seconds)

**Gradient Settings:**
- `color-limit` - Maximum colors allowed in gradients (default: 5, -1 for unlimited)
- `show-preview` - Show gradient preview when set
- `presets` - Define custom gradient presets

**Chat/Display Settings:**
- `show-above-head` - Show pronouns above player heads
- `show-in-tab-list` - Show pronouns in the tab list
- `bracket-style` - Bracket style (square, round, curly, angle)

**Filter Settings (New in 3.5.0):**
- `filter.whitelist.enabled` - Only allow specific pronouns
- `filter.whitelist.list` - List of allowed pronouns (case-insensitive)
- `filter.whitelist.regex` - Regex pattern for allowed pronouns
- `filter.blacklist.enabled` - Block specific pronouns
- `filter.blacklist.list` - List of blocked pronouns
- `filter.blacklist.regex` - Regex pattern for blocked pronouns

**Filter Example:**
```yaml
filter:
  whitelist:
    enabled: true
    list: ["he/him", "she/her", "they/them"]
    regex: ""
  blacklist:
    enabled: true
    list: []
    regex: "badword|offensive"
```

**Messages:**
All messages are fully customizable with MiniMessage and legacy color code support!

---

### Installation

1. Drop the plugin JAR into your server's `plugins/` folder
2. *(Recommended)* Install [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)
3. Restart the server
4. Configure via `plugins/PronounsPlugin/config.yml`
5. Set up your chat plugin to use `%pronouns%`

---

### Notes

- **Spigot/Paper only** (1.21+). Does not currently support BungeeCord networks
- Pronouns are stored in `plugins/PronounsPlugin/pronouns.json`
- PlaceholderAPI is **highly recommended** for chat integration
- The plugin automatically detects Paper vs Spigot and uses appropriate APIs
- Gradient effects use Minecraft's hex color system (1.16+)

---

### Planned Features

- MySQL support for shared pronouns across BungeeCord servers
- GUI for setting pronouns
- Auto-suggestions for common pronouns
- More gradient presets
- Per-world pronoun display settings

---

### Bug Reports & Support

Found a bug? Have a suggestion?
- Open an issue on the project page
- Join the support Discord
- Message KohanMathers directly

---

### Support Development

If you love this plugin and want to support continued development:
- Leave a review on SpigotMC
- Share with your server community

---

*Let's make Minecraft more inclusive, one chat message at a time.*
