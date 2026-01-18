package com.dark.cmt.entity.custom;

import com.dark.cmt.entity.AI.khynin.KhyninBrain;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class KhyninOverlordEntity extends PathAwareEntity implements Monster {

    private static final TrackedData<Byte> BOSS_STATE =
            DataTracker.registerData(KhyninOverlordEntity.class, TrackedDataHandlerRegistry.BYTE);

    private int blockTicks = 0;
    private int attackCooldown = 0;

    private final KhyninBrain brain = new KhyninBrain(this);

    public enum BossState {
        IDLE,
        CHARGING,
        SWEEP_ATTACK,
        BLOCKING
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState chargeAnimation = new AnimationState();
    public final AnimationState sweepAnimation = new AnimationState();
    public final AnimationState blockAnimation = new AnimationState();

    private int idleAnimationTimeout = 0;

    public KhyninOverlordEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WALKABLE, 0.0F);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0F);
    }

    public void startBlocking(int ticks) {
        this.blockTicks = ticks;
    }

    public boolean isBlocking() {
        return blockTicks > 0;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 5f));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new MobNavigation(this, world);
    }

    @Nullable
    private LivingEntity brainTarget;

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.2f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 992f)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 7)
                .add(EntityAttributes.GENERIC_ARMOR, 3)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 340)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, 2);
    }

    public void setBrainTarget(@Nullable LivingEntity target) {
        this.brainTarget = target;
        super.setTarget(target); // sync vanilla
    }

    @Nullable
    public LivingEntity getBrainTarget() {
        return brainTarget;
    }

    public boolean canAttack() {
        return attackCooldown <= 0 && !isBlocking();
    }

    public void resetAttackCooldown(int ticks) {
        attackCooldown = ticks;
    }

    public void slashAttack(LivingEntity target) {
        float damage = (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        target.damage(this.getDamageSources().mobAttack(this), damage);

        // Reduced knockback
        Vec3d kb = target.getPos().subtract(this.getPos()).normalize().multiply(0.2);
        target.addVelocity(kb.x, 0.05, kb.z);
    }

    public void lightAttack(LivingEntity target) {
        float damage = (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) / 2f;
        target.damage(this.getDamageSources().mobAttack(this), damage);

        // Minimal knockback
        Vec3d kb = target.getPos().subtract(this.getPos()).normalize().multiply(0.15);
        target.addVelocity(kb.x, 0.03, kb.z);
    }


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        Entity attacker = source.getAttacker();

        if (attacker instanceof LivingEntity living && living.isAlive()) {
            setBrainTarget(living);
        }

        return super.damage(source, amount);
    }


    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(BOSS_STATE, (byte) BossState.IDLE.ordinal());
    }

    public void setBossState(BossState state) {
        this.dataTracker.set(BOSS_STATE, (byte) state.ordinal());
    }

    public BossState getBossState() {
        return BossState.values()[this.dataTracker.get(BOSS_STATE)];
    }

    @Override
    public boolean canTarget(LivingEntity target) {
        return !(target instanceof KhyninOverlordEntity);
    }


    @Override
    public void tick() {
        super.tick();

        if (attackCooldown > 0) attackCooldown--;

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }

        if (this.getTarget() != brainTarget) {
            super.setTarget(brainTarget);
        }

        if (this.getWorld().isClient()) {
            switch (getBossState()) {
                case CHARGING -> chargeAnimation.startIfNotRunning(age);
                case SWEEP_ATTACK -> sweepAnimation.startIfNotRunning(age);
                case BLOCKING -> blockAnimation.startIfNotRunning(age);
                default -> idleAnimationState.startIfNotRunning(age);
            }
        }

        if (blockTicks > 0) {
            blockTicks--;
            if (blockTicks == 0) {
                // Allow attacks again
                this.velocityDirty = true;
            }
        }

        if (getBossState() == BossState.CHARGING) {
            for (LivingEntity entity : getWorld().getEntitiesByClass(
                    LivingEntity.class,
                    this.getBoundingBox().expand(1.2),
                    e -> e != this)) {

                entity.damage(getDamageSources().mobAttack(this), 14f);
            }
        }

        brain.tick();
    }

    @Override
    public Text getStyledDisplayName() {
        return super.getStyledDisplayName();
    }

    @Override
    public boolean cannotBeSilenced() {
        return super.cannotBeSilenced();
    }
}
