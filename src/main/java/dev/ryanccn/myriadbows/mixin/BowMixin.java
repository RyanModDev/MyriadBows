package dev.ryanccn.myriadbows.mixin;

import dev.ryanccn.myriadbows.MyriadBows;
import dev.ryanccn.myriadbows.MyriadBowsClient;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;
import java.util.Random;

@Mixin(BowItem.class)
public class BowMixin {
    private final Random rand = new Random();

    private boolean shouldConsumeForLevel(int lvl) {
        if (lvl == 1) return rand.nextFloat() < 0.3;
        else if (lvl == 2) return rand.nextFloat() < 0.2;
        else if (lvl == 3) return rand.nextFloat() < 0.1;

        return true;
    }

    @ModifyVariable(method = "onStoppedUsing", at = @At("STORE"), ordinal = 1)
    private boolean a(boolean b, ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int lvl = EnchantmentHelper.getLevel(MyriadBows.ENCHANTMENT, stack);

        if (!world.isClient) {
            boolean ret = b || (lvl > 0 && !shouldConsumeForLevel(lvl));

            if (!ret) {
                MyriadBowsClient.S2C.sendPacket(stack, Objects.requireNonNull(user.getServer()).getPlayerManager().getPlayer(user.getUuid()));
            }

            return ret;
        }

        return lvl > 0 || b;
    }
}
