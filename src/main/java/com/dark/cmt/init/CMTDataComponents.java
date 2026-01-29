package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.item.CurrentHeatComponent;
import com.dark.cmt.item.GlowingHeatComponent;
import com.dark.cmt.item.MeltingHeatComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTDataComponents {
    public static final ComponentType<CurrentHeatComponent> CURRENTHEAT =
            Registry.register(
                    Registries.DATA_COMPONENT_TYPE,
                    Identifier.of(CMT.MODID, "current_heat"),
                    ComponentType.<CurrentHeatComponent>builder()
                            .codec(CurrentHeatComponent.CODEC)
                            .packetCodec(CurrentHeatComponent.PACKET_CODEC)
                            .build()
            );

    public static final ComponentType<GlowingHeatComponent> GLOWINGHEAT =
            Registry.register(
                    Registries.DATA_COMPONENT_TYPE,
                    Identifier.of(CMT.MODID, "glowing_heat"),
                    ComponentType.<GlowingHeatComponent>builder()
                            .codec(GlowingHeatComponent.CODEC)
                            .packetCodec(GlowingHeatComponent.PACKET_CODEC)
                            .build()
            );

    public static final ComponentType<MeltingHeatComponent> MELTINGHEAT =
            Registry.register(
                    Registries.DATA_COMPONENT_TYPE,
                    Identifier.of(CMT.MODID, "melting_heat"),
                    ComponentType.<MeltingHeatComponent>builder()
                            .codec(MeltingHeatComponent.CODEC)
                            .packetCodec(MeltingHeatComponent.PACKET_CODEC)
                            .build()
            );

    public static void register() {

    }
}
