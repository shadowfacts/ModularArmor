package net.shadowfacts.modulararmor

import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager
import net.shadowfacts.modulararmor.api.IModule
import net.shadowfacts.modulararmor.api.IModuleProvider
import net.shadowfacts.modulararmor.api.impl.ModuleBase
import net.shadowfacts.modulararmor.api.impl.ModuleProvider
import net.shadowfacts.shadowmc.capability.Storage

/**
 * @author shadowfacts
 */
object ModCapabilities {

	@CapabilityInject(IModuleProvider::class)
	lateinit var MODULE_PROVIDER: Capability<IModuleProvider>

	@CapabilityInject(IModule::class)
	lateinit var MODULE: Capability<IModule>

	fun register() {
		CapabilityManager.INSTANCE.register(IModuleProvider::class.java, Storage(), ModuleProvider::class.java)
		CapabilityManager.INSTANCE.register(IModule::class.java, Storage(), ModuleBase::class.java)
	}

}