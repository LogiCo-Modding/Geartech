package logico.geartech.gear.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logico.geartech.gear.client.ClientProxyGear;
import logico.geartech.gear.tileentities.TileEntityShaft;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockShaft extends BlockContainer {

	public BlockShaft (final int id) {

		super(id, Material.iron);
		setBlockName("gearShaft");
		setCreativeTab(CreativeTabs.tabMisc);
		//setBlockBounds(0F, 0.4375F, 0.4375F, 1F, 0.5625F, 0.5625F);

	}

	/*public TileEntityShaft createNewTileEntity (final World world, final int x, final int y, final int z) {

		final TileEntityShaft newShaft = new TileEntityShaft();
		newShaft.setWorldObj(world);
		newShaft.setPosition(x, y, z);
		return newShaft;

	}*/

	@Override public TileEntityShaft createNewTileEntity(final World world) {

		return new TileEntityShaft();

	}

	@Override public boolean onBlockActivated (final World world, final int x, final int y, final int z, final EntityPlayer player, final int side, final float hitX, final float hitY, final float hitZ) {

		if (ForgeDirection.getOrientation(side) == ForgeDirection.NORTH) {

			((TileEntityShaft) world.getBlockTileEntity(x, y, z)).changeRotationSpeedBy(2);
			return true;

		} else if (ForgeDirection.getOrientation(side) == ForgeDirection.SOUTH) {

			((TileEntityShaft) world.getBlockTileEntity(x, y, z)).changeRotationSpeedBy(-2);
			return true;

		}

		return false;

	}

	/*@Override public int onBlockPlaced (final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ, final int metadata) {

                tileX = x;
                tileY = y;
                tileZ = z;
                tileSide = ForgeDirection.getOrientation(side);
                return metadata;

	}*/

	@Override public void onBlockAdded (final World world, final int x, final int y, final int z) {

		world.setBlockTileEntity(x, y, z, createNewTileEntity(world));
		((TileEntityShaft) world.getBlockTileEntity(x, y, z)).updateConnections();

	}

	@Override public void breakBlock (final World par1World, final int par2, final int par3, final int par4, final int par5, final int par6) {

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
		par1World.removeBlockTileEntity(par2, par3, par4);

	}

	@Override public void onNeighborBlockChange (final World world, final int x, final int y, final int z, final int blockID) {
		((TileEntityShaft) world.getBlockTileEntity(x, y, z)).updateConnections();
	}

        public boolean isConnectedOnSide(World world, int x, int y, int z, ForgeDirection side) {
            return ((TileEntityShaft) world.getBlockTileEntity(x, y, z)).isConnectedOnSide(side);
        }
        
        public Set<ForgeDirection> getConnections(World world, int x, int y, int z) {
            return ((TileEntityShaft) world.getBlockTileEntity(x, y, z)).getConnections();
        }
        
        @Override public void addCollidingBlockToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List arrayList, Entity entity) {
            
            Set<ForgeDirection> connections = getConnections(world, x, y, z);
            
            this.minX = connections.contains(ForgeDirection.WEST)  ? 0 : 0.5-0.0625;
            this.minY = connections.contains(ForgeDirection.DOWN)  ? 0 : 0.5-0.0625;
            this.minZ = connections.contains(ForgeDirection.NORTH) ? 0 : 0.5-0.0625;
            
            this.maxX = connections.contains(ForgeDirection.EAST)  ? 1 : 0.5+0.0625;
            this.maxY = connections.contains(ForgeDirection.UP)  ? 1 : 0.5+0.0625;
            this.maxZ = connections.contains(ForgeDirection.SOUTH) ? 1 : 0.5+0.0625;
            
            super.addCollidingBlockToList(world, x, y, z, axisAlignedBB, arrayList, entity);
        }
        
	@Override public boolean isOpaqueCube () {

		return false;

	}

	@Override public boolean renderAsNormalBlock () {

		return false;

	}

	@Override public boolean hasTileEntity (final int metadata) {

		return true;

	}

	@Override public int getRenderType () {

		return ClientProxyGear.shaftModelId;

	}

	@Override public int getBlockTextureFromSide (final int side) {

		return 1;

	}

}