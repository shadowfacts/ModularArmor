package net.shadowfacts.modulararmor.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

/**
 * @author shadowfacts
 */
public interface IModuleProvider extends ICapabilitySerializable<NBTTagCompound> {

	IItemHandler getInventory();

	boolean isValid(@Nonnull ItemStack stack);

	void set(int i, @Nonnull ItemStack stack);

}
