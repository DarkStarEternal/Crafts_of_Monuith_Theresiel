package com.dark.cmt.entity.client;

import com.dark.cmt.CMT;
import com.dark.cmt.entity.custom.KhyninOverlordEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class KhyninOverlordModel<T extends KhyninOverlordEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer KHYNINOVERLORD = new EntityModelLayer(Identifier.of(CMT.MODID, "khynin_overlord"), "main");
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart chest;
    private final ModelPart head;
    private final ModelPart yaw;
    private final ModelPart snort;
    private final ModelPart nose;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public KhyninOverlordModel(ModelPart root) {
        this.body = root.getChild("body");
        this.torso = this.body.getChild("torso");
        this.chest = this.torso.getChild("chest");
        this.head = this.torso.getChild("head");
        this.yaw = this.head.getChild("yaw");
        this.snort = this.head.getChild("snort");
        this.nose = this.head.getChild("nose");
        this.left_arm = this.torso.getChild("left_arm");
        this.right_arm = this.torso.getChild("right_arm");
        this.left_leg = this.body.getChild("left_leg");
        this.right_leg = this.body.getChild("right_leg");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 15.0F, 0.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 33).cuboid(-9.0F, -25.0F, -7.0F, 18.0F, 9.0F, 15.0F, new Dilation(0.0F))
                .uv(60, 0).cuboid(-11.0F, 1.0F, -8.0F, 22.0F, 3.0F, 5.0F, new Dilation(0.0F))
                .uv(0, 57).cuboid(-11.0F, -16.0F, -8.0F, 22.0F, 20.0F, 8.0F, new Dilation(0.3F))
                .uv(60, 43).cuboid(-9.0F, -17.0F, -7.0F, 18.0F, 9.0F, 14.0F, new Dilation(0.7F))
                .uv(0, 0).cuboid(-11.0F, -16.0F, -8.0F, 22.0F, 17.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData chest = torso.addChild("chest", ModelPartBuilder.create().uv(64, 21).cuboid(-9.0F, -4.0F, 0.0F, 18.0F, 6.0F, 12.0F, new Dilation(0.3F)), ModelTransform.pivot(0.0F, -20.0F, -7.0F));

        ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(76, 8).cuboid(-4.5F, -3.1F, -4.5F, 9.0F, 3.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 85).cuboid(-5.0F, -10.0F, -5.0F, 10.0F, 7.0F, 10.0F, new Dilation(0.0F))
                .uv(110, 52).cuboid(-5.0F, -3.0F, -5.0F, 10.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(30, 85).cuboid(-5.0F, -3.0F, -1.0F, 10.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -25.0F, 0.0F));

        ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(5, 42).cuboid(-7.0F, 0.5F, -2.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(8, 37).cuboid(-9.0F, 1.5F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.5F, 0.0F, 0.0F, 0.0F, 1.0908F));

        ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(66, 48).cuboid(-5.0F, 0.0F, -2.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.5F, 0.0F, 0.0F, 0.0F, 0.9599F));

        ModelPartData cube_r3 = head.addChild("cube_r3", ModelPartBuilder.create().uv(0, 85).cuboid(-3.1334F, -1.5336F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.5F, 0.0F, 0.0F, 0.0F, 0.5672F));

        ModelPartData cube_r4 = head.addChild("cube_r4", ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, -1.5F, -2.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(128, 29).cuboid(-0.6334F, -2.0336F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -8.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r5 = head.addChild("cube_r5", ModelPartBuilder.create().uv(80, 104).cuboid(-0.3666F, -2.0336F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(74, 98).cuboid(-1.0F, -1.5F, -2.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r6 = head.addChild("cube_r6", ModelPartBuilder.create().uv(128, 82).cuboid(2.1334F, -1.5336F, -2.5F, 1.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

        ModelPartData cube_r7 = head.addChild("cube_r7", ModelPartBuilder.create().uv(52, 57).cuboid(-1.4F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -9.0F, -0.5F, 0.0F, 0.0F, -0.48F));

        ModelPartData cube_r8 = head.addChild("cube_r8", ModelPartBuilder.create().uv(0, 57).cuboid(-0.6F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -9.0F, -0.5F, 0.0F, 0.0F, 0.48F));

        ModelPartData cube_r9 = head.addChild("cube_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(11, 0).cuboid(2.0F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -5.0F, 0.1745F, 0.0F, 0.0F));

        ModelPartData cube_r10 = head.addChild("cube_r10", ModelPartBuilder.create().uv(8, 35).cuboid(7.0F, 1.5F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(60, 66).cuboid(6.0F, 0.5F, -2.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.5F, 0.0F, 0.0F, 0.0F, -1.0908F));

        ModelPartData cube_r11 = head.addChild("cube_r11", ModelPartBuilder.create().uv(88, 82).cuboid(4.0F, 0.0F, -2.0F, 1.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.5F, 0.0F, 0.0F, 0.0F, -0.9599F));

        ModelPartData yaw = head.addChild("yaw", ModelPartBuilder.create().uv(106, 0).cuboid(-5.0F, -1.0F, -5.0F, 10.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

        ModelPartData snort = head.addChild("snort", ModelPartBuilder.create().uv(24, 102).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 3.0F, 4.0F, new Dilation(0.1F)), ModelTransform.pivot(2.0F, -2.0F, -5.0F));

        ModelPartData nose = head.addChild("nose", ModelPartBuilder.create().uv(51, 33).cuboid(-2.0F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 33).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, -5.0F));

        ModelPartData left_arm = torso.addChild("left_arm", ModelPartBuilder.create().uv(52, 82).cuboid(-10.0F, -4.0F, -4.0F, 14.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(9.0F, -22.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        ModelPartData cube_r12 = left_arm.addChild("cube_r12", ModelPartBuilder.create().uv(56, 98).cuboid(2.0F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 119).cuboid(2.0F, -3.0F, -3.0F, 6.0F, 11.0F, 6.0F, new Dilation(0.3F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        ModelPartData cube_r13 = left_arm.addChild("cube_r13", ModelPartBuilder.create().uv(56, 124).cuboid(4.7F, 22.2F, -3.0F, 3.0F, 4.0F, 5.0F, new Dilation(0.2F))
                .uv(112, 24).cuboid(2.7F, 16.2F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.2F))
                .uv(98, 123).cuboid(2.7F, 16.2F, -3.0F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.4981F, -0.0436F, 0.5F, 0.0F, 0.0F, -0.0436F));

        ModelPartData right_arm = torso.addChild("right_arm", ModelPartBuilder.create().uv(60, 66).cuboid(-4.0F, -4.0F, -4.0F, 14.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-9.0F, -22.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        ModelPartData cube_r14 = right_arm.addChild("cube_r14", ModelPartBuilder.create().uv(90, 92).cuboid(-8.0F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new Dilation(0.0F))
                .uv(74, 118).cuboid(-8.0F, -3.0F, -3.0F, 6.0F, 11.0F, 6.0F, new Dilation(0.3F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        ModelPartData cube_r15 = right_arm.addChild("cube_r15", ModelPartBuilder.create().uv(0, 0).cuboid(-7.7F, 22.2F, -3.0F, 3.0F, 4.0F, 5.0F, new Dilation(0.2F))
                .uv(51, 39).cuboid(-7.7F, 16.2F, -3.0F, 5.0F, 4.0F, 5.0F, new Dilation(0.2F))
                .uv(119, 61).cuboid(-7.7F, 16.2F, -3.0F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.4981F, -0.0436F, 0.5F, 0.0F, 0.0F, 0.0436F));

        ModelPartData left_leg = body.addChild("left_leg", ModelPartBuilder.create().uv(0, 102).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F))
                .uv(110, 39).cuboid(-4.0F, 4.0F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.3F)), ModelTransform.pivot(5.0F, 0.0F, 0.0F));

        ModelPartData right_leg = body.addChild("right_leg", ModelPartBuilder.create().uv(106, 110).cuboid(-4.0F, 4.0F, -4.0F, 8.0F, 5.0F, 8.0F, new Dilation(0.3F))
                .uv(96, 74).cuboid(-4.0F, 0.0F, -4.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        body.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(KhyninOverlordAnimations.RUN, limbAngle, limbDistance, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, KhyninOverlordAnimations.IDLE, animationProgress, 1f);
    }
}
