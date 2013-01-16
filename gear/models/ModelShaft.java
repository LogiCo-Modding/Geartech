package gearteam.geartech.gear.models;

import gearteam.geartech.gear.tileentities.TileEntityShaft;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

public class ModelShaft extends ModelBase {

	private final ModelRenderer shaft;

	private static final int TEXTURE_HEIGHT = 4;
	private static final int TEXTURE_WIDTH = 36;

	public ModelShaft () {

		textureHeight = TEXTURE_HEIGHT;
		textureWidth = TEXTURE_WIDTH;

		shaft = new ModelRenderer(this, 0, 0);
		shaft.addBox(-8, -1, -1, 16, 2, 2);
		shaft.rotationPointX = 0;
		shaft.rotationPointY = 0;
		shaft.rotationPointZ = 0;

	}

	public void render (final TileEntityShaft tileEntityShaft, final double x, final double y, final double z, final int rotation) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslated(x+0.5, y+0.5, z+0.5);
                GL11.glRotatef(rotation, 1, 0, 0);
		ForgeHooksClient.bindTexture("/gearteam/geartech/gear/textures/shaft.png", 0);
		shaft.render((float) (1.0 / 16.0));
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

	}

	public void render () {

		shaft.render((float) (1.0 / 16.0));

	}

}