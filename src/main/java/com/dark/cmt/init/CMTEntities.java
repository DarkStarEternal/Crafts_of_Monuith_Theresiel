package com.dark.cmt.init;

import com.dark.cmt.CMT;
import com.dark.cmt.entity.custom.KhyninOverlordEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CMTEntities {

    public static final EntityType<KhyninOverlordEntity> KHYNIN_OVERLORD = Registry.register(Registries.ENTITY_TYPE, Identifier.of(CMT.MODID, "khynin_overlord"),
            EntityType.Builder.create(KhyninOverlordEntity::new, SpawnGroup.MISC)
                    .dimensions(2f, 2.2f).build());


    public static void register() {

    }
}
