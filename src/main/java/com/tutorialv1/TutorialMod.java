package com.tutorialv1;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("tutorial-mod");
	private static KeyBinding hideHudKey;

	public static boolean crosshairHidden = false;


	@Override
	public void onInitializeClient() {
		LOGGER.info("Hello Fabric world!");

		hideHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.tutorial-mod.hide_hud",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_U,
				"category.tutorial-mod.test"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (hideHudKey.wasPressed()) {
				crosshairHidden = !crosshairHidden;
				LOGGER.info("Crosshair hidden: " + crosshairHidden);
			}
		});
	}

	public static boolean isCrosshairHidden() {
		return crosshairHidden;
	}
}