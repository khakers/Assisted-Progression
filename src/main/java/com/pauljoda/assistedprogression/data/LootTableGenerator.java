package com.pauljoda.assistedprogression.data;

import com.pauljoda.assistedprogression.lib.Registration;
import com.pauljoda.nucleus.data.BaseLootTableGenerator;
import net.minecraft.data.DataGenerator;

/**
 * This file was created for Nucleus
 * <p>
 * Nucleus is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 6/7/2022
 */
public class LootTableGenerator extends BaseLootTableGenerator {

    public LootTableGenerator(DataGenerator dataGenerator) {
        super(dataGenerator, "Assisted Progression Loot");
    }

    @Override
    protected void addTables() {

        // Spawner Frame
        lootTables.put(Registration.SPAWNER_FRAME_BLOCK.get(),
                createSimpleTable("spawner_frame", Registration.SPAWNER_FRAME_BLOCK.get()));

        // Sun
        lootTables.put(Registration.SUN_BLOCK.get(),
                createSimpleTable("sun", Registration.SUN_BLOCK.get()));
    }
}
