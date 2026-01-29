package com.dark.cmt.item;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public record CurrentHeatComponent(float temperature) {
    public static final Codec<CurrentHeatComponent> CODEC =
            Codec.FLOAT
                    .fieldOf("temperature")
                    .xmap(CurrentHeatComponent::new, CurrentHeatComponent::temperature)
                    .codec();

    public static final PacketCodec<RegistryByteBuf, CurrentHeatComponent> PACKET_CODEC =
            PacketCodec.of(
                    (value, buf) -> buf.writeFloat(value.temperature()),
                    buf -> new CurrentHeatComponent(buf.readFloat())
            );
}
