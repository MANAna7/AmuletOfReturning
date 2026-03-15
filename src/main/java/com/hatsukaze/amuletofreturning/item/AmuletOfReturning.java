package com.hatsukaze.amuletofreturning.item;

import com.hatsukaze.amuletofreturning.compat.WaystoneBindData;
import com.hatsukaze.amuletofreturning.compat.WaystonesCompat;
import com.hatsukaze.amuletofreturning.register.ModDataComponentRegister;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class AmuletOfReturning extends Item implements ICurioItem {

    public AmuletOfReturning() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        // 基本説明
        tooltipComponents.add(Component.translatable("tooltip.amuletofreturning.amulet_of_returning.desc")
                .withStyle(ChatFormatting.GRAY));

        // Waystone紐付け情報
        WaystoneBindData bindData = stack.get(ModDataComponentRegister.WAYSTONE_BIND.get());
        // Waystone紐付け情報（Waystones modが入っている場合のみ表示）
        if (WaystonesCompat.isLoaded()) {
            if (bindData != null) {
                BlockPos pos = bindData.pos();
                tooltipComponents.add(Component.translatable("tooltip.amuletofreturning.amulet_of_returning.bound",
                                pos.getX(), pos.getY(), pos.getZ())
                        .withStyle(ChatFormatting.DARK_PURPLE));
//            tooltipComponents.add(Component.literal("  " + bindData.dimension().toString())
//                    .withStyle(ChatFormatting.DARK_GRAY));
            } else {
                tooltipComponents.add(Component.translatable("tooltip.amuletofreturning.amulet_of_returning.unbound")
                        .withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }
}