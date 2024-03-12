package com.rellaxi.piglauncher.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PigLauncherItem extends Item {
    private boolean loaded;
    public PigLauncherItem(Properties pProperties) {
        super(pProperties);
        loaded = false;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (!pInteractionTarget.level().isClientSide()) {
            if (pInteractionTarget.getType() == EntityType.PIG) {
                pInteractionTarget.discard();
                loaded = !loaded;
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
