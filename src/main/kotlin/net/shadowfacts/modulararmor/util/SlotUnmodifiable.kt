package net.shadowfacts.modulararmor.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

/**
 * @author shadowfacts
 */
class SlotUnmodifiable(inventory: IInventory, id: Int, x: Int, y: Int): Slot(inventory, id, x, y) {

	override fun canTakeStack(player: EntityPlayer): Boolean {
		return false
	}

	override fun putStack(stack: ItemStack) {
	}

}