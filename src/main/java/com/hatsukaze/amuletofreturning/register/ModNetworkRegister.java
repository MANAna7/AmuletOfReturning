package com.hatsukaze.amuletofreturning.register;


import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import com.hatsukaze.amuletofreturning.network.ModClientPayloadHandler;
import com.hatsukaze.amuletofreturning.network.AmuletOfReturningActivatedPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModNetworkRegister {
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ModNetworkRegister::onRegisterPayloads);
    }

    private static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(AmuletOfReturningMain.MODID);
        registrar.playToClient(
                AmuletOfReturningActivatedPayload.TYPE,
                AmuletOfReturningActivatedPayload.STREAM_CODEC,
                ModClientPayloadHandler::handle
        );
    }
}
