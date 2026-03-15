package com.hatsukaze.amuletofreturning;

import com.hatsukaze.amuletofreturning.register.*;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;


@SuppressWarnings("SpellCheckingInspection")
@Mod(AmuletOfReturningMain.MODID)
public class AmuletOfReturningMain {
    public static final String MODID = "amuletofreturning";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AmuletOfReturningMain(IEventBus modEventBus, ModContainer modContainer) {
        ModAccessoriesRegister.ITEMS.register(modEventBus);
        ModParticleRegister.PARTICLES.register(modEventBus);
        ModNetworkRegister.register(modEventBus);
        ModCreativeTabRegister.CREATIVE_MODE_TABS.register(modEventBus);
        ModDataComponentRegister.DATA_COMPONENTS.register(modEventBus);


//        modEventBus.addListener(AmuletOfReturningClientHandler::registerParticles);
    }
}