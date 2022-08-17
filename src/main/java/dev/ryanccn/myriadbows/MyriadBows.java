package dev.ryanccn.myriadbows;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyriadBows implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Myriad Bows");
    public static Enchantment ENCHANTMENT = new MyriadEnchantment();
    public static S2CPackets S2C = new S2CPackets();

	@Override
	public void onInitialize(ModContainer mod) {
        LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
        Registry.register(Registry.ENCHANTMENT, new Identifier("myriadbows", "myriad"), ENCHANTMENT);
        S2C.registerHandlers();
	}
}
