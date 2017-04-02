package net.shadowfacts.modulararmor.api.impl

import com.google.common.collect.Multimap
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.IModuleProvider

/**
 * @author shadowfacts
 */
open class ModuleBase(val slots: Set<EntityEquipmentSlot>): IModule {

	override fun getValidSlots(): Set<EntityEquipmentSlot> {
		return slots
	}

	override fun applyAttributeModifiers(provider: IModuleProvider, modifiers: Multimap<String, AttributeModifier>) {
	}

	override fun absorbDamage(stack: ItemStack, slot: Int, provider: IModuleProvider, maxAmount: Int): Int {
		return 0
	}

	override fun addTooltip(stack: ItemStack, tooltip: MutableList<String>) {
	}

	override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
		return false
	}

	override fun <T: Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
		return null
	}

	override fun serializeNBT(): NBTTagCompound {
		return NBTTagCompound()
	}

	override fun deserializeNBT(tag: NBTTagCompound) {
	}

}