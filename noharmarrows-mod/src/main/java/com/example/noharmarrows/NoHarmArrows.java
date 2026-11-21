package com.example.noharmarrows;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class NoHarmArrows implements ModInitializer {
	/**
	 * true  = banned tipped arrows are deleted from player inventories (like the old datapack).
	 * false = players may keep them, but they vanish when shot and never apply an effect.
	 */
	public static final boolean SCRUB_INVENTORIES = true;

	@Override
	public void onInitialize() {
		if (SCRUB_INVENTORIES) {
			ServerTickEvents.END_SERVER_TICK.register(server -> {
				for (ServerPlayer player : server.getPlayerList().getPlayers()) {
					scrub(player);
				}
			});
		}
	}

	private static void scrub(ServerPlayer player) {
		Inventory inventory = player.getInventory();
		for (int i = 0; i < inventory.getContainerSize(); i++) {
			if (ArrowBan.isBanned(inventory.getItem(i))) {
				inventory.setItem(i, ItemStack.EMPTY);
			}
		}
		// Stack currently held on the mouse cursor inside a GUI
		if (ArrowBan.isBanned(player.containerMenu.getCarried())) {
			player.containerMenu.setCarried(ItemStack.EMPTY);
		}
	}
}
