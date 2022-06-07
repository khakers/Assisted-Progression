package com.pauljoda.assistedprogression.data;

import com.pauljoda.assistedprogression.lib.Reference;
import com.pauljoda.assistedprogression.lib.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
public class ItemModelGenerator extends ItemModelProvider {

    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Items -------------------------------------------------------------------------------------------------------
        // Spawner Relocator
        singleTexture(Registration.SPAWNER_RELOCATOR_ITEM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("items/spawner_relocator"));

        // Magnet
        singleTexture(Registration.MAGNET_ITEM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("items/magnet"));

        // Electric Magnet
        singleTexture(Registration.ELECTRIC_MAGNET_ITEM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("items/electro_magnet"));

        // Parashoes
        singleTexture(Registration.PARASHOES_ITEM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("items/parashoes"));

        // Climbing Gloves
        singleTexture(Registration.CLIMBING_GLOVES_ITEM.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", modLoc("items/climbing_gloves"));

        // Blocks ------------------------------------------------------------------------------------------------------

        // Ender Pad
        withExistingParent(Registration.ENDER_PAD_BLOCK.get().getRegistryName().getPath(), modLoc("block/ender_pad"));

        // Player Plate
        withExistingParent(Registration.PLAYER_PLATE_BLOCK.get().getRegistryName().getPath(), modLoc("block/player_plate"));
    }
}
