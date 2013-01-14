/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gearteam.geartech;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import gearteam.geartech.client.handlers.ClientPacketHandler;
import gearteam.geartech.handlers.ServerPacketHandler;

@NetworkMod(clientSideRequired=true,serverSideRequired=false,
clientPacketHandlerSpec = @SidedPacketHandler(channels = {"TutorialGeneral" }, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec = @SidedPacketHandler(channels = {}, packetHandler = ServerPacketHandler.class))
@Mod(modid = "geartech", name = "Geartech", version = "Unreleased")

public class ModGeartech {
    @Instance("geartech")
    public static ModGeartech instance = new ModGeartech();
    
    @SidedProxy(clientSide = "deverionx.tutorial.client.ClientProxy", serverSide = "geartech.gearteam.CommonProxy")
    public static CommonProxy proxy;
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Pre-initialization
    }
    
    @Mod.Init
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(this, proxy); //Registers the class that deals with GUI data
    }
    
    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event) {
        // Post-initialization
    }
}
