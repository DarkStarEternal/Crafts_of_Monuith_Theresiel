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

public record C2SSmithingAnvilUnfinishedMutatePayload(int recipeID, int recipePage, int blockX, int blockY, int blockZ, String material) implements CustomPayload {
    public static final Identifier IDENT = Identifier.of(CMT.MODID, "smithinganvilunfinishedmutationpacket");
    public static final CustomPayload.Id<C2SSmithingAnvilUnfinishedMutatePayload> ID =
            new CustomPayload.Id<>(IDENT);

    public static final net.minecraft.network.codec.PacketCodec<RegistryByteBuf, C2SSmithingAnvilUnfinishedMutatePayload> CODEC =
            net.minecraft.network.codec.PacketCodec.tuple(
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUnfinishedMutatePayload::recipeID,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUnfinishedMutatePayload::recipePage,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUnfinishedMutatePayload::blockX,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUnfinishedMutatePayload::blockY,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUnfinishedMutatePayload::blockZ,
                    PacketCodecs.STRING,
                    C2SSmithingAnvilUnfinishedMutatePayload::material,
                    C2SSmithingAnvilUnfinishedMutatePayload::new
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }

    // Optional: helper handle method
    public static void handle(C2SSmithingAnvilUnfinishedMutatePayload payload, MinecraftServer server, ServerPlayerEntity player) {
        server.execute(() -> {
            if (player.getWorld().getBlockEntity(new BlockPos(payload.blockX(), payload.blockY(), payload.blockZ())) instanceof SmithingAnvilBlockEntity smithinBE) {
                smithinBE.transformCraftItem(payload.recipeID(), payload.recipePage(), player, payload.material());
                smithinBE.markDirty();
                smithinBE.sync();
            }
        });
    }
}
