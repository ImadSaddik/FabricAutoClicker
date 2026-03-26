package com.imadsaddik.autoclicker;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
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
        LOGGER.info("Initializing the " + MOD_ID + " mod");

        registerKeyBind();
        registerEvents();
        registerHudOverlay();
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
            handleAutoClicker(client);
        });
    }

    private void registerHudOverlay() {
        HudElementRegistry.addLast(
            Identifier.fromNamespaceAndPath(MOD_ID, "status_overlay"),
            (guiGraphics, deltaTracker) -> renderAutoClickerStatus(guiGraphics)
        );
    }

    private void handleKeyPress(Minecraft client) {
        LocalPlayer player = client.player;
        if (player == null) return;

        boolean isToggleKeyDown = toggleKeyMapping.isDown();

        if (isToggleKeyDown && !wasToggleKeyDown) {
            isModEnabled = !isModEnabled;

            if (!isModEnabled) {
                client.options.keyAttack.setDown(false);
            }
        }

        wasToggleKeyDown = isToggleKeyDown;
    }

    private void handleAutoClicker(Minecraft client) {
        ClientLevel level = client.level;
        LocalPlayer player = client.player;

        if (!isModEnabled || player == null || level == null) {
            return;
        }

        if (isLookingAtType(client, HitResult.Type.BLOCK)) {
            client.options.keyAttack.setDown(true);
        } else if (isLookingAtType(client, HitResult.Type.MISS)) {
            client.options.keyAttack.setDown(false);
        } else if (isLookingAtType(client, HitResult.Type.ENTITY)) {
            EntityHitResult entityHitResult = (EntityHitResult) client.hitResult;
            if (entityHitResult == null) {
                return;
            }

            Entity targetEntity = entityHitResult.getEntity();
            if (targetEntity instanceof LivingEntity) {
                if (player.getAttackStrengthScale(0.0F) >= 1.0F) {
                    InputConstants.Key attackKey = client.options.keyAttack.getDefaultKey();
                    KeyMapping.click(attackKey);
                }
            }
        }
    }

    private void renderAutoClickerStatus(GuiGraphics guiGraphics) {
        Minecraft client = Minecraft.getInstance();
        if (client.getDebugOverlay().showDebugScreen()) {
            return;
        }

        int x = 10;
        int y = 10;
        int red = 0xFF00FF00;
        int green = 0xFFFF0000;
        int color = isModEnabled ? red : green;
        String statusText = "Auto clicker: " + (isModEnabled ? "ON" : "OFF");

        guiGraphics.drawString(client.font, statusText, x, y, color, true);
    }

    private boolean isLookingAtType(Minecraft client, HitResult.Type type) {
        if (client.hitResult != null && client.player != null) {
            HitResult.Type hitResultType = client.hitResult.getType();
            return hitResultType == type;
        }

        return false;
    }
}