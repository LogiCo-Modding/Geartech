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

	private final Set<ForgeDirection> connectedSides = new HashSet<ForgeDirection>();

	private Orientation orientation = Orientation.UNKNOWN;

	public void setPosition (final int x, final int y, final int z) {

		xCoord = x;
		yCoord = y;
		zCoord = z;

	}

	public int getRotation () {

		return rotation;

	}

	public float getRotationSpeed () {

		return rotationSpeed;

	}

	public void setRotationSpeed (final float speed) {

		rotationSpeed = speed;

	}

	public void changeRotationSpeedBy (final float deltaSpeed) {

		rotationSpeed += deltaSpeed;

	}

	public Orientation getOrientation () {

		return orientation;

	}

	public boolean isConnectedOnSide (final ForgeDirection side) {

		return connectedSides.contains(side);

	}

	protected void setConnectedOnSide (final ForgeDirection side) {

		if (side.ordinal() >= 0 && side.ordinal() <= 5) {

			connectedSides.add(side);
			orientation = Orientation.fromDirection(side);

		}

	}

	public void updateConnections (final World world, final int x, final int y, final int z, final ForgeDirection side) {

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

	public void updateConnections (final World world, final int x, final int y, final int z, final Orientation orientation) {

		updateConnections(world, x, y, z, orientation.defaultSide);

	}

	public void updateConnections (final World world, final int x, final int y, final int z) {

		updateConnections(world, x, y, z, orientation.defaultSide);

	}

	private boolean tryConnectingOnSide (final ForgeDirection side) {

		if (side.ordinal() < 0 || side.ordinal() > 5) {

			return false;

		}

		final TileEntity tileEntity = worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);

		if (!(tileEntity instanceof TileEntityShaft)) {

			return false;

		}

		final TileEntityShaft connectingShaft = (TileEntityShaft) tileEntity;

		if (connectingShaft.orientation != Orientation.fromDirection(side) && connectingShaft.orientation != Orientation.UNKNOWN) {

			return false;

		}

		setConnectedOnSide(side);
		connectingShaft.setConnectedOnSide(side.getOpposite());

		return true;

	}

	public boolean tryConnectingOnAxis(final Orientation orientation) {

		return tryConnectingOnAxisPreferringSide(orientation.defaultSide);

	}

	public boolean tryConnectingOnAxisPreferringSide (final ForgeDirection side) {

		return  tryConnectingOnSide(side) | tryConnectingOnSide(side.getOpposite());

	}

	@Override public void updateEntity() {

		rotation += rotationSpeed % 360;

	}

	@Override public Packet getDescriptionPacket () {

		final NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);

	}

	@Override public void onDataPacket (final INetworkManager net, final Packet132TileEntityData packet) {

		final NBTTagCompound tag = packet.customParam1;

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