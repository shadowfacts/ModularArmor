package net.shadowfacts.modulararmor.item.armor

import com.google.common.collect.Multimap
import net.minecraft.client.resources.I18n
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.shadowfacts.forgelin.extensions.forEach
import net.shadowfacts.forgelin.extensions.get
import net.shadowfacts.modulararmor.ModCapabilities.MODULE
import net.shadowfacts.modulararmor.ModCapabilities.MODULE_PROVIDER
import net.shadowfacts.modulararmor.ModularArmor
import net.shadowfacts.modulararmor.api.ITickableModule
import net.shadowfacts.modulararmor.api.impl.ModuleProvider
import net.shadowfacts.modulararmor.gui.GUI
import net.shadowfacts.modulararmor.util.openGui
import net.shadowfacts.shadowmc.ShadowMC
import net.shadowfacts.shadowmc.item.ItemModelProvider

/**
 * @author shadowfacts
 */
abstract class ItemModularArmor(name: String, slot: EntityEquipmentSlot): ItemArmor(ModularArmor.MATERIAL, 0, slot), ItemModelProvider {

	init {
		setRegistryName(name)
		unlocalizedName = registryName.toString()
		creativeTab = ModularArmor.TAB
	}

	override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack> {
		val heldItem = player.getHeldItem(hand)
		val slot = if (hand == EnumHand.OFF_HAND) 106 else player.inventory.currentItem
		player.openGui(GUI.ARMOR, world, slot, 0, 0)
		return ActionResult(EnumActionResult.SUCCESS, heldItem)
	}

	override fun addInformation(stack: ItemStack, player: EntityPlayer, tooltip: MutableList<String>, advanced: Boolean) {
		stack.getCapability(MODULE_PROVIDER, null)!!.inventory.forEach {
			if (!it.isEmpty) {
				tooltip.add(I18n.format(it.unlocalizedName + ".module"))
				val temp = mutableListOf<String>()
				it.getCapability(MODULE, null)!!.addTooltip(it, temp)
				temp.forEach {
					tooltip.add("  $it")
				}
			}
		}
	}

	override fun onArmorTick(world: World, player: EntityPlayer, stack: ItemStack) {
		val provider = stack.getCapability(MODULE_PROVIDER, null)!!
		provider.inventory.forEach {
			if (!it.isEmpty) {
				val module = it.getCapability(MODULE, null)!!
				if (module is ITickableModule) {
					module.update(player, provider)
				}
			}
		}
	}

	override fun getAttributeModifiers(slot: EntityEquipmentSlot, stack: ItemStack): Multimap<String, AttributeModifier> {
		val map = super.getAttributeModifiers(slot, stack)
		if (slot == armorType) {
			val provider = stack.getCapability(MODULE_PROVIDER, null)!!
			provider.inventory.forEach {
				if (!it.isEmpty) {
					val module = it.getCapability(MODULE, null)!!
					module.applyAttributeModifiers(provider, map)
				}
			}
		}
		return map
	}

	override fun setDamage(stack: ItemStack, damage: Int) {
		val damage = Math.max(damage, 0)
		var diff = damage - stack.itemDamage
		if (diff < 0) {
			super.setDamage(stack, damage)
		} else {
			val provider = stack.getCapability(MODULE_PROVIDER, null)!!
			for (i in 0.until(provider.inventory.slots)) {
				val module = provider.inventory[i]
				if (!module.isEmpty) {
					diff -= module.getCapability(MODULE, null)!!.absorbDamage(module, i, provider, diff)
					if (diff == 0) {
						return
					}
				}
			}
			if (diff > 0) {
				super.setDamage(stack, stack.itemDamage + diff)
			}
		}
	}

	override fun initItemModel() {
		ShadowMC.proxy.registerItemModel(this, 0, registryName)
	}

	override fun initCapabilities(stack: ItemStack?, nbt: NBTTagCompound?): ICapabilityProvider? {
		return Provider(armorType)
	}

	class Provider(slot: EntityEquipmentSlot): ICapabilitySerializable<NBTTagCompound> {

		val modules = ModuleProvider(5, slot)

		override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
			return capability == MODULE_PROVIDER || modules.hasCapability(capability, facing)
		}

		override fun <T: Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
			return if (capability == MODULE_PROVIDER) modules as T else modules.getCapability(capability, facing)
		}

		override fun serializeNBT(): NBTTagCompound {
			return modules.serializeNBT()
		}

		override fun deserializeNBT(tag: NBTTagCompound) {
			return modules.deserializeNBT(tag)
		}

	}

}