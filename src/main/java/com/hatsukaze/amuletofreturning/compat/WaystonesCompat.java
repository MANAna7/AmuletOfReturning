package com.hatsukaze.amuletofreturning.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

/**
 * Waystones modとのソフト依存を隔離するクラス。
 * Waystones が入っていなければ全て false / no-op で返す。
 */
public final class WaystonesCompat {

    private static final String WAYSTONES_MODID = "waystones";

    /** Waystones modがロードされているか */
    public static boolean isLoaded() {
        return ModList.get().isLoaded(WAYSTONES_MODID);
    }

    /**
     * 指定座標のブロックがWaystoneか判定する。
     * BuiltInRegistries から直接レジストリ名を引く。
     */
    public static boolean isWaystoneBlock(Level level, BlockPos pos) {
        if (!isLoaded()) return false;

        Block block = level.getBlockState(pos).getBlock();
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);

        // waystones:waystone, waystones:mossy_waystone, waystones:sandy_waystone,
        // waystones:deepslate_waystone, waystones:blackstone_waystone, waystones:endstone_waystone etc.
        return blockId.getNamespace().equals(WAYSTONES_MODID)
                && blockId.getPath().contains("waystone");
    }

    private WaystonesCompat() {}
}