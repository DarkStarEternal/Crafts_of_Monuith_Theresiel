package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilCombinerScreenHandler;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilPartScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class CMTScreenHandlers {
    public static final ScreenHandlerType<SmithingAnvilPartScreenHandler> SMITHINGANVILPARTSCREENHANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CMT.MODID, "smithing_anvil_part_handler"),
            new ExtendedScreenHandlerType<>(SmithingAnvilPartScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<SmithingAnvilCombinerScreenHandler> SMITHINGANVILCOMBINERSCREENHANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CMT.MODID, "smithing_anvil_combiner_handler"),
                    new ExtendedScreenHandlerType<>(SmithingAnvilCombinerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
    }
}