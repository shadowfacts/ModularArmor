package net.shadowfacts.modulararmor.item

import net.shadowfacts.modulararmor.item.armor.ArmorBoots
import net.shadowfacts.modulararmor.item.armor.ArmorChestplate
import net.shadowfacts.modulararmor.item.armor.ArmorHelmet
import net.shadowfacts.modulararmor.item.armor.ArmorLeggings
import net.shadowfacts.modulararmor.item.module.ModuleArmorPlating
import net.shadowfacts.modulararmor.item.module.ModuleBattery
import net.shadowfacts.modulararmor.item.module.ModuleSolarPanel
import net.shadowfacts.modulararmor.item.module.ModuleWirelessCharger
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
	val solarPanel = ModuleSolarPanel()
	val wirelessCharger = ModuleWirelessCharger()
	val armorPlating = ModuleArmorPlating()

	override fun init() {
//		Armors
		register(helmet)
		register(chestplate)
		register(leggings)
		register(boots)

//		Modules
		register(battery)
		register(solarPanel)
		register(wirelessCharger)
		register(armorPlating)
	}

}