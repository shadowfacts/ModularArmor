package net.shadowfacts.modulararmor.item.module

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.energy.CapabilityEnergy.ENERGY
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.shadowfacts.modulararmor.ModCapabilities.MODULE_PROVIDER
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import net.shadowfacts.modulararmor.util.containsItem
import java.util.*

/**
 * @author shadowfacts
 */
class ModuleHighJump: ItemModuleBase("high_jump") {

	init {
		MinecraftForge.EVENT_BUS.register(this)
	}

	override fun createModule(): IModule {
		return HighJump()
	}

	@SubscribeEvent
	fun onJump(event: LivingEvent.LivingJumpEvent) {
		val player = event.entityLiving
		if (player is EntityPlayer) {
			val leggings = player.inventory.armorInventory[1]
			if (!leggings.isEmpty && leggings.hasCapability(MODULE_PROVIDER, null)) {
				val provider = leggings.getCapability(MODULE_PROVIDER, null)!!
				if (provider.inventory.containsItem(this) && provider.hasCapability(ENERGY, null)) {
					val energy = provider.getCapability(ENERGY, null)!!
					if (energy.extractEnergy(50, true) == 50) {
						energy.extractEnergy(50, false)
						player.addVelocity(0.0, 0.4, 0.0)
					}
				}
			}
		}
	}

	class HighJump: ModuleBase(EnumSet.of(EntityEquipmentSlot.LEGS))

}