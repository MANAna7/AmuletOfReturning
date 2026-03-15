package com.hatsukaze.amuletofreturning;

import com.hatsukaze.amuletofreturning.effect.AmuletReturnParticle;
import com.hatsukaze.amuletofreturning.register.ModParticleRegister;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import static com.hatsukaze.amuletofreturning.AmuletOfReturningMain.MODID;

@Mod(value = MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class AmuletOfReturningClientHandler {

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleRegister.AMULET_OF_RETURN.get(),
                AmuletReturnParticle.Provider::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        AmuletOfReturningMain.LOGGER.info("client setup");
    }
}
