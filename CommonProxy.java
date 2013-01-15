/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gearteam.geartech;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

    public void registerRenderInformation() { //Client side texture registering
        
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { //For GUI's
        return null;
    }

    public void registerTiles() { //For registering TileEntities
    }

    public void registerBlocks() { //For registering Blocks
    }

    public void addNames() { //For adding Item's ingame names
    }

    public void addRecipes() { //For adding your Item's recipes
    }
}