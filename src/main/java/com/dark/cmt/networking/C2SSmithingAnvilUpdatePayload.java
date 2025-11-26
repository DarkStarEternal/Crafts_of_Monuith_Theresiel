package com.dark.cmt.networking;

import com.dark.cmt.CMT;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record C2SSmithingAnvilUpdatePayload(int recipeID, int recipePage, int blockX, int blockY, int blockZ) implements CustomPayload {
    public static final Identifier IDENT = Identifier.of(CMT.MODID, "smithinganvilupdatepacket");
    public static final CustomPayload.Id<C2SSmithingAnvilUpdatePayload> ID =
            new CustomPayload.Id<>(IDENT);

    public static final net.minecraft.network.codec.PacketCodec<RegistryByteBuf, C2SSmithingAnvilUpdatePayload> CODEC =
            net.minecraft.network.codec.PacketCodec.tuple(
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUpdatePayload::recipeID,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUpdatePayload::recipePage,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUpdatePayload::blockX,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUpdatePayload::blockY,
                    net.minecraft.network.codec.PacketCodecs.INTEGER,
                    C2SSmithingAnvilUpdatePayload::blockZ,
                    C2SSmithingAnvilUpdatePayload::new
            );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }

    // Optional: helper handle method
    public static void handle(C2SSmithingAnvilUpdatePayload payload, MinecraftServer server, ServerPlayerEntity player) {
        server.execute(() -> {
            if (player.getWorld().getBlockEntity(new BlockPos(payload.blockX(), payload.blockY(), payload.blockZ())) instanceof SmithingAnvilBlockEntity smithinBE) {
                smithinBE.transformCraftItem(payload.recipeID(), payload.recipePage(), player);
                smithinBE.markDirty();
                smithinBE.sync();
            }
        });
    }
}
