package logico.geartech.gear.tileentities;

import java.util.HashSet;
import java.util.Set;

import logico.geartech.Orientation;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityShaft extends TileEntity {

	private int rotation = 0;
	private float rotationSpeed = 0;
        
        private Set<ForgeDirection> connectedSides = new HashSet<ForgeDirection>();
        
        private Orientation orientation = Orientation.UNKNOWN;
        
        public TileEntityShaft() {
            
        }
        
        public void setPosition(int x, int y, int z) {
            this.xCoord = x;
            this.yCoord = y;
            this.zCoord = z;
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
            if (side.ordinal() >= 0 && side.ordinal() <= 5) {
                connectedSides.add(side);
                orientation = Orientation.fromDirection(side);
            }
        }
        
        public void updateConnections(World world, int x, int y, int z, final ForgeDirection side) {
            connectedSides.clear();
            orientation = Orientation.UNKNOWN;
            
            if (tryConnectingOnAxisPreferringSide(side)) {
                return;
            }
            
            boolean success = false;
            
            switch (Orientation.fromDirection(side)) {
                case X_AXIS:
                    success = tryConnectingOnAxis(Orientation.Y_AXIS)
                           || tryConnectingOnAxis(Orientation.Z_AXIS);
                    break;
                case Y_AXIS:
                    success = tryConnectingOnAxis(Orientation.X_AXIS)
                           || tryConnectingOnAxis(Orientation.Z_AXIS);
                    break;
                case Z_AXIS:
                    success = tryConnectingOnAxis(Orientation.X_AXIS)
                           || tryConnectingOnAxis(Orientation.Y_AXIS);
                    break;
                case UNKNOWN:
                    success = tryConnectingOnAxis(Orientation.X_AXIS)
                           || tryConnectingOnAxis(Orientation.Y_AXIS)
                           || tryConnectingOnAxis(Orientation.Z_AXIS);
                    break;
            }
                
        }
        
        public void updateConnections(World world, int x, int y, int z, Orientation orientation) {
            updateConnections(world, x, y, z, orientation.defaultSide);
        }
        
        public void updateConnections(World world, int x, int y, int z) {
            updateConnections(world, x, y, z, orientation.defaultSide);
        }
        
        private boolean tryConnectingOnSide(final ForgeDirection side) {
            if (side.ordinal() < 0 || side.ordinal() > 5) {
                return false;
            }
            
            TileEntity tileEntity = worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
            
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
                
                // Each expression in a variable instead of or-ing them straight off
                // because both functions need to be evaluated
                boolean success1 = tryConnectingOnSide(side);
                boolean success2 = tryConnectingOnSide(side.getOpposite());
                return  success1 || success2;
                
        }
        
        @Override public void updateEntity() {
            
                this.rotation += rotationSpeed % 360;
                
        }
        
        @Override public Packet getDescriptionPacket() {
            NBTTagCompound tag = new NBTTagCompound();
            this.writeToNBT(tag);
            return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
        }
        
        @Override public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
            
            NBTTagCompound tag = packet.customParam1;
            
            orientation = Orientation.values()[tag.getInteger("Orientation")];
            rotation = tag.getInteger("Direction");
            rotationSpeed = tag.getFloat("RotationSpeed");
            
        }
        
	@Override public void readFromNBT (final NBTTagCompound tagCompound) {

		super.readFromNBT(tagCompound);

		rotation = tagCompound.getInteger("Direction");
		rotationSpeed = tagCompound.getFloat("RotationSpeed");
                orientation = Orientation.values()[tagCompound.getInteger("Orientation")];
                
	}

	@Override public void writeToNBT (final NBTTagCompound tagCompound) {

		super.writeToNBT(tagCompound);

		tagCompound.setInteger("Direction", rotation);
                tagCompound.setFloat("RotationSpeed", rotationSpeed);
                tagCompound.setInteger("Orientation", orientation.ordinal());

	}

}