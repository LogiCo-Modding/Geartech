/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gearteam.geartech;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "geartech", name = "Geartech", version = "Unreleased")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ModGeartech {
    @Mod.Instance("geartech")
    public static ModGeartech instance;
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Pre-initialization
    }
    
    @Mod.Init
    public void init(FMLInitializationEvent event) {
        // Initialization
    }
    
    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event) {
        // Post-initialization
    }
}
