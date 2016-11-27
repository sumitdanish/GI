package cm.gojeck.ServiceDAO;

public interface ParkingServiceDAO {
	public String addEntry(String entryDetails) throws Exception;
	public String createParkingLot(String slotDetails) throws Exception;
	public String makeFreeSlot(String freeSlotDetail) throws Exception;
	public String vehicleListsWithColor(String colorName) throws Exception;
	public String vehicleListsWithSlot(String slotNumber) throws Exception;
	public String slotNumberWithReg(String regDetails) throws Exception;
	public String summary() throws Exception;
}
