package dev.ryanccn.myriadbows;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public final class S2CPackets {
    public S2CPackets() {}

    private static final Identifier COMMS_CHANNEL = new Identifier("myriadbows", "arrow_used");

    public void sendPacket(ItemStack data, ServerPlayerEntity player) {
        final var buf = PacketByteBufs.create();

        buf.writeItemStack(data);

        ServerPlayNetworking.send(player, COMMS_CHANNEL, buf);
    }

    @Environment(EnvType.CLIENT)
    public void registerHandlers() {
        // registers the packet handlers
        // call this in your client entrypoint
        ClientPlayNetworking.registerGlobalReceiver(COMMS_CHANNEL, this::handlePacket);
    }

    @Environment(EnvType.CLIENT)
    private void handlePacket(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        // this is currently in a network worker thread
        // read and parse packet data and do NOT do anything else here!
        final var data = buf.readItemStack();

        client.execute(() -> data.decrement(1));
    }
}
