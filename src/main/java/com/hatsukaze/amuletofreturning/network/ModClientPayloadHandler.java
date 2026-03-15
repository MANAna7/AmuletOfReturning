package com.hatsukaze.amuletofreturning.network;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import com.hatsukaze.amuletofreturning.effect.AmuletOverlayRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.hatsukaze.amuletofreturning.effect.AmuletOverlayRenderer.OVERLAY_DURATION;

@OnlyIn(Dist.CLIENT)
public class ModClientPayloadHandler {
    public static int overlayTicks = 0;

    public static void handle(AmuletOfReturningActivatedPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            AmuletOfReturningMain.LOGGER.info("overlay start: {}", AmuletOverlayRenderer.OVERLAY_DURATION);
            overlayTicks = OVERLAY_DURATION; // 108

        });
    }
}
