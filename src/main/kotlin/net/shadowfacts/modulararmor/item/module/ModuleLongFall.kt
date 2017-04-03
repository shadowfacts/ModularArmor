package net.shadowfacts.modulararmor.item.module

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.energy.CapabilityEnergy.ENERGY
import net.minecraftforge.event.entity.living.LivingFallEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.shadowfacts.modulararmor.ModCapabilities.MODULE_PROVIDER
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import java.util.*

/**
 * @author shadowfacts
 */
class ModuleLongFall: ItemModuleBase("long_fall") {

	init {
		MinecraftForge.EVENT_BUS.register(this)
	}

	override fun createModule(): IModule {
		return LongFall()
	}

	@SubscribeEvent
	fun onLivingFall(event: LivingFallEvent) {
		if (event.distance < 3) return

		val player = event.entityLiving
		if (player is EntityPlayer) {
			val boots = player.inventory.armorInventory[0]
			if (!boots.isEmpty && boots.hasCapability(MODULE_PROVIDER, null)) {
				val provider = boots.getCapability(MODULE_PROVIDER, null)!!
				if (provider.hasModule(this) && provider.hasCapability(ENERGY, null)) {
					val energy = provider.getCapability(ENERGY, null)!!
					val amount = (event.distance * 25).toInt()
					val extracted = energy.extractEnergy(amount, true)
					if (extracted > 0) {
						val absorbed = extracted / 25
						energy.extractEnergy(absorbed * 25, false)
						event.distance -= absorbed
					}
				}
			}
		}
	}

	class LongFall: ModuleBase(EnumSet.of(EntityEquipmentSlot.FEET))

}