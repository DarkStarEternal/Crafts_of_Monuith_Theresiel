package com.dark.cmt.screen.smithinganvil;


import com.dark.cmt.CMT;
import com.dark.cmt.block.smithinganvil.SmithingAnvilBlockEntity;
import com.dark.cmt.item.SmithingManual;
import com.dark.cmt.item.smitheditems.UnfinishedSmithedItem;
import com.dark.cmt.materials.SmithingMaterial;
import com.dark.cmt.networking.C2SSmithingAnvilCraftStepValidationPayload;
import com.dark.cmt.networking.C2SSmithingAnvilUnfinishedMutatePayload;
import com.dark.cmt.recipe.SmithingManualRecipe;
import com.dark.cmt.init.custom.MetalMaterialRegistry;
import com.dark.cmt.init.custom.SmithingManualRecipes;
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



public class SmithingAnvilScreen extends HandledScreen<SmithingAnvilScreenHandler> {
    public static final Identifier GUI_TEXTURE =
            Identifier.of(CMT.MODID, "textures/gui/smithing_anvil/smithing_anvil_gui.png");

    PageTurnWidget prevPageButton;
    PageTurnWidget nextPageButton;
    private int page = 0;
    private final int maxPages = 5;
    int itemsPerPage = 3;

    public SmithingAnvilScreen(SmithingAnvilScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Bend"), button -> {
                    if (client != null && client.player != null) {
                        if (handler.getSlot(0).getStack().getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem) {
                            sendCommand("B");
                        }
                    }
                }).dimensions(x + 25, y + 10, 60, 15).build()
        );
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Shorten"), button -> {
                    if (client != null && client.player != null) {
                        if (handler.getSlot(0).getStack().getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem) {
                            sendCommand("S");
                        }
                    }
                }).dimensions(x + 25, y + 25, 60, 15).build()
        );
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Lenghten"), button -> {
                    if (client != null && client.player != null) {
                        if (handler.getSlot(0).getStack().getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem) {
                            sendCommand("L");
                        }
                    }
                }).dimensions(x + 25, y + 40, 60, 15).build()
        );
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Flatten"), button -> {
                    if (client != null && client.player != null) {
                        if (handler.getSlot(0).getStack().getItem() instanceof UnfinishedSmithedItem unfinishedSmithedItem) {
                            sendCommand("F");
                        }
                    }
                }).dimensions(x + 25, y + 55, 60, 15).build()
        );

        this.addDrawableChild(
                new ItemDisplayButtonWidget(
                        x + 93, y + 35, () -> transformItemToUnfinished(1, page, getMaterialFromInput(handler.getSlot(0).getStack())),
                        () -> getFinalItemFromRecipeEntry(page, 1)
                )
        );

        this.addDrawableChild(
                new ItemDisplayButtonWidget(
                        x + 117, y + 35, () -> transformItemToUnfinished(2, page, getMaterialFromInput(handler.getSlot(0).getStack())),
                        () -> getFinalItemFromRecipeEntry(page, 2)
                )
        );

        this.addDrawableChild(
                new ItemDisplayButtonWidget(
                        x + 141, y + 35, () -> transformItemToUnfinished(3, page, getMaterialFromInput(handler.getSlot(0).getStack())),
                        () -> getFinalItemFromRecipeEntry(page, 3)
                )
        );

        prevPageButton = this.addDrawableChild(new PageTurnWidget(
                x + 95, y + 60,
                false,
                button -> switchPage(-1),
                true
        ));

        nextPageButton = this.addDrawableChild(new PageTurnWidget(
                x + 140, y + 60,
                true,
                button -> switchPage(1),
                true
        ));

        updateButtons();
    }

    public void transformItemToUnfinished(int RecipeID, int RecipePage, String material) {
        if (handler.getBlockEntity() instanceof SmithingAnvilBlockEntity smithingBE) {
            sendUnfinishedMutationPacket(RecipeID, RecipePage, handler.getBlockPos(), material);
        }
    }

    public String getMaterialFromInput(ItemStack input) {
        SmithingMaterial material = MetalMaterialRegistry.getMaterialFromInput(input);
        if (material != null) {
            return material.getMaterialIdentifier();
        }
        return "";
    }

    public void sendCommand(String inputCommand) {
        if (handler.getBlockEntity() instanceof SmithingAnvilBlockEntity smithingBE) {
            sendCraftStepValidationPacket(inputCommand, handler.getBlockPos());
        }
    }

    public void sendUnfinishedMutationPacket(int RecipeID, int RecipePage, BlockPos pos, String material) {
        C2SSmithingAnvilUnfinishedMutatePayload payload = new C2SSmithingAnvilUnfinishedMutatePayload(RecipeID, RecipePage, pos.getX(), pos.getY(), pos.getZ(), material);
        ClientPlayNetworking.send(payload);
    }

    public void sendCraftStepValidationPacket(String inputCommand, BlockPos pos) {
        C2SSmithingAnvilCraftStepValidationPayload payload = new C2SSmithingAnvilCraftStepValidationPayload(inputCommand, pos.getX(), pos.getY(), pos.getZ());
        ClientPlayNetworking.send(payload);
    }

    public ItemStack getFinalItemFromRecipeEntry(int page, int entry){
        Item slot2 = handler.getSlot(2).getStack().getItem();
        if (slot2 instanceof SmithingManual manual) {
            if (getRecipeEntry(page, entry) != null) {
                ItemStack stack = getRecipeEntry(page, entry).getFinalitem();
                return stack;
            }
            else {
                ItemStack stack = new ItemStack(Blocks.BARRIER.asItem());
                return stack;
            }
        }
        return new ItemStack(Blocks.AIR.asItem());
    }

    public SmithingManualRecipe getRecipeEntry(int page, int entry){
        Item slot0Item = handler.getSlot(0).getStack().getItem();
        List<SmithingManualRecipe> filtered = getFilteredRecipes(slot0Item);
        List<SmithingManualRecipe> pageEntries = getPageEntries(filtered, page, itemsPerPage);

        int idx = entry - 1;
        if (idx >= 0 && idx < pageEntries.size()) return pageEntries.get(idx);
        return null;
    }

    public List<SmithingManualRecipe> getPageEntries(List<SmithingManualRecipe> list, int page, int itemsPerPage) {
        int start = page * itemsPerPage;
        if (start >= list.size()) return Collections.emptyList();
        int end = Math.min(start + itemsPerPage, list.size());
        return list.subList(start, end);
    }

    private void switchPage(int delta) {
        page += delta;
        page = Math.max(0, Math.min(page, getMaxPageIndex()));
        updateButtons();
    }

    private int getMaxPageIndex() {
        Item slot0Item = handler.getSlot(0).getStack().getItem();
        List<SmithingManualRecipe> filtered = getFilteredRecipes(slot0Item);
        int totalRecipes = filtered.size();
        return (totalRecipes - 1) / itemsPerPage; // zero-based index
    }

    private List<SmithingManualRecipe> getFilteredRecipes(Item inputItem) {
        if (handler == null) return Collections.emptyList();
        Item slot2Item = handler.getSlot(2).getStack().getItem();
        if (!(slot2Item instanceof SmithingManual manual)) return Collections.emptyList();

        List<String> recipeNames = manual.getRecipeList(handler.getSlot(2).getStack());

        List<SmithingManualRecipe> recipes = new ArrayList<>();

        for (String name : recipeNames) {
            SmithingManualRecipe recipe = SmithingManualRecipes.getRecipeFromName(name);
            recipes.add(recipe);
        }

        return recipes;
    }


    private void updateButtons() {
        prevPageButton.active = page > 1;
        nextPageButton.active = page < maxPages - 1;
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