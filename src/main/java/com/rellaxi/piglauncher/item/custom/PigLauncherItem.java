package com.rellaxi.piglauncher.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class PigLauncherItem extends Item {
    private boolean loaded;
    private int ticker;
    public PigLauncherItem(Properties pProperties) {
        super(pProperties);
        loaded = false;
        ticker = 0;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (loaded) {
            if (ticker > 0) {
                ticker--;
            }
        }
        else {
            ticker = 20;
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (ticker == 0) {
            if (loaded) {
                ArrowItem arrowitem = (ArrowItem) (Items.ARROW);
                AbstractArrow abstractarrow = arrowitem.createArrow(pLevel, new ItemStack(Items.ARROW), pPlayer);
                abstractarrow.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 3.0F, 1.0F);
                abstractarrow.pickup = AbstractArrow.Pickup.DISALLOWED;
                pLevel.addFreshEntity(abstractarrow);
                loaded = false;
                toTag(pPlayer.getItemInHand(pUsedHand), loaded);
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (!pInteractionTarget.level().isClientSide()) {
            if (pInteractionTarget.getType() == EntityType.PIG) {
                pInteractionTarget.discard();
                loaded = true;
                toTag(pPlayer.getItemInHand(pUsedHand), loaded);
            }
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }
    public void toTag(ItemStack stack, boolean armed) {

        CompoundTag loaded = new CompoundTag();
        loaded.putBoolean("piglauncher.loaded", armed);
        stack.setTag(loaded);
    }
}
