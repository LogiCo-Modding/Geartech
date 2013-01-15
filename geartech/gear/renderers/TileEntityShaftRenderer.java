/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gearteam.geartech.gear.renderers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gearteam.geartech.gear.client.ClientProxyGear;
import gearteam.geartech.gear.models.ModelShaft;
import gearteam.geartech.gear.tileentities.TileEntityShaft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

@SideOnly(Side.CLIENT)
public class TileEntityShaftRenderer extends TileEntitySpecialRenderer {
    private ModelShaft modelShaft = new ModelShaft();
    
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        modelShaft.render((TileEntityShaft) tileEntity, x, y, z);
    }
    
    public int getRenderId() {
        return ClientProxyGear.shaftModelId;
    }
}
