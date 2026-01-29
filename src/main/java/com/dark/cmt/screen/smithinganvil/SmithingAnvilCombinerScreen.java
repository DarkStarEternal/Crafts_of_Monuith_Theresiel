package com.dark.cmt.screen.smithinganvil;


import com.dark.cmt.CMT;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import com.dark.cmt.init.custom.MetalMaterialRegistry;
import com.dark.cmt.init.custom.SmithingManualRecipes;
import com.dark.cmt.item.SmithingManual;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.networking.C2SSmithingAnvilCraftStepValidationPayload;
import com.dark.cmt.networking.C2SSmithingAnvilUIChangePayload;
import com.dark.cmt.networking.C2SSmithingAnvilUnfinishedMutatePayload;
import com.dark.cmt.recipe.SmithingManualRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PageTurnWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SmithingAnvilCombinerScreen extends HandledScreen<SmithingAnvilCombinerScreenHandler> {
    public static final Identifier GUI_TEXTURE =
            Identifier.of(CMT.MODID, "textures/gui/smithing_anvil/smithing_anvil_gui_combiner.png");


    public SmithingAnvilCombinerScreen(SmithingAnvilCombinerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        this.addDrawableChild(
                new TypeChangeButtonWidget(
                        x + 5, y - 17, this::changeScreen, TypeChangeButtonWidget.buttonType.PART
                )
        );
    }

    public void changeScreen(){
        sendUIChangePacket(false, handler.getBlockPos());
    }

    public void sendUIChangePacket(boolean toCombiner, BlockPos pos) {
        C2SSmithingAnvilUIChangePayload payload = new C2SSmithingAnvilUIChangePayload(toCombiner, pos.getX(), pos.getY(), pos.getZ());
        ClientPlayNetworking.send(payload);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, backgroundWidth, backgroundHeight);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}