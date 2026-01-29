package com.dark.cmt.item;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public record GlowingHeatComponent(float glowingTemperature) {
    public static final Codec<GlowingHeatComponent> CODEC =
            Codec.FLOAT
                    .fieldOf("glowingtemperature")
                    .xmap(GlowingHeatComponent::new, GlowingHeatComponent::glowingTemperature)
                    .codec();

    public static final PacketCodec<RegistryByteBuf, GlowingHeatComponent> PACKET_CODEC =
            PacketCodec.of(
                    (value, buf) -> buf.writeFloat(value.glowingTemperature()),
                    buf -> new GlowingHeatComponent(buf.readFloat())
            );
}
