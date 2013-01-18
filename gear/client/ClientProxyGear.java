package logico.geartech.gear.client;

import logico.geartech.gear.renderers.TileEntityShaftRenderer;
import logico.geartech.gear.tileentities.TileEntityShaft;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;



public class ClientProxyGear {

	final public static int shaftModelId = RenderingRegistry.getNextAvailableRenderId();

	public static void registerRenderInformation () {}

	public static void registerTiles () {

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShaft.class, new TileEntityShaftRenderer());

	}

}