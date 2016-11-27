package cm.gojeck.entity;

public class Slot {
	private int slotNo;
	private Vehicle vehicle;
	private boolean isBussy;
	private boolean isFree;

	public Slot(int slotNo, Vehicle vehicle, boolean isBussy, boolean isFree) {
		this.slotNo = slotNo;
		this.vehicle = vehicle;
		this.isBussy = isBussy;
		this.isFree = isFree;

	}

	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean isBussy() {
		return isBussy;
	}

	public void setBussy(boolean isBussy) {
		this.isBussy = isBussy;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isBussy ? 1231 : 1237);
		result = prime * result + (isFree ? 1231 : 1237);
		result = prime * result + slotNo;
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Slot other = (Slot) obj;
		if (isBussy != other.isBussy)
			return false;
		if (isFree != other.isFree)
			return false;
		if (slotNo != other.slotNo)
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

}
