package com.hatsukaze.amuletofreturning.register;

import com.hatsukaze.amuletofreturning.AmuletOfReturningMain;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModParticleRegister {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, AmuletOfReturningMain.MODID);

    public static final Supplier<SimpleParticleType> AMULET_OF_RETURN =
            PARTICLES.register("amulet_of_returning",
                    () -> new SimpleParticleType(false));
}