package net.shadowfacts.modulararmor.item.module

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.shadowfacts.modulararmor.ModCapabilities.MODULE
import net.shadowfacts.modulararmor.ModularArmor
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.item.ItemBase

/**
 * @author shadowfacts
 */
abstract class ItemModuleBase(name: String): ItemBase(name) {

	init {
		creativeTab = ModularArmor.TAB
	}

	override fun addInformation(stack: ItemStack, player: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
		stack.getCapability(MODULE, null)!!.addTooltip(stack, tooltip)
	}

	abstract fun createModule(): IModule

	override fun initCapabilities(stack: ItemStack, nbt: NBTTagCompound?): ICapabilityProvider? {
		return Provider(createModule())
	}

	class Provider(val module: IModule): ICapabilitySerializable<NBTTagCompound> {

		override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
			return capability == MODULE || module.hasCapability(capability, facing)
		}

		override fun <T: Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
			return if (capability == MODULE) module as T else module.getCapability(capability, facing)
		}

		override fun serializeNBT(): NBTTagCompound {
			return module.serializeNBT()
		}

		override fun deserializeNBT(tag: NBTTagCompound) {
			module.deserializeNBT(tag)
		}

	}

}