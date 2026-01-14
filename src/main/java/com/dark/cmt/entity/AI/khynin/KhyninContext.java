package com.dark.cmt.entity.AI.khynin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public record KhyninContext(
        LivingEntity target,
        float healthPct,
        double distance
) {}