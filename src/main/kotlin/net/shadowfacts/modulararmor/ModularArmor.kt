package net.shadowfacts.modulararmor

import net.minecraft.init.SoundEvents
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.util.EnumHelper
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.shadowfacts.modulararmor.gui.GUIHandler
import net.shadowfacts.modulararmor.item.ModItems
import net.shadowfacts.modulararmor.util.EventHandler

/**
 * @author shadowfacts
 */
@Mod(modid = MOD_ID, name = NAME, version = VERSION, dependencies = "required-after:shadowmc;required-after:forgelin;", modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object ModularArmor {

	val MATERIAL = EnumHelper.addArmorMaterial("MODULAR_ARMOR", "modular_armor", 1000, intArrayOf(3, 6, 8, 3), 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1f)
	val TAB = ModCreativeTab

	@Mod.EventHandler
	fun preInit(event: FMLPreInitializationEvent) {
		ModItems.init()
		ModCapabilities.register()
		NetworkRegistry.INSTANCE.registerGuiHandler(this, GUIHandler)
		MinecraftForge.EVENT_BUS.register(EventHandler)
	}

}