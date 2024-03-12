package com.rellaxi.piglauncher.item;

import com.rellaxi.piglauncher.PigLauncher;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.PIGLAUNCHER.get(), new ResourceLocation(PigLauncher.MOD_ID, "loaded"),
                (pStack, pLevel, pEntity, pSeed) -> {
                    boolean loaded;
                    if (pStack.hasTag()) {
                        loaded = pStack.getTag().getBoolean("piglauncher.loaded");
                    }else {
                        loaded = false;
                    }
                    return loaded ? 1 : 0;
                });
    }


}
