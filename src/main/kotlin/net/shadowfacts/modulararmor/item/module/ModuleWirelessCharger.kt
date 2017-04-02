package net.shadowfacts.modulararmor.item.module

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy.ENERGY
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.IModuleProvider
import net.shadowfacts.modulararmor.api.ITickableModule
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import java.util.*

/**
 * @author shadowfacts
 */
class ModuleWirelessCharger: ItemModuleBase("wireless_charger") {

	override fun createModule(): IModule {
		return WirelessCharger()
	}

	class WirelessCharger: ModuleBase(EnumSet.of(EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET)), ITickableModule {

		var slot = 0

		override fun update(player: EntityPlayer, provider: IModuleProvider) {
			if (provider.hasCapability(ENERGY, null)) {
				val storage = provider.getCapability(ENERGY, null)!!
				val stack = player.inventory.mainInventory[slot]
				if (!stack.isEmpty && stack.hasCapability(ENERGY, null)) {
					val receiver = stack.getCapability(ENERGY, null)!!
					val amount = receiver.receiveEnergy(storage.extractEnergy(100, true), false)
					if (amount != 0) {
						storage.extractEnergy(amount, false)
						return
					}
				}
				slot++
				if (slot >= player.inventory.mainInventory.size) slot = 0
			}
		}

	}

}