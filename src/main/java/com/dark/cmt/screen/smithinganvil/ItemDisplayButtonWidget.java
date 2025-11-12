package com.dark.cmt.screen.smithinganvil;

import com.dark.cmt.CMT;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ItemDisplayButtonWidget extends ClickableWidget {
    private final Supplier<ItemStack> stackSupplier;
    private static final Identifier SLOT_TEXTURE = Identifier.of(CMT.MODID, "textures/gui/smithing_anvil/smithing_anvil_slot.png");
    private final Runnable onPress;

    public ItemDisplayButtonWidget(int x, int y, Runnable onPress, Supplier<ItemStack> stackSupplier) {
        super(x, y, 24, 24, Text.empty());
        this.onPress = onPress;
        this.stackSupplier = stackSupplier;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        onPress.run();
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw slot
        context.drawTexture(SLOT_TEXTURE, getX(), getY(), 0, 0, 24, 24, 24 ,24);

        // Draw hover overlay if hovered
        if (isHovered()) {
            context.fill(getX(), getY(), getX() + width, getY() + height, 0x80FFFFFF);
        }

        // Draw item
        ItemStack stack = stackSupplier.get();
        if (!stack.isEmpty()) {
            context.drawItem(stack, getX() + 4, getY() + 4);
        }
    }
}
