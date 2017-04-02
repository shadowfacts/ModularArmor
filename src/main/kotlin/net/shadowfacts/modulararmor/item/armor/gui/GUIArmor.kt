package net.shadowfacts.modulararmor.item.armor.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.resources.I18n
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.shadowfacts.modulararmor.MOD_ID
import net.shadowfacts.shadowmc.ui.dsl.*

/**
 * @author shadowfacts
 */
object GUIArmor {

	private val BG = ResourceLocation(MOD_ID, "textures/gui/armor.png")

	fun create(container: Container, stack: ItemStack): GuiContainer {
		return container(container) {
			fixed {
				id = "root"
				width = 176
				height = 166

				image {
					id = "bg"
					width = 176
					height = 166
					texture = BG
				}

				label {
					id = "armor"
					text = I18n.format(stack.unlocalizedName + ".name")
				}

				label {
					id = "inventory"
					text = Minecraft.getMinecraft().player.inventory.displayName.unformattedText
				}
			}

			style("$MOD_ID:armor")
		}
	}

}