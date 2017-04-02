package net.shadowfacts.modulararmor.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

/**
 * @author shadowfacts
 */
object GUIHandler: IGuiHandler {

	override fun getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
		if (ID in 0..GUI.values().size) {
			return GUI.values()[ID].screen(player, world, x, y, z)
		} else {
			return null
		}
	}

	override fun getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
		if (ID in 0..GUI.values().size) {
			return GUI.values()[ID].container(player, world, x, y, z)
		} else {
			return null
		}
	}

}