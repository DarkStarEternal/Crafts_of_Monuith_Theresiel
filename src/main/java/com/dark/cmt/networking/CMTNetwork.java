package com.dark.cmt.networking;

import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;

import java.util.Objects;

public class CMTNetwork {

    public static void register() {
        // Register the payload type for client->server on the common side:
        PayloadTypeRegistry.playC2S().register(C2SSmithingAnvilUpdatePayload.ID, C2SSmithingAnvilUpdatePayload.CODEC);

        // Register the handler which receives the decoded payload instance
        ServerPlayNetworking.registerGlobalReceiver(
                C2SSmithingAnvilUpdatePayload.ID,
                (payload, context) -> {
                    C2SSmithingAnvilUpdatePayload.handle(payload, context.server(), context.player());
                }
        );
    }
}
