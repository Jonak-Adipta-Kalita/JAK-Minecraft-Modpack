package com.beastnighttv.jak.tiers;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ArmorMaterial;
import com.beastnighttv.jak.item.ModItems;

public class ArmorTiers {
	public static final ArmorMaterial ZIRCON = new ModArmorMaterial(
		"zircon",
    	37,
        new int[]{5, 8, 10, 5},
        40,
        SoundEvents.ARMOR_EQUIP_NETHERITE,
        5f,
        0.3f,
        () -> Ingredient.of(ModItems.ZIRCON.get())
	);
}
