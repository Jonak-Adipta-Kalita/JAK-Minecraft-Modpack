package com.beastnighttv.jak.tiers;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import com.beastnighttv.jak.JAKMinecraftMod;
import net.minecraft.sounds.SoundEvent;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public record ModArmorMaterial(
	String name,
	int durability,
	int[] protection,
	int enchantability,
	SoundEvent equipSound,
	float toughness,
	float knockbackResistance,
	Supplier<Ingredient> repairMaterial
) implements ArmorMaterial {
    private static final int[] DURABILITY_PER_SLOT = new int[] {18, 20, 21, 16};

    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return DURABILITY_PER_SLOT[slot.getIndex()] * this.durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.protection[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Override
    public @NotNull String getName() {
        return JAKMinecraftMod.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}