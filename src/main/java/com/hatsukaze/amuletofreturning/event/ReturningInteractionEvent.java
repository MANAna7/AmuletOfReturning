package com.hatsukaze.amuletofreturning.event;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import com.hatsukaze.amuletofreturning.item.AmuletOfReturning;
import com.hatsukaze.amuletofreturning.compat.WaystonesCompat;
import com.hatsukaze.amuletofreturning.compat.WaystoneBindData;
import com.hatsukaze.amuletofreturning.register.ModDataComponentRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.world.InteractionHand;

@EventBusSubscriber(modid = AmuletOfReturningMain.MODID)
public class ReturningInteractionEvent {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        // メインハンドの時だけ処理（オフハンドでも発火するので二重実行を防ぐ）
        if (event.getHand() != InteractionHand.MAIN_HAND) return;

        Player player = event.getEntity();
        if (player.level().isClientSide()) return;
        if (!player.isShiftKeyDown()) return;

        // Waystones入ってなければスキップ
        if (!WaystonesCompat.isLoaded()) return;

        // 手持ちがアミュレットか（メインハンド優先）
        ItemStack amulet = findHeldAmulet(player);
        if (amulet.isEmpty()) return;

        BlockPos clickedPos = event.getPos();

        // クリックしたブロックがWaystoneか
        if (!WaystonesCompat.isWaystoneBlock(player.level(), clickedPos)) return;

        // 現在のbindデータ取得
        WaystoneBindData currentBind = amulet.get(ModDataComponentRegister.WAYSTONE_BIND.get());
        ResourceLocation dimId = player.level().dimension().location();

        if (currentBind != null
                && currentBind.pos().equals(clickedPos)
                && currentBind.dimension().equals(dimId)) {
            // 同じWaystone → 解除
            amulet.remove(ModDataComponentRegister.WAYSTONE_BIND.get());
            player.displayClientMessage(
                    Component.translatable("message.amuletofreturning.amulet_unbound"), true);
            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
        } else {
            // 新規登録 or 上書き
            amulet.set(ModDataComponentRegister.WAYSTONE_BIND.get(),
                    new WaystoneBindData(clickedPos, dimId));
            player.displayClientMessage(
                    Component.translatable("message.amuletofreturning.amulet_bound"), true);
            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.PLAYERS, 1.0f, 1.0f);
        }

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }

    /**
     * メインハンド → オフハンドの順でアミュレットを探す。
     */
    private static ItemStack findHeldAmulet(Player player) {
        ItemStack mainHand = player.getMainHandItem();
        if (mainHand.getItem() instanceof AmuletOfReturning) return mainHand;

        ItemStack offHand = player.getOffhandItem();
        if (offHand.getItem() instanceof AmuletOfReturning) return offHand;

        return ItemStack.EMPTY;
    }
}