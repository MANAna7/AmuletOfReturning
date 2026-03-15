package com.hatsukaze.amuletofreturning.datagen;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

//Jsonを生成するdatagenクラス。jsonを作らない処理系はregister系に記載する
@EventBusSubscriber(modid = AmuletOfReturningMain.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // --- Server ---
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        // TODO: LootTableProvider, TagProviders, etc.
    }
}
