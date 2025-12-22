package com.dark.cmt.registry;

import com.dark.cmt.CMT;
import com.dark.cmt.item.smitheditems.unfinished.UnfinishedHookModel;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

public class CMTModelLoadingPlugin implements ModelLoadingPlugin {

    public static final Identifier UNFINISHED_HOOK_MODEL =
            Identifier.of(CMT.MODID, "item/unfinished_hook");

    @Override
    public void onInitializeModelLoader(Context context) {

        // Tell Minecraft this model exists
        context.addModels(UNFINISHED_HOOK_MODEL);

        // Provide the UnbakedModel when asked
        context.resolveModel().register(ctx -> {
            if (ctx.id().equals(UNFINISHED_HOOK_MODEL)) {
                return new UnfinishedHookModel.Unbaked();
            }
            return null;
        });

    }
}