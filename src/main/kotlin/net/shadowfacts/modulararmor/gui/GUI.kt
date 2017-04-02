package net.shadowfacts.modulararmor.gui

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.shadowfacts.forgelin.extensions.get
import net.shadowfacts.modulararmor.item.armor.gui.ContainerArmor
import net.shadowfacts.modulararmor.item.armor.gui.GUIArmor

/**
 * @author shadowfacts
 */
enum class GUI(val screen: (EntityPlayer, World, Int, Int, Int) -> Any?, val container: (EntityPlayer, World, Int, Int, Int) -> Container?) {
	ARMOR({ player, _, slot, _, _ ->
		GUIArmor.create(ContainerArmor(player, slot), player.inventory[slot])
	}, { player, _, slot, _, _ ->
		ContainerArmor(player, slot)
	});

	constructor(screen: (EntityPlayer, World, BlockPos) -> Any?, container: (EntityPlayer, World, BlockPos) -> Container?):
			this({ player, world, x, y, z ->
				screen(player, world, BlockPos(x, y, z))
			}, { player, world, x, y, z ->
				container(player, world, BlockPos(x, y, z))
			})
}