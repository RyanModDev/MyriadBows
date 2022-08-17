package dev.ryanccn.myriadbows;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.InfinityEnchantment;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;

public class MyriadEnchantment extends Enchantment {
    public MyriadEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentTarget.BOW,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMinPower(int level) {
        return 10;
    }

    @Override
    public int getMaxPower(int level) {
        return 40;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public boolean canAccept(Enchantment other) {
        return !(other instanceof MendingEnchantment) && !(other instanceof InfinityEnchantment) && super.canAccept(other);
    }
}
