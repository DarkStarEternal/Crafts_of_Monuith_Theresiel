package com.dark.cmt.screen;

import com.dark.cmt.CMT;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SmithingAnvilScreen extends Screen {
    private static final Identifier TEXTURE = Identifier.of(CMT.MODID, "textures/screen/smithing_anvil_gui.png");
    private final int backgroundWidth = 176;
    private final int backgroundHeight = 166;

    public SmithingAnvilScreen() {
        super(Text.literal("My Custom GUI"));
    }

    @Override
    protected void init() {
        super.init();
        // Add buttons, text fields, etc. here if needed
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, TEXTURE);
        RenderSystem.texParameter(3553, 10241, 9728);
        RenderSystem.texParameter(3553, 10240, 9728);

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, backgroundWidth, backgroundHeight);

        context.drawText(textRenderer, "Hello from Fabric GUI!", x + 10, y + 10, 0xFFFFFF, false);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}