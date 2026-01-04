package com.dark.cmt.client;

import com.dark.cmt.CMT;
import com.dark.cmt.item.smitheditems.unfinished.UnfinishedHookBakedModels;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.util.Identifier;

public class CMTModelLoadingPlugin implements ModelLoadingPlugin {
    public static final Identifier UNFINISHED_HOOK = Identifier.of(CMT.MODID, "unfinished_hook");


    public CMTModelLoadingPlugin(){
        System.out.println("Modelloader pre initting or smthin");
    }

    @Override
    public void onInitializeModelLoader(Context context) {
        System.out.println("Modelloader initting or smthin");

        context.addModels(
                UNFINISHED_HOOK
        );

        context.resolveModel().register((modelId) -> {
            if (modelId.id().equals(UNFINISHED_HOOK)) {
                System.out.println("MODEL FOUND YAAAAA");
                return new UnfinishedHookBakedModels.HookUnbakedModel();
            }
            return null;
        });

    }
}
