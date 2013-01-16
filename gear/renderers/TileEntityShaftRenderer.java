package gearteam.geartech.gear.renderers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gearteam.geartech.gear.client.ClientProxyGear;

import gearteam.geartech.gear.models.ModelShaft;

import gearteam.geartech.gear.tileentities.TileEntityShaft;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT) public class TileEntityShaftRenderer extends TileEntitySpecialRenderer {

	private final ModelShaft modelShaft = new ModelShaft();

	@Override public void renderTileEntityAt (final TileEntity tileEntity, final double x, final double y, final double z, final float tick) {
            
                TileEntityShaft tileShaft = (TileEntityShaft) tileEntity;
		modelShaft.render(tileShaft, x, y, z, tileShaft.getRotation());

	}

	public int getRenderId () {

		return ClientProxyGear.shaftModelId;

	}

}