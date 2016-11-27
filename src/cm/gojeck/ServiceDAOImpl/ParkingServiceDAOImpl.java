package cm.gojeck.ServiceDAOImpl;

import com.gojeck.utility.CustomList;
import com.gojeck.utility.CustomListUtil;
import com.gojeck.utility.CustomMap;
import com.gojeck.utility.HeapImpl;

import cm.gojeck.ServiceDAO.ParkingServiceDAO;
import cm.gojeck.entity.*;


public class ParkingServiceDAOImpl implements ParkingServiceDAO {

	private CustomMap<String, Slot> parkEntry;
	private CustomMap<String, CustomList<Slot>> colorVehicleEntry;
	private CustomMap<Integer, String> slotEntry;
	private CustomListUtil<Slot> listUtil;
	private HeapImpl freeSlot;
	private boolean isSlotFree;
	private int size;
	private int slotNo;

	@Override
	public String createParkingLot(String slotDetails) throws Exception {
		// TODO Auto-generated method stub
		String[] pd = slotDetails.split(" ");
		if ((pd.length <= 1 || !isValidSlotNumber(pd[pd.length - 1]))) {
			return "Wrong Input Format, Try Again";
		}
		size = Integer.parseInt(pd[pd.length - 1]);
		parkEntry = new CustomMap<>(size);
		colorVehicleEntry = new CustomMap<>(size);
		slotEntry = new CustomMap<>(size);
		listUtil = new CustomListUtil<>();
		freeSlot = new HeapImpl(size);
		initFreeSlot();
		slotNo = 0;
		return "Created a parking lot with slots "+size;
	}

	@Override
	public String addEntry(String entryDetails) throws Exception {
		// TODO Auto-generated method stub
		String[] vehicleDetails = entryDetails.split(" ");
		if (!isSlotFree()) {
			return "Sorry, Parking is full";
		}
		if(parkEntry.contains(vehicleDetails[1])){
			return "The given details are allready parked";
		}

		int slotN = freeSlot.remove();
		//System.out.println(slotN);
		Vehicle v = new Car(vehicleDetails[1], color(vehicleDetails[2]));
		Slot slot = new Slot(slotN, v, true, false);
		parkEntry.put(vehicleDetails[1], slot);
		addColoredVehicle(vehicleDetails[2], slot);
		slotEntry.put(slotN, vehicleDetails[1]);
		++slotNo;
		return "Allocated slot number: " + slotN;
	}

	@Override
	public String makeFreeSlot(String freeSlotDetail) throws Exception {
		// TODO Auto-generated method stub
		String[] freeSlotDetails = freeSlotDetail.split(" ");
		if ((freeSlotDetails.length <= 1 || !isValidSlotNumber(freeSlotDetails[freeSlotDetails.length - 1]))) {
			return "Wrong Input Format, Try Again";
		}
		int occupiedSlotNumber = Integer.parseInt(freeSlotDetails[freeSlotDetails.length - 1]);
		String plateNumber = null;
		if (!slotEntry.contains(occupiedSlotNumber) || occupiedSlotNumber > parkEntry.size()
				|| occupiedSlotNumber <= 0) {
			return "Given Slot No " + occupiedSlotNumber + " is Not exist";
		}
		plateNumber = slotEntry.remove(occupiedSlotNumber);
		Slot slot = parkEntry.remove(plateNumber);
		Car c = (Car) slot.getVehicle();
		String colorName = c.getColor().getColorName();
		int slotN = slot.getSlotNo();
		CustomList<Slot> slotList = colorVehicleEntry.remove(colorName);
		slotList = listUtil.deleteEntry(slot, slotList);
		freeSlot.insert(slotN);
		if (slotList != null) {
			colorVehicleEntry.put(colorName, slotList);
		}
		--slotNo;
		return "Slot number " + slotN + " is free";
	}

	@Override
	public String vehicleListsWithColor(String colorNameDetails) throws Exception {
		String[] regDetails = colorNameDetails.split(" ");
		if (colorNameDetails.length() <= 1) {
			return "Input format is incorrect, Try again..";
		}
		String colorName = regDetails[regDetails.length - 1];
		StringBuilder sb = new StringBuilder();
		CustomList<Slot> carList = colorVehicleEntry.get(colorName.toLowerCase());
		while (carList != null) {
			Slot s = carList.getValue();
			Car c = (Car) s.getVehicle();
			sb = sb.append(c.getPlateNo().trim()).append(",");
			carList = carList.getNext();
		}

		return sb.toString().substring(0, sb.toString().lastIndexOf(","));

	}

	@Override
	public String vehicleListsWithSlot(String slotNumber) throws Exception {
		String[] regDetails = slotNumber.split(" ");
		if (slotNumber.length() <= 1) {
			return "Input format is incorrect, Try again..";
		}
		String colorName = regDetails[regDetails.length - 1];
		StringBuilder sb = new StringBuilder();
		if(!colorVehicleEntry.contains(colorName.toLowerCase())){
			return "Not found";
		}
		CustomList<Slot> carList = colorVehicleEntry.get(colorName.toLowerCase());
		while (carList != null) {
			Slot s = carList.getValue();
			sb = sb.append(String.valueOf(s.getSlotNo())).append(",");
			carList = carList.getNext();
		}
		//System.out.println(sb.toString());
		return sb.toString().substring(0, sb.toString().lastIndexOf(","));
	}

	@Override
	public String slotNumberWithReg(String regDetail) throws Exception {
		String[] regDetails = regDetail.split(" ");
		if (regDetail.length() <= 1) {
			return "Input format is incorrect, Try again..";
		}
		String reg = regDetails[regDetails.length - 1];
		if (!parkEntry.contains(reg.trim())) {
			return "Not found";
		}
		Slot s = parkEntry.get(reg);
		return String.valueOf(s.getSlotNo());
	}

	@Override
	public String summary() throws Exception {
		// TODO Auto-generated method stub
		return parkEntry.display();
	}

	private void addColoredVehicle(String colorName, Slot slot) {
		CustomList<Slot> list = null;
		if (colorVehicleEntry.contains(colorName.toLowerCase())) {
			list = colorVehicleEntry.get(colorName.toLowerCase());
		}
		colorVehicleEntry.put(colorName.toLowerCase(), listUtil.addEntry(slot, list));
	}
	
	private Color color(String colorName) {

		for (Color colorList : Color.values()) {
			if (colorList.getColorName().equals(colorName.toLowerCase())) {
				return colorList;
			}
		}
		return null;
	}
	
	
	private void initFreeSlot() {
		for (int i = 1; i <= size; i++) {
			freeSlot.insert(i);
		}
	}

	private boolean isValidSlotNumber(String slotNo) {
		for (char ch : slotNo.toCharArray()) {
			if ((int) ch < 49 && (int) ch > 57) {
				return false;
			}
		}
		return true;
	}

	private boolean isSlotFree() {
		return slotNo < size;
	}

}
