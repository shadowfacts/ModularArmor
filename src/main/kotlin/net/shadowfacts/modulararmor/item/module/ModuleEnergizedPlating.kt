package net.shadowfacts.modulararmor.item.module

import com.google.common.collect.Multimap
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraftforge.energy.CapabilityEnergy.ENERGY
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.IModuleProvider
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import java.util.*

/**
 * @author shadowfacts
 */
class ModuleEnergizedPlating: ItemModuleBase("energized_plating") {

	override fun createModule(): IModule {
		return EnergizedPlating()
	}

	class EnergizedPlating: ModuleBase(EnumSet.of(EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET)) {

		companion object {
			val ID = UUID.fromString("599c9068-2b29-462d-a7bd-c7aea16c7d40")
		}

		override fun applyAttributeModifiers(provider: IModuleProvider, modifiers: Multimap<String, AttributeModifier>) {
			if (provider.hasCapability(ENERGY, null) && provider.getCapability(ENERGY, null)!!.energyStored > 100) {
				val armors = modifiers.get(SharedMonsterAttributes.ARMOR.name).sumByDouble { it.amount }
				val toughness = modifiers.get(SharedMonsterAttributes.ARMOR_TOUGHNESS.name).sumByDouble { it.amount }
				modifiers.removeAll(SharedMonsterAttributes.ARMOR.name)
				modifiers.removeAll(SharedMonsterAttributes.ARMOR_TOUGHNESS.name)
				modifiers.put(SharedMonsterAttributes.ARMOR.name, AttributeModifier(ID, "Armor Plating modifier", armors + 4.0, 0))
				modifiers.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.name, AttributeModifier(ID, "Armor Plating toughness", toughness + 1.0, 0))
			}
		}

		override fun absorbDamage(stack: ItemStack, slot: Int, provider: IModuleProvider, maxAmount: Int): Int {
			if (provider.hasCapability(ENERGY, null)) {
				val storage = provider.getCapability(ENERGY, null)!!
				val amount = Math.min(storage.energyStored, maxAmount * 100)
				val extracted = storage.extractEnergy(amount, true)
				val absorbed = extracted / 100
				storage.extractEnergy(absorbed * 100, false)
				return absorbed
			}
			return 0
		}

	}

}