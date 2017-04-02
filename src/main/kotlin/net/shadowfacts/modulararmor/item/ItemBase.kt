package net.shadowfacts.modulararmor.item

import net.shadowfacts.shadowmc.item.ItemBase

/**
 * @author shadowfacts
 */
open class ItemBase(name: String): ItemBase(name) {

	init {
		unlocalizedName = registryName.toString()
	}

}