package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import com.dark.cmt.screen.smithinganvil.SmithingAnvilScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.compress.harmony.pack200.Codec;

public class CMTScreenHandlers {
    public static final ScreenHandlerType<SmithingAnvilScreenHandler> SMITHING_ANVIL_SCREEN_HANDLER_TYPE =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CMT.MODID, "smithing_anvil_screen_handler"),
                    new ExtendedScreenHandlerType<>(SmithingAnvilScreenHandler::new, BlockPos.PACKET_CODEC));
    public static void registerScreenHandlers() {
    }
}