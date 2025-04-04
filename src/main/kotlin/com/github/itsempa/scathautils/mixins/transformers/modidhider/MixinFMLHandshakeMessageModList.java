package com.github.itsempa.scathautils.mixins.transformers.modidhider;

import com.github.itsempa.scathautils.ScathaUtils;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;


@Mixin(FMLHandshakeMessage.ModList.class)
public abstract class MixinFMLHandshakeMessageModList {

    @Shadow
    public abstract Map<String, String> modList();

    @Inject(method = "<init>(Ljava/util/List;)V", at = @At(value = "RETURN"))
    public void onInitLast(List modList, CallbackInfo ci) {
        if (!ScathaUtils.HIDE_MOD_ID) return;
        modList().remove(ScathaUtils.MOD_ID);
    }
}
