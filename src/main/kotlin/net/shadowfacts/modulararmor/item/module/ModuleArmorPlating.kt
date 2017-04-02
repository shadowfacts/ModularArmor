package net.shadowfacts.modulararmor.item.module

import com.google.common.collect.Multimap
import net.minecraft.entity.SharedMonsterAttributes.ARMOR
import net.minecraft.entity.SharedMonsterAttributes.ARMOR_TOUGHNESS
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandlerModifiable
import net.shadowfacts.forgelin.extensions.set
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.IModuleProvider
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import java.util.*

/**
 * @author shadowfacts
 */
class ModuleArmorPlating: ItemModuleBase("armor_plating") {

	init {
		maxDamage = 250
	}

	override fun createModule(): IModule {
		return ArmorPlating()
	}

	class ArmorPlating: ModuleBase(EnumSet.of(EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET)) {

		companion object {
			private val ID = UUID.fromString("79851549-9a76-4671-ab69-9907a1e87d18")
		}

		override fun applyAttributeModifiers(provider: IModuleProvider, modifiers: Multimap<String, AttributeModifier>) {
			val armors = modifiers.get(ARMOR.name).sumByDouble { it.amount }
			val toughness = modifiers.get(ARMOR_TOUGHNESS.name).sumByDouble { it.amount }
			modifiers.removeAll(ARMOR.name)
			modifiers.removeAll(ARMOR_TOUGHNESS.name)
			modifiers.put(ARMOR.name, AttributeModifier(ID, "Armor Plating modifier", armors + 4.0, 0))
			modifiers.put(ARMOR_TOUGHNESS.name, AttributeModifier(ID, "Armor Plating toughness", toughness + 1.0, 0))
		}

		override fun absorbDamage(stack: ItemStack, slot: Int, provider: IModuleProvider, maxAmount: Int): Int {
			val amount = Math.min(stack.maxDamage - stack.itemDamage, maxAmount)
			stack.itemDamage += amount
			if (stack.itemDamage == stack.maxDamage) {
				(provider.inventory as IItemHandlerModifiable)[slot] = ItemStack.EMPTY
			}
			return amount
		}

		override fun addTooltip(stack: ItemStack, tooltip: MutableList<String>) {
			tooltip.add("Durability: ${stack.maxDamage - stack.itemDamage} / ${stack.maxDamage}")
		}

	}

}