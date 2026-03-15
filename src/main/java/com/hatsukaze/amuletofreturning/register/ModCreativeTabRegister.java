package com.hatsukaze.amuletofreturning.register;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabRegister {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AmuletOfReturningMain.MODID);

    public static final Supplier<CreativeModeTab> AMULETOFRETURNING_TAB = CREATIVE_MODE_TABS.register("amuletofreturning_tab",
            () -> CreativeModeTab.builder()
//                    .title(Component.translatable("itemGroup." + AmuletOfReturningMain.MODID + ".amuletofreturning_tab"))
                    .title(Component.translatable("amuletOfReturning"))
                    .icon(() -> new ItemStack(ModAccessoriesRegister.AMULET_OF_RETURN.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModAccessoriesRegister.AMULET_OF_RETURN.get());
                        // アクセサリー増えたらここに足すだけ
                    })
                    .build());
}