package gearteam.geartech.client.handlers;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

@SideOnly(Side.CLIENT)
public class ClientPacketHandler implements IPacketHandler {
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player){
        DataInputStream data = new DataInputStream(new ByteArrayInputStream(payload.data)); //Handles incoming data
    }
}