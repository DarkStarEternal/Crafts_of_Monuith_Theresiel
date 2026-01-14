package com.dark.cmt.entity.client;

import com.dark.cmt.CMT;
import com.dark.cmt.entity.custom.KhyninOverlordEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class KhyninOverlordRenderer extends MobEntityRenderer<KhyninOverlordEntity, KhyninOverlordModel<KhyninOverlordEntity>> {

    public KhyninOverlordRenderer(EntityRendererFactory.Context context) {
        super(context, new KhyninOverlordModel<>(context.getPart(KhyninOverlordModel.KHYNINOVERLORD)), 1f);
    }

    @Override
    public Identifier getTexture(KhyninOverlordEntity entity) {
        return Identifier.of(CMT.MODID, "textures/entity/khynin/khynin_boss.png");
    }

    @Override
    public void render(KhyninOverlordEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
