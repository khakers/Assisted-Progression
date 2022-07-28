package com.pauljoda.assistedprogression.data;

import com.pauljoda.assistedprogression.lib.Reference;
import com.pauljoda.assistedprogression.lib.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

/**
 * This file was created for Nucleus
 * <p>
 * Nucleus is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 6/4/2022
 */
public class TranslationGenerator extends LanguageProvider {

    public TranslationGenerator(DataGenerator gen, String locale) {
        super(gen, Reference.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        // Creative Tab
        add("itemGroup." + Reference.MOD_ID, "Assisted Progression");
        add("itemGroup." + Reference.MOD_ID + "_pipettes", "Assisted Progression : Pipettes");

        // Items -------------------------------------------------------------------------------------------------------
        // Spawner Relocator
        addWithDescription(Registration.SPAWNER_RELOCATOR_ITEM
                , "Spawner Relocator",
                "Hold right click and release while looking at a spawner to pick it up, hold right click while aiming at a block to place it back down");
        add("assisted_progression.text.spawnerRelocator.type", "%s%sType: %s");

        // Parashoes
        add(Registration.PARASHOES_ITEM.get(), "Parashoes");
        add("parashoes.desc", "Wear these shoes to slow your decent");

        // Nets
        addWithDescription(Registration.NET_ITEM,
                "Mob Net",
                "Use with Mob Net Launcher to launch nets that capture mobs, right click to place captured mobs");
        add("assisted_progression.text.net.stored", "Entity Stored: ");

        // Net Launcher
        addWithDescription(Registration.NET_LAUNCHER_ITEM,
                "Mob Net Launcher",
                "Launches mob nets from inventory to capture mobs");

        // Blocks ------------------------------------------------------------------------------------------------------

        // Spawner Frame
        add(Registration.SPAWNER_FRAME_BLOCK.get(), "Spawner Frame");
        add("spawner_frame.desc", "Right click with a mob net with a captured mob to create a spawner of that type");

        // Sun
        add(Registration.SUN_BLOCK.get(), "Miniature Sun");
        add("sun.desc", "Place above plants to help them grow. Will service a radius of 3 blocks around and 3 blocks below");

        // Entities ----------------------------------------------------------------------------------------------------
        add(Registration.NET_ENTITY.get(), "Mob Net");
    }

    private void addWithDescription(RegistryObject<Item> item, String name, String desc) {
        add(item.get(), name);
        add(item.get().getRegistryName() + ".desc", desc);
    }
}
