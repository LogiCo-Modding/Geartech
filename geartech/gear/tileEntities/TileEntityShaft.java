package gearteam.geartech.gear.tileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * The shaft that gears are placed on
 */
public class TileEntityShaft extends TileEntity {
    private int direction;
    private int rotationSpeed;
    
    public TileEntityShaft() {
        direction = 0;
        rotationSpeed = 0;
    }
    
    public void setRotationSpeed(int speed) {
        rotationSpeed = speed;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        direction = tagCompound.getInteger("Direction");
        rotationSpeed = tagCompound.getInteger("RotationSpeed");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("Direction", direction);
        tagCompound.setInteger("RotationSpeed", rotationSpeed);
    }
}
