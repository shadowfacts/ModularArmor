package net.shadowfacts.modulararmor.item.module

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import net.shadowfacts.modulararmor.util.energy.EnergyHandler
import net.shadowfacts.shadowmc.nbt.dsl.compound
import java.util.*

/**
 * @author shadowfacts
 */
class ModuleBattery: ItemModuleBase("battery") {

	override fun createModule(): IModule {
		return Battery()
	}

	class Battery: ModuleBase(EnumSet.of(EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET)) {

		val handler = EnergyHandler(2500)

		override fun addTooltip(stack: ItemStack, tooltip: MutableList<String>) {
			tooltip.add("Energy: ${handler.storage.energyStored} / ${handler.storage.maxEnergyStored}")
		}

		override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
			return handler.hasCapability(capability, facing)
		}

		override fun <T: Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
			return handler.getCapability(capability, facing)
		}

		override fun serializeNBT(): NBTTagCompound {
			return compound {
				"handler" to handler.serializeNBT()
			}
		}

		override fun deserializeNBT(tag: NBTTagCompound) {
			handler.deserializeNBT(tag.getTag("handler"))
		}

	}

}