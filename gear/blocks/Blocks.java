package gearteam.geartech.gear.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;

public class Blocks {

	public static Block shaft;

	public static void load() {

		shaft = new BlockShaft(230);

		GameRegistry.registerBlock(shaft, "Gear Shaft");
		LanguageRegistry.addName(shaft, "Gear Shaft");

	}

}