package com.dark.cmt.networking;

import com.dark.cmt.CMT;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record C2SSmithingAnvilUIChangePayload(boolean toCombiner, int blockX, int blockY, int blockZ) implements CustomPayload {
    public static final Identifier IDENT = Identifier.of(CMT.MODID, "smithinganviluichangepacket");
    public static final Id<C2SSmithingAnvilUIChangePayload> ID =
            new Id<>(IDENT);

    public static final net.minecraft.network.codec.PacketCodec<RegistryByteBuf, C2SSmithingAnvilUIChangePayload> CODEC =
            net.minecraft.network.codec.PacketCodec.tuple(
                    PacketCodecs.BOOL,
                    C2SSmithingAnvilUIChangePayload::toCombiner,
                    PacketCodecs.INTEGER,
                    C2SSmithingAnvilUIChangePayload::blockX,
                    PacketCodecs.INTEGER,
                    C2SSmithingAnvilUIChangePayload::blockY,
                    PacketCodecs.INTEGER,
                    C2SSmithingAnvilUIChangePayload::blockZ,
                    C2SSmithingAnvilUIChangePayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void handle(C2SSmithingAnvilUIChangePayload payload, MinecraftServer server, ServerPlayerEntity player) {
        server.execute(() -> {
            if (player.getWorld().getBlockEntity(new BlockPos(payload.blockX(), payload.blockY(), payload.blockZ())) instanceof SmithingAnvilBlockEntity smithinBE) {
                if (payload.toCombiner()) {
                    smithinBE.setMode(SmithingAnvilBlockEntity.AnvilMode.COMBINE);
                } else {
                    smithinBE.setMode(SmithingAnvilBlockEntity.AnvilMode.PARTS);
                }
                player.openHandledScreen(smithinBE);
                smithinBE.markDirty();
                smithinBE.sync();
            }
        });
    }
}
