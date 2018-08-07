# CleanWarps

CleanWarps is a bukkit plugin that allows players to set and teleport to warps. This plugin also comes with admin commands that allows server administrators to look at players warps and teleport between them along with allowing players to only set a certain amount of warps

## [Bukkit Link](https://dev.bukkit.org/projects/cleanwarps) - 500 Downloads

### Version: 1.3 Optimized for **Bukkit 1.13**

#### About: This plugin is free to use and fork to create any changes that need to be made. Download the `.jar` if you are unsure on how to modify and compile the code, and drag into plugins folder and run the server. Simple as that

## Commands

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

## TODO's

1. ~~Support for Minecraft Allowing Players to change their name and not lose their warps.~~
1. Database capabilities to allow for other than text-file OOTB saving of warps.
1. Support for auto completion for player commands in game
1. ~~Admin support to allow admins to change warps without having to modify text files.~~
1. ~~Add settings for different amount of warps for different people~~
