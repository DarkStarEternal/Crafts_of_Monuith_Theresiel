package com.dark.cmt.networking;

import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class CMTNetwork {

    public static void register() {

        ServerPlayNetworking.registerGlobalReceiver(
                C2SSmithingAnvilUpdatePacket.ID,
                (payload, context) -> {
                    C2SSmithingAnvilUpdatePacket.handle(
                            new C2SSmithingAnvilUpdatePacket(0, 0, new BlockPos(0, 0, 0)),
                            context.server(),
                            context.player()
                    );
                }
        );
    }

    public static void send(C2SSmithingAnvilUpdatePacket payload) {
        Objects.requireNonNull(payload, "Payload cannot be null");
        Objects.requireNonNull(payload.getPacketId(), "CustomPayload#getId() cannot return null for payload class: " + String.valueOf(payload.getClass()));
        if (MinecraftClient.getInstance().getNetworkHandler() != null) {
            MinecraftClient.getInstance().getNetworkHandler().sendPacket(payload);
        } else {
            throw new IllegalStateException("Cannot send packets when not in game!");
        }
    }
}
