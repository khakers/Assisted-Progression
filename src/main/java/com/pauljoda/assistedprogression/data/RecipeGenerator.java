package com.pauljoda.assistedprogression.data;

import com.pauljoda.assistedprogression.lib.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

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
public class RecipeGenerator extends RecipeProvider {

    public RecipeGenerator(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        // Items -------------------------------------------------------------------------------------------------------
        // Spawner Relocator
        ShapedRecipeBuilder
                .shaped(Registration.SPAWNER_RELOCATOR_ITEM.get())
                .define('e', Tags.Items.ENDER_PEARLS)
                .define('i', Tags.Items.INGOTS_IRON)
                .define('s', Tags.Items.SLIMEBALLS)
                .pattern("  s")
                .pattern(" i ")
                .pattern("e  ")
                .unlockedBy("has_ender_pearls", has(Tags.Items.ENDER_PEARLS))
                .save(consumer);

        // Parashoes
        ShapedRecipeBuilder
                .shaped(Registration.PARASHOES_ITEM.get())
                .pattern("F F")
                .pattern("SBS")
                .pattern("C C")
                .define('F', Tags.Items.FEATHERS)
                .define('S', Tags.Items.STRING)
                .define('B', Items.LEATHER_BOOTS)
                .define('C', Items.WHITE_CARPET)
                .unlockedBy("has_boots", has(Items.LEATHER_BOOTS))
                .save(consumer);

        // Net
        ShapedRecipeBuilder
                .shaped(Registration.NET_ITEM.get())
                .pattern("S S")
                .pattern(" I ")
                .pattern("S S")
                .define('S', Tags.Items.STRING)
                .define('I', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_string", has(Tags.Items.STRING))
                .save(consumer);

        // Launcher
        ShapedRecipeBuilder
                .shaped(Registration.NET_LAUNCHER_ITEM.get())
                .pattern("   ")
                .pattern("IIB")
                .pattern(" IR")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('B', Tags.Items.STORAGE_BLOCKS_IRON)
                .define('R', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                .unlockedBy("has_redstone", has(Tags.Items.STORAGE_BLOCKS_REDSTONE))
                .save(consumer);

        // Blocks ------------------------------------------------------------------------------------------------------


        ShapedRecipeBuilder
                .shaped(Registration.SPAWNER_FRAME_BLOCK.get())
                .pattern("ODO")
                .pattern("IEI")
                .pattern("ODO")
                .define('O', Tags.Items.OBSIDIAN)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('E', Items.END_CRYSTAL)
                .unlockedBy("has_endcrystal", has(Items.END_CRYSTAL))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(Registration.SUN_BLOCK.get())
                .pattern("GMG")
                .pattern("MDM")
                .pattern("GMG")
                .define('G', Tags.Items.DUSTS_GLOWSTONE)
                .define('M', Items.BONE_MEAL)
                .define('D', Items.LANTERN)
                .unlockedBy("hasLantern", has(Items.LANTERN))
                .save(consumer);
    }
}
