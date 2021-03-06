package logico.geartech;

import net.minecraftforge.common.ForgeDirection;

public enum Orientation {

	X_AXIS(ForgeDirection.WEST),
	Y_AXIS(ForgeDirection.UP),
	Z_AXIS(ForgeDirection.NORTH),
	UNKNOWN(ForgeDirection.UNKNOWN);

	public ForgeDirection defaultSide;

	public static Orientation fromDirection (final ForgeDirection direction) {

		if (direction.offsetX != 0) return Orientation.X_AXIS;

		else if (direction.offsetY != 0) return Orientation.Y_AXIS;

		else if (direction.offsetZ != 0) return Orientation.Z_AXIS;

		else return Orientation.UNKNOWN;

	}

	private Orientation (final ForgeDirection defaultSide) {

		this.defaultSide = defaultSide;

	}

}