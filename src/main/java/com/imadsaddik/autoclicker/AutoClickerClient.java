package com.imadsaddik.autoclicker;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoClickerClient implements ClientModInitializer {
    public static final String MOD_ID = "auto_clicker";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static KeyMapping toggleKeyMapping;
    private boolean isModEnabled = false;
    private boolean wasToggleKeyDown = false;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing the" + MOD_ID + "mod");

        registerKeyBind();
        registerEvents();
    }

    private void registerKeyBind() {
        KeyMapping.Category category = new KeyMapping.Category(
            Identifier.fromNamespaceAndPath(MOD_ID, "main")
        );
        String toggleKeyName = "key." + MOD_ID + ".toggle";

        toggleKeyMapping = KeyBindingHelper.registerKeyBinding(
            new KeyMapping(
                toggleKeyName,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                category
            )
        );
    }

    private void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            handleKeyPress(client);
        });
    }

    private void handleKeyPress(Minecraft client) {
        LocalPlayer player = client.player;
        if (player == null) return;

        boolean isToggleKeyDown = toggleKeyMapping.isDown();

        if (isToggleKeyDown && !wasToggleKeyDown) {
            isModEnabled = !isModEnabled;
            String literal = "Auto clicker: " + (isModEnabled ? "ON" : "OFF");
            player.displayClientMessage(Component.literal(literal), false);
        }

        wasToggleKeyDown = isToggleKeyDown;
    }
}