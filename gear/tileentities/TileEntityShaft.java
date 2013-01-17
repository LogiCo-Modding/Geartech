package gearteam.geartech.gear.tileentities;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityShaft extends TileEntity {

	private int rotation;
	private float rotationSpeed;
        
        private Set<ForgeDirection> connectedSides = new HashSet<ForgeDirection>();
        
        public enum Orientation {
            X_AXIS(ForgeDirection.WEST),
            Y_AXIS(ForgeDirection.UP),
            Z_AXIS(ForgeDirection.NORTH),
            UNKNOWN(ForgeDirection.UNKNOWN);
            
            public ForgeDirection defaultSide;
            
            public static Orientation fromDirection(final ForgeDirection direction) {
                if (direction.offsetX != 0) {
                    return Orientation.X_AXIS;
                }
                if (direction.offsetY != 0) {
                    return Orientation.Y_AXIS;
                }
                if (direction.offsetZ != 0) {
                    return Orientation.Z_AXIS;
                }
                return Orientation.UNKNOWN;
            }
            
            Orientation(final ForgeDirection defaultSide) {
                this.defaultSide = defaultSide;
            }
        }
        
        private Orientation orientation;

	/**
        * @param side The side that the shaft was placed on
        */
        public TileEntityShaft(final World world, final int x, final int y, final int z, final ForgeDirection side) {
            
                this.setWorldObj(world);
		this.xCoord = x;
                this.yCoord = y;
                this.zCoord = z;
                
                rotation = 0;
		rotationSpeed = 0;
                
                updateConnections(side.getOpposite());

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
        
        public Orientation getOrientation() {
            return orientation;
        }
        
        public boolean isConnectedOnSide(final ForgeDirection side) {
                return connectedSides.contains(side);
        }
        
        protected void setConnectedOnSide(final ForgeDirection side) {
            if (side.ordinal() >= 0 && side.ordinal() <= 6) {
                connectedSides.add(side);
                orientation = Orientation.fromDirection(side);
            }
        }
        
        private void dropConnectionOnSide(final ForgeDirection side) {
            connectedSides.remove(side);
        }
        
        public void updateConnections(final ForgeDirection side) {
            connectedSides.clear();
            orientation = Orientation.UNKNOWN;
            
            tryConnectingOnAxisPreferringSide(side);
            switch (Orientation.fromDirection(side)) {
                case X_AXIS:
                    tryConnectingOnAxis(Orientation.Y_AXIS);
                    tryConnectingOnAxis(Orientation.Z_AXIS);
                    break;
                case Y_AXIS:
                    tryConnectingOnAxis(Orientation.X_AXIS);
                    tryConnectingOnAxis(Orientation.Z_AXIS);
                    break;
                case Z_AXIS:
                    tryConnectingOnAxis(Orientation.X_AXIS);
                    tryConnectingOnAxis(Orientation.Y_AXIS);
                    break;
                case UNKNOWN:
                    tryConnectingOnAxis(Orientation.X_AXIS);
                    tryConnectingOnAxis(Orientation.Y_AXIS);
                    tryConnectingOnAxis(Orientation.Z_AXIS);
                    break;
            }
                
        }
        
        private boolean tryConnectingOnSide(final ForgeDirection side) {
            if (side.ordinal() < 0 || side.ordinal() > 6) {
                return false;
            }
            
            TileEntity tileEntity = this.worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
            
            if (!(tileEntity instanceof TileEntityShaft)) {
                return false;
            }
            
            TileEntityShaft connectingShaft = (TileEntityShaft) tileEntity;
            if (connectingShaft.orientation != Orientation.fromDirection(side) && connectingShaft.orientation != Orientation.UNKNOWN) {
                return false;
            }
            setConnectedOnSide(side);
            connectingShaft.setConnectedOnSide(side.getOpposite());
            return true;
        }
        
        public boolean tryConnectingOnAxis(Orientation orientation) {
            return tryConnectingOnAxisPreferringSide(orientation.defaultSide);
        }
        
        public boolean tryConnectingOnAxisPreferringSide(ForgeDirection side) {
                
                return tryConnectingOnSide(side) || tryConnectingOnSide(side.getOpposite());
                
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