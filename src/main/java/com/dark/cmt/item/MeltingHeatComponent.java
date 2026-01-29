package com.dark.cmt.item;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public record MeltingHeatComponent(float meltingTemperature) {
    public static final Codec<MeltingHeatComponent> CODEC =
            Codec.FLOAT
                    .fieldOf("meltingTemperature")
                    .xmap(MeltingHeatComponent::new, MeltingHeatComponent::meltingTemperature)
                    .codec();

    public static final PacketCodec<RegistryByteBuf, MeltingHeatComponent> PACKET_CODEC =
            PacketCodec.of(
                    (value, buf) -> buf.writeFloat(value.meltingTemperature()),
                    buf -> new MeltingHeatComponent(buf.readFloat())
            );
}
