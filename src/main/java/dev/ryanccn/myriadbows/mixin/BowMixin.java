package dev.ryanccn.myriadbows.mixin;

import dev.ryanccn.myriadbows.MyriadBows;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Mixin(BowItem.class)
public class BowMixin {
    private final Random rand = new Random();

    private boolean shouldConsumeForLevel(int lvl) {
        if (lvl == 0) return true;
        return rand.nextFloat() < 0.1;
    }

    @ModifyArgs(
            method="onStoppedUsing",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V")
    )
    private void inject(Args args, ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int myriadLevel = EnchantmentHelper.getLevel(MyriadBows.ENCHANTMENT, stack);
        args.set(0, shouldConsumeForLevel(myriadLevel) ? 1 : 0);
    }
}
