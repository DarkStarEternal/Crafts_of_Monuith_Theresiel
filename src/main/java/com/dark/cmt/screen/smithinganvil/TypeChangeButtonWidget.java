package com.dark.cmt.screen.smithinganvil;

import com.dark.cmt.CMT;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class TypeChangeButtonWidget extends ClickableWidget {
    private buttonType type;
    private static final String BASE_SLOT_PATH = "textures/gui/smithing_anvil/smithing_anvil_change";
    private final Runnable onPress;

    public TypeChangeButtonWidget(int x, int y, Runnable onPress, buttonType type) {
        super(x, y, 20, 20, Text.empty());
        this.onPress = onPress;
        this.type = type;
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
        Identifier SLOT_TEXTURE;

        if (this.type == buttonType.COMBINER) {
            SLOT_TEXTURE = Identifier.of(CMT.MODID, BASE_SLOT_PATH + "_combiner.png");
        } else if (this.type == buttonType.PART) {
            SLOT_TEXTURE = Identifier.of(CMT.MODID, BASE_SLOT_PATH + "_part.png");
        } else {
            SLOT_TEXTURE = Identifier.of(CMT.MODID, BASE_SLOT_PATH + "_fallback.png");
        }


        context.drawTexture(SLOT_TEXTURE, getX(), getY(), 0, 0, 20, 20, 20 ,20);

        if (isHovered()) {
            context.fill(getX(), getY(), getX() + width, getY() + height, 0x80FFFFFF);
        }
    }

    public enum buttonType {
        PART,
        COMBINER
    }
}
