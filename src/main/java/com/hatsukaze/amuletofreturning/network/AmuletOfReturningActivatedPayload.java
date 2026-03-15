package com.hatsukaze.amuletofreturning.network;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record AmuletOfReturningActivatedPayload() implements CustomPacketPayload {

        public static final Type<AmuletOfReturningActivatedPayload> TYPE =
                new Type<>(ResourceLocation.fromNamespaceAndPath(AmuletOfReturningMain.MODID, "amulet_of_returning_activated"));

        public static final StreamCodec<ByteBuf, AmuletOfReturningActivatedPayload> STREAM_CODEC =
                StreamCodec.unit(new AmuletOfReturningActivatedPayload());

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
}

