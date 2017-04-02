package net.shadowfacts.modulararmor.api;

import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public interface ITickableModule extends IModule {

	void update(@Nonnull EntityPlayer player, @Nonnull IModuleProvider provider);

}
