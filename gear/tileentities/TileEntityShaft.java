package gearteam.geartech.gear.tileentities;

import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.tileentity.TileEntity;

public class TileEntityShaft extends TileEntity {

	private int rotation;
	private float rotationSpeed;

	public TileEntityShaft() {

		rotation = 0;
		rotationSpeed = 0;

	}

	public int getRotation() {

		return rotation;

	}

	public float getRotationSpeed() {

		return rotationSpeed;

	}

	public void setRotationSpeed (final float speed) {

		rotationSpeed = speed;

	}
        
        public void changeRotationSpeedBy (final float deltaSpeed) {
            
                rotationSpeed += deltaSpeed;
            
        }
        
        @Override public void updateEntity() {
            
                this.rotation += rotationSpeed % 360;
                
        }
        
	@Override public void readFromNBT (final NBTTagCompound tagCompound) {

		super.readFromNBT(tagCompound);

		rotation = tagCompound.getInteger("Direction");
		rotationSpeed = tagCompound.getFloat("RotationSpeed");

	}

	@Override public void writeToNBT (final NBTTagCompound tagCompound) {

		super.writeToNBT(tagCompound);

		tagCompound.setInteger("Direction", rotation);
		tagCompound.setFloat("RotationSpeed", rotationSpeed);

	}

}