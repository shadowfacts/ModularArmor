package net.shadowfacts.modulararmor.item.armor.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraftforge.items.SlotItemHandler
import net.shadowfacts.forgelin.extensions.get
import net.shadowfacts.modulararmor.ModCapabilities.MODULE_PROVIDER
import net.shadowfacts.modulararmor.util.SlotUnmodifiable
import net.shadowfacts.shadowmc.inventory.ContainerBase

/**
 * @author shadowfacts
 */
class ContainerArmor(player: EntityPlayer, slot: Int, stack: ItemStack = player.inventory[slot]): ContainerBase(BlockPos.ORIGIN) {

	init {
		val inv = stack.getCapability(MODULE_PROVIDER, null)!!.inventory
		for (i in 0.until(inv.slots)) {
			addSlotToContainer(SlotItemHandler(inv, i, 44 + i * 18, 35))
		}

		for (i in 0..2) {
			for (j in 0..8) {
				addSlotToContainer(Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
			}
		}

		for (k in 0..8) {
			if (slot == k) {
				addSlotToContainer(SlotUnmodifiable(player.inventory, k, 8 + k * 18, 142))
			} else {
				addSlotToContainer(Slot(player.inventory, k, 8 + k * 18, 142))
			}
		}
	}

	override fun canInteractWith(player: EntityPlayer): Boolean {
		return true
	}

}