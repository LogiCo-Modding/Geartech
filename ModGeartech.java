package logico.geartech;

import logico.geartech.CommonProxy;
import logico.geartech.client.handlers.ClientPacketHandler;
import logico.geartech.gear.Gear;
import logico.geartech.handlers.ServerPacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;





@NetworkMod(clientSideRequired=true,serverSideRequired=false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"TutorialGeneral" }, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {}, packetHandler = ServerPacketHandler.class))

@Mod(modid = "geartech", name = "Geartech", version = "0.1")

public class ModGeartech {

	@Instance("geartech") public static ModGeartech instance = new ModGeartech();

	@SidedProxy(clientSide = "logico.geartech.client.ClientProxy", serverSide = "logico.geartech.CommonProxy")
	public static CommonProxy proxy;

	@Mod.PreInit public void preInit (final FMLPreInitializationEvent event) {}

	@Mod.Init public void init (final FMLInitializationEvent event) {

		NetworkRegistry.instance().registerGuiHandler(this, proxy);
		Gear.load();
		proxy.registerTiles();

	}

	@Mod.PostInit public void postInit (final FMLPostInitializationEvent event) {



	}

}