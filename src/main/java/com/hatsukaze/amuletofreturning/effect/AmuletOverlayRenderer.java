package com.hatsukaze.amuletofreturning.effect;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import com.hatsukaze.amuletofreturning.register.ModAccessoriesRegister;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import static com.hatsukaze.amuletofreturning.network.ModClientPayloadHandler.overlayTicks;
import static net.minecraft.client.renderer.texture.OverlayTexture.*;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AmuletOfReturningMain.MODID, value = Dist.CLIENT)
public class AmuletOverlayRenderer {

    // ── オーバーレイ設定 ──────────────────
    // ModClientPayloadHandlerでoverlayDurationを参照中
    public static final int   OVERLAY_DURATION   = 140; // 全体フレーム数
    private static final int   PHASE_IN_END       = 14;  // 登場終わり
    private static final int   PHASE_HOLD_END     = 40;  // 慣性終わり
    private static final int PHASE_STILL_END  = 86;  // 無回転ここまで（in+hold）
    private static final float ROTATION_SPEED     = 58.0f; // Y軸回転速度
    private static final float MAX_SCALE          = 19.0f;

    // ─────────────────────────────────────

    private static float easeOut(float t) { return 1.0f - (1.0f - t) * (1.0f - t); }
    private static float easeIn(float t)  { return t * t; }
    private static float easeInOut(float t) {
        return t < 0.5f ? 2 * t * t : 1 - (-2 * t + 2) * (-2 * t + 2) / 2;
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (overlayTicks <= 0) return;

        Minecraft mc = Minecraft.getInstance();
        GuiGraphics graphics = event.getGuiGraphics();
        int screenW = mc.getWindow().getGuiScaledWidth();
        int screenH = mc.getWindow().getGuiScaledHeight();

        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                AmuletOfReturningMain.MODID, "textures/item/amulet_of_returning.png"
        );


        int elapsed = OVERLAY_DURATION - overlayTicks; // 経過フレーム

        float scale;
        float rotY;
        float rotX = 0;
        float offsetX = 0;
        float offsetY = 0;

        if (elapsed < PHASE_IN_END) {
            // フェーズ1：登場
            float t = elapsed / (float) PHASE_IN_END;
            offsetX = 50.0f * (1.0f - easeOut(t)); // 左から来る
            offsetY = 20.0f * (1.0f - easeOut(t)); // 左から来る
            scale = 0.5f + MAX_SCALE * easeOut(t);
            rotY = elapsed * ROTATION_SPEED;

        } else if (elapsed < PHASE_HOLD_END) {
            // フェーズ2：慣性で止まる
            float t = (elapsed - PHASE_IN_END) / (float)(PHASE_HOLD_END - PHASE_IN_END);
            scale = MAX_SCALE;
            float overshoot = (float) Math.sin(t * Math.PI) * 30.0f;
            rotY = PHASE_IN_END * ROTATION_SPEED * (1.0f - easeIn(t)) - overshoot;
            offsetX = -20.0f * (1.0f - easeIn(t));

        } else if (elapsed < PHASE_STILL_END) {
            // フェーズ3：スローで逆回転収束
            float t = (elapsed - PHASE_HOLD_END) / (float)(PHASE_STILL_END - PHASE_HOLD_END);
            scale = MAX_SCALE;
            float drift = elapsed * 0.03f;
            rotY = -20.0f * easeInOut(t) - drift;
            float sway = (float) Math.sin(elapsed * 0.05f) * 1.1f;
            graphics.pose().mulPose(Axis.ZP.rotationDegrees(sway));
        } else {
            // フェーズ4：退場
            float t = (elapsed - PHASE_STILL_END) / (float)(OVERLAY_DURATION - PHASE_STILL_END);
            scale = MAX_SCALE * (1.0f - easeIn(t));
            rotY = -(elapsed - PHASE_STILL_END) * ROTATION_SPEED;
            offsetX = 50.0f * easeIn(t); // 右に飛んでいく
            offsetY = 15.0f * (1.0f - easeOut(t)); // 左から来るo

        }

        graphics.pose().pushPose();
        graphics.pose().translate(screenW / 2.0f + offsetX, screenH / 2.0f + offsetY, 100);
        graphics.pose().scale(scale * 8, -scale * 8, scale * 8);
        graphics.pose().mulPose(Axis.YP.rotationDegrees(rotY));


        mc.getItemRenderer().renderStatic(
                new ItemStack(ModAccessoriesRegister.AMULET_OF_RETURN.get()),
                ItemDisplayContext.GUI,
                15728880,
                NO_OVERLAY,
                graphics.pose(),
                mc.renderBuffers().bufferSource(),
                mc.level,
                0
        );
        mc.renderBuffers().bufferSource().endBatch();
        graphics.pose().popPose();
        overlayTicks--;

    }
}