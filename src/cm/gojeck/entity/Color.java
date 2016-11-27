package cm.gojeck.entity;

public enum Color {
	RED("red"),WHITE("white"),GREEN("green"),BLACK("black"),BLUE("blue");
	private String colorName;
	private Color(String colorName){
		this.colorName = colorName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	
	
}
