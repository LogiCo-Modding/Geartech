/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gearteam.geartech.gear.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import gearteam.geartech.gear.renderers.TileEntityShaftRenderer;
import gearteam.geartech.gear.tileentities.TileEntityShaft;

public class ClientProxyGear {
    final public static int shaftModelId = RenderingRegistry.getNextAvailableRenderId();
    
    public static void registerRenderInformation() {
    }
    
    public static void registerTiles() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShaft.class, new TileEntityShaftRenderer());
    }
}
