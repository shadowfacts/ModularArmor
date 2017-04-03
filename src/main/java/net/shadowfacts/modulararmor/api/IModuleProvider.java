package net.shadowfacts.modulararmor.api;

import net.minecraft.item.Item;
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

	default boolean hasModule(@Nonnull Item item) {
		IItemHandler inv = getInventory();
		for (int i = 0; i < inv.getSlots(); i++) {
			if (inv.getStackInSlot(i).getItem() == item) {
				return true;
			}
		}
		return false;
	}

	default ItemStack getModule(@Nonnull Item item) {
		IItemHandler inv = getInventory();
		for (int i = 0; i < inv.getSlots(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (stack.getItem() == item) {
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}

}
