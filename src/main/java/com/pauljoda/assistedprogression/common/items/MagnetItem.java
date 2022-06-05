package com.pauljoda.assistedprogression.common.items;

import com.mojang.math.Vector3d;
import com.pauljoda.assistedprogression.lib.Registration;
import com.pauljoda.nucleus.common.IAdvancedToolTipProvider;
import com.pauljoda.nucleus.util.ClientUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This file was created for Nucleus
 * <p>
 * Nucleus is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Paul Davis - pauljoda
 * @since 6/5/2022
 */
public class MagnetItem extends Item implements IAdvancedToolTipProvider {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    // Set Values
    protected static final int RANGE = 10;
    protected static final float ATTRACT_SPEED = 0.0075f;

    // Tags
    protected static final String ACTIVE = "Magnet_Active";

    // Value of magnet
    protected boolean isCheapMagnet = true;

    public MagnetItem() {
        super(new Properties()
                .stacksTo(1)
                .tab(Registration.tabAssistedProgression));
    }

    /*******************************************************************************************************************
     * Item                                                                                                            *
     *******************************************************************************************************************/

    /**
     * Only show foil when active
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains(ACTIVE) && stack.getTag().getBoolean(ACTIVE);
    }

    /**
     * Allow player to shift right click to toggle mode
     */
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player,
                                                           @NotNull InteractionHand hand) {
        // Toggle mode
        if (player.isShiftKeyDown() && player.getItemInHand(hand).getItem() instanceof MagnetItem) {
            var stack = player.getItemInHand(hand);
            // Create tag and swap in
            var tag = new CompoundTag();
            tag.putBoolean(ACTIVE, (!stack.hasTag() || !stack.getTag().getBoolean(ACTIVE)));
            stack.setTag(tag);
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
        }
        return super.use(level, player, hand);
    }

    /**
     * Perform the magnet action
     */
    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level,
                              @NotNull Entity entity, int itemSlot, boolean isSelected) {
        // If we are a player on the server
        if (!level.isClientSide && stack.hasTag() &&
                stack.getTag().contains(ACTIVE) &&
                stack.getTag().getBoolean(ACTIVE) &&
                entity instanceof Player player) {

            AABB bb = new AABB(
                    player.getX() - RANGE, player.getY() - RANGE, player.getX() - RANGE,
                    player.getX() + RANGE, player.getY() + RANGE, player.getZ() + RANGE);

            // Start with items
            var entitiesInBox = new ArrayList<Entity>(level.getEntitiesOfClass(ItemEntity.class, bb)
                    .stream().filter(item -> !item.hasPickUpDelay()).toList());

            // XP
            entitiesInBox.addAll(level.getEntitiesOfClass(ExperienceOrb.class, bb));

            // If this is the cheaper magnet, grab bad things
            if(isCheapMagnet) {
                // Explosive Things
                entitiesInBox.addAll(level.getEntitiesOfClass(Creeper.class, bb));
                entitiesInBox.addAll(level.getEntitiesOfClass(PrimedTnt.class, bb));

                // Projectiles, need to expand the box
                bb.expandTowards(RANGE * 2, RANGE * 2, RANGE * 2);
                entitiesInBox.addAll(level.getEntitiesOfClass(Arrow.class, bb));
                entitiesInBox.addAll(level.getEntitiesOfClass(ThrownTrident.class, bb));
                entitiesInBox.addAll(level.getEntitiesOfClass(ShulkerBullet.class, bb));
                entitiesInBox.addAll(level.getEntitiesOfClass(Fireball.class, bb));
                entitiesInBox.addAll(level.getEntitiesOfClass(DragonFireball.class, bb));
            }

            // Apply motion
            if(!entitiesInBox.isEmpty()) {
                for (Entity entityToMove : entitiesInBox) {
                    // Create a vector pointing to the play
                    Vec3 motionVector = new Vec3(
                            player.getX() - entityToMove.getX(),
                            player.getY() - entityToMove.getY(),
                            player.getZ() - entityToMove.getZ()
                    );

                    // Normalize
                    if(motionVector.length() > 1)
                        motionVector.normalize();

                    // Arrows need more pull
                    boolean isArrow = entityToMove instanceof Arrow;
                    float speed = ATTRACT_SPEED + (isArrow ? 0.2F : 0.0F);

                    // Apply speed
                    motionVector.add(new Vec3(speed, speed, speed));

                    // Apply to entity
                    entityToMove.setDeltaMovement(motionVector);
                }
            }
        }
    }

    /*******************************************************************************************************************
     * IAdvancedToolTipProvider                                                                                        *
     *******************************************************************************************************************/

    @Nullable
    @Override
    public List<String> getAdvancedToolTip(@NotNull ItemStack itemStack) {
        return List.of(ClientUtils.translate("item_cheap_magnet.desc"));
    }
}
