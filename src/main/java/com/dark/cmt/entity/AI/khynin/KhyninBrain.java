package com.dark.cmt.entity.AI.khynin;

import com.dark.cmt.entity.custom.KhyninOverlordEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class KhyninBrain {

    private final KhyninOverlordEntity boss;
    private final KhyninMemory memory = new KhyninMemory();

    private int decisionCooldown = 0;
    private int attackCooldown = 0;

    public KhyninBrain(KhyninOverlordEntity boss) {
        this.boss = boss;
    }

    public void tick() {
        // Cooldowns decrement
        if (decisionCooldown > 0) decisionCooldown--;
        if (attackCooldown > 0) attackCooldown--;

        if (decisionCooldown > 0) return;
        KhyninContext ctx = sense();
        decide(ctx);

        decisionCooldown = 5;
    }

    private KhyninContext sense() {
        LivingEntity target = boss.getBrainTarget();
        float healthPct = boss.getHealth() / boss.getMaxHealth();
        double distance = target == null ? Double.MAX_VALUE : boss.distanceTo(target);
        return new KhyninContext(target, healthPct, distance);
    }

    private void decide(KhyninContext ctx) {
        if (ctx.target() == null) return;

        faceTarget(ctx.target());

        if (ctx.healthPct() < 0.1f && memory.lastAttack != KhyninAttacks.BLOCK) {
            boss.startBlocking(40);
            memory.lastAttack = KhyninAttacks.BLOCK;
            return;
        }

        if (boss.isBlocking()) return;

        if (!boss.isBlocking() && memory.lastAttack == KhyninAttacks.BLOCK) {
            memory.lastAttack = KhyninAttacks.NONE;
        }

        double distance = ctx.distance();

        if (distance > 2.5 && distance < 5.5) {
            if (memory.lastAttack != KhyninAttacks.DASH) {
                dashAttack(ctx);
                return;
            }
        }

        if (distance <= 2.5) {
            int enemiesInFront = countEnemiesInFront(3.5, 2.5);

            if (ctx.healthPct() < 0.7f) {
                enragedAttack(ctx);
            } else if (enemiesInFront >= 2 && memory.lastAttack != KhyninAttacks.MELEE_SLASH) {
                meleeSlashAttack(ctx);
            } else {
                meleeAttack(ctx);
            }
            return;
        }
        moveTowardTarget(ctx);
    }


    private void meleeAttack(KhyninContext ctx) {
        if (!boss.canAttack()) return;

        boss.getNavigation().stop();
        faceTarget(ctx.target());

        if (isInMeleeRange(ctx.target())) {
            boss.lightAttack(ctx.target());
            boss.resetAttackCooldown(20);
            memory.lastAttack = KhyninAttacks.MELEE_SINGLE;
        }
    }

    private void meleeSlashAttack(KhyninContext ctx) {
        if (!boss.canAttack()) return;

        boss.getNavigation().stop();
        faceTarget(ctx.target());

        double range = 3.5;
        double width = 2.5;
        double height = 2.0;

        Vec3d forward = boss.getRotationVec(1.0F);
        Vec3d center = boss.getPos().add(forward.multiply(range / 2.0));

        Box slashBox = new Box(
                center.x - width / 2,
                boss.getY(),
                center.z - width / 2,
                center.x + width / 2,
                boss.getY() + height,
                center.z + width / 2
        );

        List<LivingEntity> targets = boss.getWorld().getEntitiesByClass(
                LivingEntity.class,
                slashBox,
                e -> e != boss && e.isAlive() && (boss.canTarget(e) || isAggressiveTamed(e))
        );

        if (targets.size() >= 2) { // only clear crowded spaces
            for (LivingEntity entity : targets) {
                boss.slashAttack(entity);
            }
            memory.lastAttack = KhyninAttacks.MELEE_SLASH;
            boss.resetAttackCooldown(20);
        }
    }

    private void dashAttack(KhyninContext ctx) {
        if (!boss.canAttack()) return;

        boss.getNavigation().stop();
        Vec3d dir = ctx.target().getPos().subtract(boss.getPos()).normalize();
        boss.setVelocity(dir.multiply(0.9));
        boss.velocityDirty = true;
        memory.lastAttack = KhyninAttacks.DASH;
        boss.resetAttackCooldown(20);
    }

    private void enragedAttack(KhyninContext ctx) {
        if (!boss.canAttack()) return;

        boss.getNavigation().stop();
        LivingEntity target = ctx.target();
        faceTarget(target);

        if (isInMeleeRange(target)) {
            if (ctx.target().isUsingItem() && ctx.target().getActiveItem().getItem() instanceof ShieldItem) {
                ctx.target().clearActiveItem(); // stops blocking
                ctx.target().getWorld().playSound(null, ctx.target().getBlockPos(), SoundEvents.ITEM_SHIELD_BREAK,
                        SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
            boss.tryAttack(target);
            memory.lastAttack = KhyninAttacks.ENRAGED;
            boss.resetAttackCooldown(20);
        }
    }

    private int countEnemiesInFront(double range, double width) {
        Vec3d forward = boss.getRotationVec(1.0F).normalize();
        Vec3d origin = boss.getPos();
        Vec3d center = origin.add(forward.multiply(range / 2.0));

        Box box = new Box(
                center.x - width / 2,
                boss.getY(),
                center.z - width / 2,
                center.x + width / 2,
                boss.getY() + 2.0,
                center.z + width / 2
        );

        return (int) boss.getWorld().getEntitiesByClass(
                LivingEntity.class,
                box,
                e -> e != boss && e.isAlive() && (boss.canTarget(e) || isAggressiveTamed(e)) && isInFront(e, forward)
        ).stream().count();
    }

    private boolean isAggressiveTamed(LivingEntity e) {
        if (e instanceof TameableEntity tame && tame.isTamed()) {
            return tame.getOwner() != null && tame.getTarget() == boss;
        }
        return false;
    }

    private boolean isInFront(LivingEntity entity, Vec3d forward) {
        Vec3d toTarget = entity.getPos().subtract(boss.getPos()).normalize();
        return forward.dotProduct(toTarget) > 0.5; // ~60° cone
    }

    private boolean isInMeleeRange(LivingEntity target) {
        double reach = boss.getWidth() * 1.5 + target.getWidth();
        return boss.squaredDistanceTo(target) <= reach * reach;
    }

    private void moveTowardTarget(KhyninContext ctx) {
        EntityNavigation nav = boss.getNavigation();
        LivingEntity target = ctx.target();

        if (target == null) return;

        double distance = boss.distanceTo(target);
        if (distance <= 2.5) {
            nav.stop();
            return;
        }

        if (nav.isIdle() || boss.age % 10 == 0) {
            nav.startMovingTo(target, 1.1f);
        }
    }

    private void faceTarget(LivingEntity target) {
        double dx = target.getX() - boss.getX();
        double dz = target.getZ() - boss.getZ();

        float targetYaw = (float) (Math.atan2(dz, dx) * (180 / Math.PI)) - 90F;
        boss.setYaw(yawLerp(boss.getYaw(), targetYaw, 10f));
        boss.bodyYaw = boss.getYaw();
        boss.headYaw = boss.getYaw();
    }

    private float yawLerp(float current, float target, float maxChange) {
        float delta = wrapDegrees(target - current);
        if (delta > maxChange) delta = maxChange;
        if (delta < -maxChange) delta = -maxChange;
        return current + delta;
    }

    private float wrapDegrees(float degrees) {
        while (degrees >= 180F) degrees -= 360F;
        while (degrees < -180F) degrees += 360F;
        return degrees;
    }
}
