package net.shadowfacts.modulararmor.item.module

import net.darkhax.tesla.capability.TeslaCapabilities.CAPABILITY_CONSUMER
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraftforge.energy.CapabilityEnergy.ENERGY
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.IModuleProvider
import net.shadowfacts.modulararmor.api.ITickableModule
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import java.util.*

/**
 * @author shadowfacts
 */
class ModuleSolarPanel: ItemModuleBase("solar_panel") {

	override fun createModule(): IModule {
		return SolarPanel()
	}

	class SolarPanel: ModuleBase(EnumSet.of(EntityEquipmentSlot.HEAD)), ITickableModule {

		override fun update(player: EntityPlayer, provider: IModuleProvider) {
			if (!player.world.isRemote && player.world.isDaytime && player.world.canBlockSeeSky(player.position)) {
				for (it in player.inventory.armorInventory) {
					if (!it.isEmpty) {
						if (it.hasCapability(ENERGY, null)) {
							if (it.getCapability(ENERGY, null)!!.receiveEnergy(10, false) > 0) {
								break
							}
						} else if (it.hasCapability(CAPABILITY_CONSUMER, null)) {
							if (it.getCapability(CAPABILITY_CONSUMER, null)!!.givePower(10, false) > 0) {
								break
							}
						}
					}
				}
			}
		}

	}

}