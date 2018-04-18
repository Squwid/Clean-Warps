# CleanWarps

### Description: This plugin is a look-alike Minecraft Plugin like the MCPVP warps plugin. 
### Version: 1.2 Optimized for **Bukkit 1.12.2.** (Spigot version in the works)
#
#### About: This plugin is free to use and fork to create any changes that need to be made. Download the `.jar` if you are unsure on how to modify and compile the code, and drag into plugins folder and run the server. Simple as that. 

#
## Commands: 
```
All Players
--------------------------------------------------
/go <warp> -  Teleport to a warp
/go set <warp> - Set a warp
/go del <warp> - Delete a warp
/go list - List your warps

Admins ("cleanwarps.admin")
--------------------------------------------------
/g del <player> [warpIndex] - Delete a players warp
/g list <player> - Get indexes of a players warps
/g set <player> <warpName> - Set a warp for another player
/g tp <player> [warpIndex] - Teleport to another players warp
```

## Permissions

Admin Commands: "cleanwarps.admin"

Max Warps: "cleanwarps.limit.10" (Max is 100)


## TODO's: 
1. ~~Support for Minecraft Allowing Players to change their name and not lose their warps.~~
2. Database capabilities to allow for other than text-file OOTB saving of warps.
3. ~~Admin support to allow admins to change warps without having to modify text files.~~
4. ~~Add settings for different amount of warps for different people~~