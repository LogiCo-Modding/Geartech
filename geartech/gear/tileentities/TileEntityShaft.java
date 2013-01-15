package gearteam.geartech.gear.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * The shaft that gears are placed on
 */
public class TileEntityShaft extends TileEntity {
    private int rotation;
    private int rotationSpeed;
    
    public TileEntityShaft() {
        rotation = 0;
        rotationSpeed = 0;
    }
    
    public int getRotation(int speed) {
        return rotation;
    }
    
    public int getRotationSpeed() {
        return rotationSpeed;
    }
    
    public void setRotationSpeed(int speed) {
        rotationSpeed = speed;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        rotation = tagCompound.getInteger("Direction");
        rotationSpeed = tagCompound.getInteger("RotationSpeed");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("Direction", rotation);
        tagCompound.setInteger("RotationSpeed", rotationSpeed);
    }
}