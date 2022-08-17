package dev.ryanccn.myriadbows;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class MyriadEnchantment extends Enchantment {
    public MyriadEnchantment() {
        super(Rarity.COMMON, EnchantmentTarget.BOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    public boolean canAccept(Enchantment other) {
        return !(other instanceof MendingEnchantment) && !(other instanceof InfinityEnchantment) && super.canAccept(other);
    }
}
