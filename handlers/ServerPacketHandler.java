package logico.geartech.handlers;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.network.INetworkManager;

import net.minecraft.network.packet.Packet250CustomPayload;

public class ServerPacketHandler implements IPacketHandler {

	@Override public void onPacketData (final INetworkManager manager, final Packet250CustomPayload payload, final Player player) {

		final DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data));
		final EntityPlayer sender = (EntityPlayer) player;

	}

}