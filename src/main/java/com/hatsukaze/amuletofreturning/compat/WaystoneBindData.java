package com.hatsukaze.amuletofreturning.compat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

/**
 * アミュレットに紐づけるWaystoneの座標情報。
 * - pos:        Waystoneブロックのワールド座標
 * - dimension:  ディメンションID（例: "minecraft:overworld"）
 */
public record WaystoneBindData(BlockPos pos, ResourceLocation dimension) {

    public static final Codec<WaystoneBindData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockPos.CODEC.fieldOf("pos").forGetter(WaystoneBindData::pos),
                    ResourceLocation.CODEC.fieldOf("dimension").forGetter(WaystoneBindData::dimension)
            ).apply(instance, WaystoneBindData::new)
    );
}