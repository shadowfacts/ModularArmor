package net.shadowfacts.modulararmor.api.impl

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler
import net.shadowfacts.forgelin.extensions.forEach
import net.shadowfacts.forgelin.extensions.set
import net.shadowfacts.modulararmor.ModCapabilities.MODULE
import net.shadowfacts.modulararmor.api.IModuleProvider
import net.shadowfacts.modulararmor.util.containsItem

/**
 * @author shadowfacts
 */
class ModuleProvider(size: Int, val slot: EntityEquipmentSlot): IModuleProvider {

	val inventory = object: ItemStackHandler(size) {

		override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
			if (!isValid(stack)) return stack
			return super.insertItem(slot, stack, simulate)
		}

		override fun getSlotLimit(slot: Int): Int {
			return 1
		}

	}

	override fun getInventory(): IItemHandler {
		return inventory
	}

	override fun isValid(stack: ItemStack): Boolean {
		return !stack.isEmpty &&
				stack.hasCapability(MODULE, null) &&
				stack.getCapability(MODULE, null)!!.validSlots.contains(slot) &&
				!inventory.containsItem(stack.item)
	}

	override fun set(i: Int, stack: ItemStack) {
		inventory[i] = stack
	}

	override fun serializeNBT(): NBTTagCompound {
		return inventory.serializeNBT()
	}

	override fun deserializeNBT(tag: NBTTagCompound) {
		inventory.deserializeNBT(tag)
	}

	override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
		inventory.forEach {
			if (!it.isEmpty && it.getCapability(MODULE, null)!!.hasCapability(capability, facing)) {
				return true
			}
		}
		return false
	}

	override fun <T: Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
		inventory.forEach {
			if (!it.isEmpty && it.getCapability(MODULE, null)!!.hasCapability(capability, facing)) {
				return it.getCapability(capability, facing)
			}
		}
		return null
	}

}