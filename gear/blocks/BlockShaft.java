/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gearteam.geartech.gear.blocks;

import gearteam.geartech.gear.client.ClientProxyGear;
import gearteam.geartech.gear.tileentities.TileEntityShaft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockShaft extends Block {
    public BlockShaft(int id) {
        super(id, Material.iron);
        setBlockName("gearShaft");
        //setBlockBounds(1F, 0.25F, 0.25F, -1F, -0.25F, -0.25F);
        setCreativeTab(CreativeTabs.tabMisc);
        setBlockBounds(0F, 0.4375F, 0.4375F, 1F, 0.5625F, 0.5625F);
    }
    
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityShaft();
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        world.setBlockTileEntity(x, y, z, this.createNewTileEntity(world));
    }
    
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        par1World.removeBlockTileEntity(par2, par3, par4);
    }
    
    @Override
    public boolean isOpaqueCube() {
         return false;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
         return false;
    }
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
    @Override
    public int getRenderType() {
         return ClientProxyGear.shaftModelId;
    }
    
    @Override
    public int getBlockTextureFromSide(int side) {
         return 1;
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return createNewTileEntity(world);
    }
}
