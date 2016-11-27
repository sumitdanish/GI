package cm.gojeck.entity;

public class Car extends Vehicle{
	private String plateNo;
	private Color color;
	
	public Car(String plateNo,Color color){
		this.plateNo = plateNo;
		this.color = color;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((plateNo == null) ? 0 : plateNo.hashCode());
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
		Car other = (Car) obj;
		if (color != other.color)
			return false;
		if (plateNo == null) {
			if (other.plateNo != null)
				return false;
		} else if (!plateNo.equals(other.plateNo))
			return false;
		return true;
	}
	
	
}
