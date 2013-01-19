package logico.geartech.gear.blocks;

import logico.geartech.gear.client.ClientProxyGear;
import logico.geartech.gear.tileentities.TileEntityShaft;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.tileentity.TileEntity;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockShaft extends BlockContainer {

	public BlockShaft (final int id) {

		super(id, Material.iron);
		setBlockName("gearShaft");
		setCreativeTab(CreativeTabs.tabMisc);
		setBlockBounds(0F, 0.4375F, 0.4375F, 1F, 0.5625F, 0.5625F);

	}

	public TileEntityShaft createNewTileEntity (final World world, final int x, final int y, final int z) {

		final TileEntityShaft newShaft = new TileEntityShaft();
		newShaft.setWorldObj(world);
		newShaft.setPosition(x, y, z);
		return newShaft;

	}

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

		world.setBlockTileEntity(x, y, z, createNewTileEntity(world, x, y, z));
		((TileEntityShaft) world.getBlockTileEntity(x, y, z)).updateConnections(world, x, y, z);

	}

	@Override public void breakBlock (final World par1World, final int par2, final int par3, final int par4, final int par5, final int par6) {

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
		par1World.removeBlockTileEntity(par2, par3, par4);

	}

	@Override public void onNeighborBlockChange (final World world, final int x, final int y, final int z, final int blockID) {
		((TileEntityShaft) world.getBlockTileEntity(x, y, z)).updateConnections(world, x, y, z);
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

	@Override public TileEntity createTileEntity (final World world, final int metadata) {

		return createNewTileEntity(world);

	}

}