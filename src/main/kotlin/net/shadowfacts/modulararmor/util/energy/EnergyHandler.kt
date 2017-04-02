package net.shadowfacts.modulararmor.util.energy

import net.darkhax.tesla.capability.TeslaCapabilities.*
import net.minecraft.nbt.NBTBase
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.energy.CapabilityEnergy.ENERGY
import net.minecraftforge.energy.EnergyStorage

/**
 * @author shadowfacts
 */
class EnergyHandler(capacity: Int): ICapabilitySerializable<NBTBase> {

	val storage = EnergyStorage(capacity)
	val tesla = TeslaAdapter(storage)

	override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
		return capability == ENERGY || capability == CAPABILITY_HOLDER || capability == CAPABILITY_CONSUMER || capability == CAPABILITY_PRODUCER
	}

	override fun <T: Any?> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
		if (capability == ENERGY) {
			return storage as T
		} else if (capability == CAPABILITY_HOLDER || capability == CAPABILITY_CONSUMER || capability == CAPABILITY_PRODUCER) {
			return tesla as T
		} else {
			return null
		}
	}

	override fun serializeNBT(): NBTBase {
		return ENERGY.storage.writeNBT(ENERGY, storage, null)!!
	}

	override fun deserializeNBT(tag: NBTBase) {
		ENERGY.storage.readNBT(ENERGY, storage, null, tag)
	}

}