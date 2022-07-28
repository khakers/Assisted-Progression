package com.pauljoda.assistedprogression.data;

import com.pauljoda.assistedprogression.lib.Reference;
import com.pauljoda.assistedprogression.lib.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
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
public class BlockStateGenerator extends BlockStateProvider {

    public BlockStateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Reference.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Spawner Frame
        simpleBlock(Registration.SPAWNER_FRAME_BLOCK.get());

        // Sun
        var sunModel =
                models().getBuilder("block/sun")
                        .parent(models().getExistingFile(mcLoc("block")))
                        .element().from(6F, 6F, 6F).to(10F, 10F, 10F)
                        .allFaces(((direction, faceBuilder) -> {
                            faceBuilder.texture("#sun");
                        }))
                        .end()
                        .texture("sun", modLoc("block/sun"))
                        .texture("all", modLoc("block/sun"))
                        .texture("particle", modLoc("block/sun"));
        getVariantBuilder(Registration.SUN_BLOCK.get()).partialState().setModels(new ConfiguredModel(sunModel));
    }
}