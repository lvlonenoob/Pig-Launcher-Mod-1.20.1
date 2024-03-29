package com.rellaxi.piglauncher.item;

import com.rellaxi.piglauncher.PigLauncher;
import com.rellaxi.piglauncher.item.custom.PigLauncherItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PigLauncher.MOD_ID);

    public static final RegistryObject<Item> PIGLAUNCHER = ITEMS.register("piglauncher",
            () -> new PigLauncherItem(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
