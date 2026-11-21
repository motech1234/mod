# No Harming / Slow Falling Arrows (Fabric, Minecraft 26.2)

Disables Tipped Arrows of Harming and Slow Falling (all variants and any custom potion
containing Instant Damage or Slow Falling).

## Build
Requires JDK 25.
1. Get the Gradle wrapper (the `gradlew`, `gradlew.bat` and `gradle/` folder) from
   https://github.com/FabricMC/fabric-example-mod (26.2 branch) and copy it into this folder,
   or run `gradle wrapper` if you have Gradle installed.
2. Run `./gradlew build` (Windows: `gradlew build`).
3. The jar is `build/libs/noharmarrows-1.0.0.jar` (not the `-sources` one).

## Install
Put the jar and Fabric API in `mods/`. Server-side is enough; clients don't need it.

## Options
`NoHarmArrows.SCRUB_INVENTORIES` (true by default): delete banned arrows from inventories.
Set to false if you'd rather let players keep them (they still vanish when shot).
