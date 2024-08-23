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

	//we declare these outside of the methods below so they can be used anywhere in our
	//code. KeyBinding is a class from minecraft source code mapped using yarn
	//you can think of these kind of like const being declared early in a react app

	@Override
	public void onInitializeClient() {

		//on initialize is a method from ClientModInitializer that is the entry point
		//for fabric there are 3 main initializers, for client, server, and both
		LOGGER.info("Hello Fabric world!");

		hideHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.tutorial-mod.hide_hud",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_U,
				"category.tutorial-mod.test"

				//these are the 4 parameters that the key bindings helper hook fabric
				// provides needs as arguments to construct a new KeyBinding object
				// the first is literally for human languages, like english or spanish
				//if i wanted to support multi language. the second is for keyboard etc
				//the 3rd is for what key i am using to use my mnod by default
				//the fourth is the name displayed in the in game menu in Minecraft. like
				//literally if i go to minecraft settings in game, the name of this key
				//set to U is called "category.tutorial-mod.test"
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


