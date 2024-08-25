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

    //1. Declaring the switch (Declaration): This is like saying "I'm going to have a
    // light switch."
    // You haven't installed it yet, but you've decided you're going to have one.

    //You're correct that this variable will eventually hold an instance of the KeyBinding class.
    // However, at this point in the code, it's just being declared - it doesn't actually
    // reference an instance yet. It's null until you assign a KeyBinding object to it.



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

                //2. Installing and configuring the switch (Initialization):
                //This is like actually installing the light switch.
                // You're deciding where to put it (on the keyboard), what it
                // looks like (the 'U' key), and what to label it
                // ("category.tutorial-mod.test" in the settings menu).

                //these are the 4 parameters that the key bindings helper hook fabric
                // provides needs as arguments to construct a new KeyBinding object
                // the first is literally for human languages, like english or spanish
                //if i wanted to support multi language. the second is for keyboard etc
                //the 3rd is for what key i am using to use my mnod by default
                //the fourth is the name displayed in the in game menu in Minecraft. like
                //literally if i go to minecraft settings in game, the name of this key
                //set to U is called "category.tutorial-mod.test"
        ));


        //in regards to crosshairHidden = !crosshairHidden; coming up below
        //So, step by step:
        //
        //If crosshairHidden is true:
        //
        //!crosshairHidden evaluates to false
        //So crosshairHidden becomes false
        //
        //
        //If crosshairHidden is false:
        //
        //!crosshairHidden evaluates to true
        //So crosshairHidden becomes true
        //It's like saying "whatever state the light is in,
        // switch it to the opposite state".


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hideHudKey.wasPressed()) {

                //so it's pretty much saying, while while (hideHudKey.wasPressed()) {
                //then switch to the opposite of whatever state it was
                // previously. meaning, every time we press the button,
                // we switch to the opposite state. thats what the boolean
                //below does

                crosshairHidden = !crosshairHidden;

                LOGGER.info("Crosshair hidden: " + crosshairHidden);

                //3. Setting up what the switch does:
                //This is like setting up the wiring for your light switch. Let's
                // break it down:
                //
                //ClientTickEvents.END_CLIENT_TICK.register(...): This is like saying
                // "Every time the house's electricity pulses (which happens many
                // times per second), check the following:"
                //while (hideHudKey.wasPressed()) { ... }: This is like saying "If
                // someone flipped the switch since the last pulse, do the following:"
                //crosshairHidden = !crosshairHidden;: This is the actual action of
                // turning the light on or off. If it was off, turn it on. If it was on,
                // turn it off.
                //LOGGER.info("Crosshair hidden: " + crosshairHidden);: This is
                // like having a little indicator light that shows whether the main
                // light is on or off.


                //So the overall behavior is:
                //
                //Every time you press the 'U' key:
                //
                //If the crosshair was visible, it becomes hidden.
                //If the crosshair was hidden, it becomes visible.
                //
                //
                //
                //It's like flipping a light switch:
                //
                //Press once: Light turns on (crosshair hides)
                //Press again: Light turns off (crosshair shows)
                //Press again: Light turns on (crosshair hides)






                //5a. ClientTickEvents is indeed a Minecraft class. It's like the house's
                // electrical system that's constantly pulsing.
                //
                //You're correct about .wasPressed(). It's a method that checks if the
                // key was pressed since the last check. The actual logic of what pressing
                // 'U' does is right here in this code - it toggles the crosshairHidden
                // boolean.
            }
        });
    }

    public static boolean isCrosshairHidden() {
        return crosshairHidden;
    }
}

