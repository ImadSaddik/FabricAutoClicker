# Fabric auto clicker

[![build](https://github.com/ImadSaddik/FabricAutoClicker/actions/workflows/build.yml/badge.svg)](https://github.com/ImadSaddik/FabricAutoClicker/actions/workflows/build.yml)
[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.11-62B451?logo=minecraft&logoColor=white)](https://www.minecraft.net/)
[![Fabric Loader](https://img.shields.io/badge/Loader-Fabric-F8F1D1?logo=fabricloader&logoColor=3F3024)](https://fabricmc.net/)
[![Java Version](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/technologies/downloads/#java21)
[![GitHub license](https://img.shields.io/github/license/ImadSaddik/FabricAutoClicker)](https://github.com/ImadSaddik/FabricAutoClicker/blob/main/LICENSE)

A smart, context-aware auto clicker mod for Minecraft built on the Fabric API.

Unlike traditional macro-based auto clickers that blindly spam the left mouse button, this mod reads the game state to perform perfect, cooldown-aware attacks and continuous block mining.

Watch how the mod performs smart attacking and continuous mining based on game state.

https://github.com/user-attachments/assets/cace9bd2-c89e-4acb-9a21-a7f211e5d1a4

## Usage

The mod is designed to be simple and intuitive. Once installed, you can toggle it with a single keypress.

### Toggle status

You can enable or disable the auto clicker by pressing the **`R`** key. Once toggled, a clean, animated overlay will appear in the top-left corner of your screen to show you the current state.

![Auto clicker status indicator](./images/state_indicator.png)

### Customizing keybinds

If `R` conflicts with your existing controls, you can easily change it:

1. Press `Esc` and go to **Options** > **Controls** > **Key Binds**.
2. Go to the **Auto clicker** section.
3. Click the key to rebind it to your preference.

![Change key binds](./images/change_key_binds.png)

## Installation

You have two options to get the mod. You can download the compiled file or build it yourself from the source code.

### Downloading from releases

This is the easiest way to install the mod for regular play.

1. Download the latest `.jar` file from the [Releases](https://github.com/ImadSaddik/FabricAutoClicker/releases) page.
2. Make sure you have the [Fabric Loader](https://fabricmc.net/use/installer/) installed for Minecraft 1.21.11.
3. Download the [Fabric API](https://modrinth.com/mod/fabric-api) mod.
4. Place both the Fabric API `.jar` and the Auto clicker `.jar` into your Minecraft `mods` folder.
5. Launch the game using the Fabric profile.

### Building manually

If you want to compile the mod yourself, you can build it from the source code.

Clone the repository:

```bash
git clone https://github.com/ImadSaddik/FabricAutoClicker.git
```

Navigate to the project directory:

```bash
cd FabricAutoClicker
```
Build the mod using Gradle:

```bash
./gradlew build
```

> [!NOTE]
> On Windows, use `gradlew.bat` instead of `./gradlew`.

The compiled `.jar` file will be located in the [build/libs](./build/libs/) directory. You can then place this file in your Minecraft `mods` folder along with the Fabric API to use the mod.

## Licence

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.
