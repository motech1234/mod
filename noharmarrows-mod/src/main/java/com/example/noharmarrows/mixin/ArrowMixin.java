package com.example.noharmarrows.mixin;

import com.example.noharmarrows.ArrowBan;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Arrow.class)
public abstract class ArrowMixin {

	/** Banned arrows are removed the moment they exist in the world (bow, crossbow, dispenser, command...). */
	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void noharmarrows$removeBanned(CallbackInfo ci) {
		Arrow self = (Arrow) (Object) this;
		if (self.level().isClientSide()) {
			return;
		}
		if (ArrowBan.isBanned(self.get(DataComponents.POTION_CONTENTS))) {
			self.discard();
			ci.cancel();
		}
	}

	/** Safety net: even if one gets a hit in before its first tick, no effect is applied. */
	@Inject(method = "doPostHurtEffects", at = @At("HEAD"), cancellable = true)
	private void noharmarrows$noEffect(LivingEntity target, CallbackInfo ci) {
		Arrow self = (Arrow) (Object) this;
		if (ArrowBan.isBanned(self.get(DataComponents.POTION_CONTENTS))) {
			ci.cancel();
		}
	}
}
