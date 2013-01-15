package gearteam.geartech.gear.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {
    public static void load() {
        GameRegistry.registerTileEntity(TileEntityShaft.class, "Shaft");
    }
}