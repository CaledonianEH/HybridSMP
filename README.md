# HybridSMP
A server core for a private SMP server, eliminating the need for excessive amounts of plugins.
### Features
- Server Reactions
- Staff Chat
- Staff List
- Vanish
- Full JSON chat formatting
- Gamemode commands
- World height limit
- Disable creeper explosions
- Unlimited random join / leave messages
- Fully configurable config and messages
- Full server log & alert
- PlaceholderAPI support
- 1.16 hex support

### Commands
##### Reactions
- /f - Pay respects
- /nf - Don't pay respects
- /bruh - Declare a bruh moment
- /gg - Declare a good game
- /rip - Rest in peace
- /doubt - Doubt something
- And many more

##### General | [required] {optional}
- /hybridcore - Main plugin command
- /staff {hide} - Staff list
- /sc {help|enable|disable} - Staff chat
- /v [help,(player)]
- /coords - Broadcast your coordinates


##### Admin | [required] {optional}
- /a (player) - Adventure mode
- /c (player) - Creative mode
- /sp (player) - Spectator mode
- /s (player) - Survival mode

### Configuration
##### config.yml
- mysql - Ignore, don't use
- server-name - Name of the local server, will be used with MySQL
- update-alert - Alert when a new update is available
- log-cooldown - Cooldown for log events (Height limit)
- chat-formatting - Config the chat format including message, hover, and click event 
- staffchat - Enable or disable the staffchat feature
- stafflist - Enable or disable the stafflist feature
- height-limit - Edit height limit settings (Prevents players from flying thousands of blocks in the air)
- creeper-explosions - Edit creeper settings to prevent block damage
