package net.shadowfacts.modulararmor.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.items.IItemHandler
import net.shadowfacts.forgelin.extensions.forEach
import net.shadowfacts.modulararmor.ModularArmor
import net.shadowfacts.modulararmor.gui.GUI

/**
 * @author shadowfacts
 */
fun EntityPlayer.openGui(type: GUI, world: World, x: Int, y: Int, z: Int) {
	openGui(ModularArmor, type.ordinal, world, x, y, z)
}

fun EntityPlayer.openGui(type: GUI, world: World, pos: BlockPos) {
	openGui(type, world, pos.x, pos.y, pos.z)
}
