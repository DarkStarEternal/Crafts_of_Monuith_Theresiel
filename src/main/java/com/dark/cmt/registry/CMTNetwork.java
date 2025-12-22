package com.dark.cmt.registry;

import com.dark.cmt.networking.C2SSmithingAnvilCraftStepValidationPayload;
import com.dark.cmt.networking.C2SSmithingAnvilUnfinishedMutatePayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class CMTNetwork {

    public static void register() {
        PayloadTypeRegistry.playC2S().register(C2SSmithingAnvilUnfinishedMutatePayload.ID, C2SSmithingAnvilUnfinishedMutatePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(C2SSmithingAnvilCraftStepValidationPayload.ID, C2SSmithingAnvilCraftStepValidationPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(
                C2SSmithingAnvilUnfinishedMutatePayload.ID,
                (payload, context) -> {
                    C2SSmithingAnvilUnfinishedMutatePayload.handle(payload, context.server(), context.player());
                }
        );
        ServerPlayNetworking.registerGlobalReceiver(
                C2SSmithingAnvilCraftStepValidationPayload.ID,
                (payload, context) -> {
                    C2SSmithingAnvilCraftStepValidationPayload.handle(payload, context.server(), context.player());
                }
        );

    }
}
