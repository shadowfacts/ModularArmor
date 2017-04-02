package net.shadowfacts.modulararmor.api;

import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

/**
 * @author shadowfacts
 */
public interface IModule extends ICapabilitySerializable<NBTTagCompound> {

	@Nonnull
	Set<EntityEquipmentSlot> getValidSlots();

	void applyAttributeModifiers(@Nonnull IModuleProvider provider, @Nonnull Multimap<String, AttributeModifier> modifiers);

	int absorbDamage(@Nonnull ItemStack stack, int slot, @Nonnull IModuleProvider provider, int maxAmount);

	void addTooltip(@Nonnull ItemStack stack, @Nonnull List<String> tooltip);
}
