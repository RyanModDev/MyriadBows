package dev.ryanccn.myriadbows;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class MyriadBowsClient implements ClientModInitializer {
    public static S2CPackets S2C = new S2CPackets();

    @Override
    public void onInitializeClient(ModContainer mod) {
        S2C.registerHandlers();
    }
}
