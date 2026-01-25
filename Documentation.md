# 📘 Pronouns Documentation

Welcome to the official documentation for **Pronouns v3.5.0** – a lightweight, inclusive plugin that lets players set and display pronouns in chat and beyond using [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/).

---

## 🔧 Installation

1. Place the plugin JAR in your `plugins/` directory.
2. Install [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) if you haven't already.
3. Start or restart the server.
4. Configure via `plugins/PronounsPlugin/config.yml`

---

## 📝 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/pronouns set <color> <pronouns>` | Set your pronouns with a color | `pronouns.use` |
| `/pronouns set gradient <preset\|colors> <pronouns>` | Set pronouns with gradient colors | `pronouns.gradient` |
| `/pronouns remove` | Remove your own pronouns | `pronouns.use` |
| `/pronouns remove <player>` | Remove another player's pronouns | `pronouns.remove.others` |
| `/pronouns reload` | Reload the plugin configuration | `pronouns.reload` |

**Aliases:** `/pronoun`, `/pn`

**Examples:**
- `/pronouns set white they/them`
- `/pronouns set gradient pride they/them`
- `/pronouns set gradient #ff0000 #0000ff she/her`
- `/pronouns set gradient red blue green he/him`

---

## 🌈 Gradient Presets

Built-in gradient presets:
| Preset | Description |
|--------|-------------|
| `pride` | 🏳️‍🌈 Rainbow pride colors |
| `galaxy` | 🌌 Purple galaxy aesthetic |
| `pastel` | 🎀 Soft pastel tones |

You can add custom presets in `config.yml` or use color names/hex codes directly!

---

## 🔒 Permissions

| Node | Description | Default |
|------|-------------|---------|
| `pronouns.use` | Allows setting/removing your own pronouns | ✅ Everyone |
| `pronouns.gradient` | Allows using gradient pronouns | ✅ Everyone |
| `pronouns.remove.others` | Allows removing other players' pronouns | ❌ OP only |
| `pronouns.gradient-limit-bypass` | Bypass the gradient color limit | ❌ OP only |
| `pronouns.bypass-filter` | Bypass whitelist/blacklist filters | ❌ OP only |
| `pronouns.reload` | Allows reloading the configuration | ❌ OP only |
| `pronouns.admin` | All admin permissions | ❌ OP only |

---

## 🧩 PlaceholderAPI Placeholders

You can use these placeholders in any plugin that supports [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/). All variants pull the player's saved pronouns.

---

### ✅ Basic Placeholders

| Placeholder | Description |
|-------------|-------------|
| `%pronouns%` | Pronouns with brackets and color (e.g., `[they/them]`) |
| `%pronouns_raw%` | Pronouns with color, no brackets |
| `%pronouns_colorless%` | Pronouns with brackets, white color |
| `%pronouns_fullraw%` | Plain text pronouns only, no formatting |
| `%pronouns_lowercase%` | Pronouns in all lowercase |
| `%pronouns_uppercase%` | Pronouns in all uppercase |
| `%pronouns_capitalized%` | Pronouns capitalized (e.g., `They/Them`) |
| `%pronouns_short%` | First pronoun only (e.g., `they`) |
| `%pronouns_parentheses%` | Pronouns in parentheses (e.g., `(they/them)`) |
| `%pronouns_parentheses_colorless%` | Colorless pronouns in parentheses |

---

### 🆘 Fallback Variants

These placeholders provide a default value if the player hasn't set their pronouns.

| Placeholder | Description |
|-------------|-------------|
| `%pronouns_or:<fallback>%` | Default bracketed pronouns, or `<fallback>` if not set |
| `%pronouns_raw_or:<fallback>%` | Raw pronouns or `<fallback>` |
| `%pronouns_fullraw_or:<fallback>%` | Full raw pronouns or `<fallback>` |

**Examples:**
```
%pronouns_or:unspecified%
%pronouns_raw_or:ask me%
%pronouns_fullraw_or:none set%
```

These will return the stored pronouns if available — otherwise, the fallback string will be used.

---

### 💡 Example Chat Format
```
[%pronouns_or:no pronouns%] %player%: %message%
```

Use this with any chat plugin that supports PlaceholderAPI!

---

## ⚙️ Configuration

### 📁 Data Storage

All pronouns are stored in `plugins/PronounsPlugin/pronouns.json`. The format is:

```json
{
  "uuid": "color:pronouns"
}
```

Example:
```json
{
  "fb6647db-ad52-4a54-9c7b-56fff703c299": "GOLD:She/They"
}
```

> ⚠️ The plugin automatically saves to this file. Do not edit while the server is running unless you reload afterward.

---

### 🛡️ Filter Settings (New in 3.5.0)

Control which pronouns are allowed on your server:

```yaml
filter:
  whitelist:
    enabled: false
    list: ["he/him", "she/her", "they/them"]
    regex: ""
  blacklist:
    enabled: false
    list: []
    regex: "badword|offensive"
```

| Setting | Description |
|---------|-------------|
| `whitelist.enabled` | Only allow pronouns matching the list or regex |
| `whitelist.list` | Exact matches (case-insensitive) |
| `whitelist.regex` | Java regex pattern for allowed pronouns |
| `blacklist.enabled` | Block pronouns matching the list or regex |
| `blacklist.list` | Exact matches to block |
| `blacklist.regex` | Java regex pattern for blocked pronouns |

> 💡 Whitelist takes priority if both have matches. Players with `pronouns.bypass-filter` permission skip filtering.

---

### 🎨 Gradient Settings

```yaml
gradient:
  color-limit: 5          # Max colors allowed (-1 for unlimited)
  show-preview: true      # Show preview when gradient is applied
  presets:
    pride: "#FF0018 #FFA52C #FFFF41 #008018 #0000F9 #86007D"
    galaxy: "#667eea #764ba2 #667eea"
    pastel: "#ffecd2 #fcb69f #ffecd2"
```

---

### 💬 Display Settings

```yaml
general:
  max-pronoun-length: 20
  update-display-names: true
  auto-save: true
  save-delay: 1

chat:
  show-in-tab-list: true
  show-above-head: false
  bracket-style: "square"  # square, round, curly, angle
```

---

### 📨 Messages

All messages are fully customizable with MiniMessage and legacy color code (`&c`, `&a`, etc.) support!

---

## 🌐 Compatibility

| Server Type | Status |
|-------------|--------|
| Paper | ✅ Fully supported |
| Spigot | ✅ Fully supported |
| Purpur | ✅ Compatible |
| Folia | ❓ Untested |
| BungeeCord | ❌ Not supported (local config only) |

**Minimum Version:** 1.21+

---

## 📌 Planned Features

- 🔁 MySQL support for global pronouns across networks
- 🖼️ GUI menu for pronoun selection
- 🧠 Auto-suggest common pronouns
- 🌍 Per-world pronoun display settings

---

## 📊 bStats

This plugin uses [bStats](https://bstats.org/) to collect anonymous usage statistics. You can opt out in `plugins/bStats/config.yml`.

---

## ❤️ Contributing & Support

If you'd like to suggest features, report a bug, or contribute code:
- Open an issue or PR on GitHub
- Contact me directly on Spigot

*Let's make Minecraft more inclusive, one pronoun at a time!* 🏳️‍🌈
