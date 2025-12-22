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

public record C2SSmithingAnvilCraftStepValidationPayload(String command, int blockX, int blockY, int blockZ) implements CustomPayload {
    public static final Identifier IDENT = Identifier.of(CMT.MODID, "smithinganvilcraftstepvalidationpacket");
    public static final Id<C2SSmithingAnvilCraftStepValidationPayload> ID =
            new Id<>(IDENT);

    public static final net.minecraft.network.codec.PacketCodec<RegistryByteBuf, C2SSmithingAnvilCraftStepValidationPayload> CODEC =
            net.minecraft.network.codec.PacketCodec.tuple(
                    PacketCodecs.STRING,
                    C2SSmithingAnvilCraftStepValidationPayload::command,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilCraftStepValidationPayload::blockX,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilCraftStepValidationPayload::blockY,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilCraftStepValidationPayload::blockZ,
                    C2SSmithingAnvilCraftStepValidationPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    // Optional: helper handle method
    public static void handle(C2SSmithingAnvilCraftStepValidationPayload payload, MinecraftServer server, ServerPlayerEntity player) {
        server.execute(() -> {
            if (player.getWorld().getBlockEntity(new BlockPos(payload.blockX(), payload.blockY(), payload.blockZ())) instanceof SmithingAnvilBlockEntity smithinBE) {
                smithinBE.applyCraftCommand(payload.command, player);
                smithinBE.markDirty();
                smithinBE.sync();
            }
        });
    }
}
