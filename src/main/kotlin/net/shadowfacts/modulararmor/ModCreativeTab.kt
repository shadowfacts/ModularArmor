package net.shadowfacts.modulararmor

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.shadowfacts.modulararmor.item.ModItems

/**
 * @author shadowfacts
 */
object ModCreativeTab: CreativeTabs(MOD_ID) {

	override fun getTabIconItem(): ItemStack {
		return ItemStack(ModItems.helmet)
	}

}