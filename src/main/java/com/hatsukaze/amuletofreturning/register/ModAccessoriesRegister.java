package com.hatsukaze.amuletofreturning.register;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import com.hatsukaze.amuletofreturning.item.AmuletOfReturning;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModAccessoriesRegister {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, AmuletOfReturningMain.MODID);

    public static final Supplier<Item> AMULET_OF_RETURN =
            register("amulet_of_returning", AmuletOfReturning::new);

    //Supplier　ファクトリークラス
    private static <T extends Item> Supplier<T> register(
            String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }
}
