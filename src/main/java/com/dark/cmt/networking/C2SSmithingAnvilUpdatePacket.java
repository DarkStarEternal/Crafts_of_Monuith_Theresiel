package com.dark.cmt.networking;

import com.dark.cmt.CMT;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public record C2SSmithingAnvilUpdatePacket(int RecipeID, int RecipePage, BlockPos pos) implements Packet {

    public static final CustomPayload.Id ID = new CustomPayload.Id(Identifier.of(CMT.MODID, "smithinganvilupdatepacket"));

    public static final PacketType PACKET_TYPE = new PacketType(NetworkSide.SERVERBOUND, Identifier.of(CMT.MODID, "smithinganvilupdatepacket"));

    public void write(PacketByteBuf buf) {
        buf.writeVarInt(RecipeID());
        buf.writeVarInt(RecipePage());
        buf.writeBlockPos(pos());
    }

    public static C2SSmithingAnvilUpdatePacket read(PacketByteBuf buf) {

        int RecipeID = buf.readVarInt();
        int RecipePage = buf.readVarInt();
        BlockPos pos = buf.readBlockPos();

        return new C2SSmithingAnvilUpdatePacket(RecipeID, RecipePage, pos);
    }

    public static void handle(C2SSmithingAnvilUpdatePacket payload,MinecraftServer server, ServerPlayerEntity player) {

        server.execute(() -> {
            if (player.getWorld().getBlockEntity(payload.pos()) instanceof SmithingAnvilBlockEntity smithinBE) {
                smithinBE.transformCraftItem(payload.RecipeID(), payload.RecipePage());
            }
        });
    }

    @Override
    public PacketType<? extends Packet> getPacketId() {
        return PACKET_TYPE;
    }

    @Override
    public void apply(PacketListener listener) {

    }
}
