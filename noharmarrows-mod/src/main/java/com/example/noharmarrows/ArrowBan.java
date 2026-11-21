package com.example.noharmarrows;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;

/** Decides which tipped arrows are banned: anything carrying Instant Damage (Harming) or Slow Falling. */
public final class ArrowBan {
	private ArrowBan() {}

	/** Checks every effect (base potion AND custom effects), so Strong/Long variants and custom potions are covered. */
	public static boolean isBanned(PotionContents contents) {
		if (contents == null) {
			return false;
		}
		for (MobEffectInstance instance : contents.getAllEffects()) {
			MobEffect effect = instance.getEffect().value();
			if (effect == MobEffects.INSTANT_DAMAGE.value() || effect == MobEffects.SLOW_FALLING.value()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBanned(ItemStack stack) {
		return !stack.isEmpty()
			&& stack.is(Items.TIPPED_ARROW)
			&& isBanned(stack.get(DataComponents.POTION_CONTENTS));
	}
}
