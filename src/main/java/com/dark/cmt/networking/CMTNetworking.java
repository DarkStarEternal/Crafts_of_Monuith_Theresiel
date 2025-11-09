package com.dark.cmt.network;

import com.dark.cmt.CMT;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.impl.networking.CustomPayloadTypeProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.PacketByteBuf;

public class CMTNetworking {

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(TransformItemC2SPacket.ID, TransformItemC2SPacket::receive);
    }
}
