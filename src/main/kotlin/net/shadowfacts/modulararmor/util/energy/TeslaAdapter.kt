package net.shadowfacts.modulararmor.util.energy

import net.darkhax.tesla.api.ITeslaConsumer
import net.darkhax.tesla.api.ITeslaHolder
import net.darkhax.tesla.api.ITeslaProducer
import net.minecraftforge.energy.EnergyStorage

/**
 * @author shadowfacts
 */
class TeslaAdapter(val storage: EnergyStorage): ITeslaHolder, ITeslaConsumer, ITeslaProducer {

	override fun getStoredPower(): Long {
		return storage.energyStored.toLong()
	}

	override fun getCapacity(): Long {
		return storage.maxEnergyStored.toLong()
	}

	override fun givePower(power: Long, simulated: Boolean): Long {
		return storage.receiveEnergy(power.toInt(), simulated).toLong()
	}

	override fun takePower(power: Long, simulated: Boolean): Long {
		return storage.extractEnergy(power.toInt(), simulated).toLong()
	}

}