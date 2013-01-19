package logico.geartech;

import cpw.mods.fml.common.network.IGuiHandler;

import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

	public void registerRenderInformation () {}

	@Override public Object getServerGuiElement (final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {

		return null;

	}

	@Override  public Object getClientGuiElement (final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {

		return null;

	}

	public void registerTiles () {}

	public void registerBlocks () {}

	public void addNames () {}

	public void addRecipes () {}

}