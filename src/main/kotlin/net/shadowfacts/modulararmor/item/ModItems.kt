package net.shadowfacts.modulararmor.item

import net.shadowfacts.modulararmor.item.armor.ArmorBoots
import net.shadowfacts.modulararmor.item.armor.ArmorChestplate
import net.shadowfacts.modulararmor.item.armor.ArmorHelmet
import net.shadowfacts.modulararmor.item.armor.ArmorLeggings
import net.shadowfacts.modulararmor.item.module.*
import net.shadowfacts.shadowmc.item.ModItems

/**
 * @author shadowfacts
 */
object ModItems: ModItems() {

//	Armors
	val helmet = ArmorHelmet()
	val chestplate = ArmorChestplate()
	val leggings = ArmorLeggings()
	val boots = ArmorBoots()

//	Modules
	val battery = ModuleBattery()
	val mediumBattery = ModuleMediumBattery()
	val largeBattery = ModuleLargeBattery()
	val solarPanel = ModuleSolarPanel()
	val wirelessCharger = ModuleWirelessCharger()
	val armorPlating = ModuleArmorPlating()
	val energizedPlating = ModuleEnergizedPlating()
	val highJump = ModuleHighJump()
	val longFall = ModuleLongFall()

	override fun init() {
//		Armors
		register(helmet)
		register(chestplate)
		register(leggings)
		register(boots)

//		Modules
		register(battery)
		register(mediumBattery)
		register(largeBattery)
		register(solarPanel)
		register(wirelessCharger)
		register(armorPlating)
		register(energizedPlating)
		register(highJump)
		register(longFall)
	}

}