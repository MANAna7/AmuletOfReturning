package com.hatsukaze.amuletofreturning.register;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;import com.hatsukaze.amuletofreturning.compat.WaystoneBindData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponentRegister {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, AmuletOfReturningMain.MODID);

    public static final Supplier<DataComponentType<WaystoneBindData>> WAYSTONE_BIND = DATA_COMPONENTS.register(
            "waystone_bind",
            () -> DataComponentType.<WaystoneBindData>builder()
                    .persistent(WaystoneBindData.CODEC)
                    .build()
    );
}