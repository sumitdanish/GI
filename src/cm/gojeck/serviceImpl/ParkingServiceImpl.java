package cm.gojeck.serviceImpl;

import com.gojeck.service.ParkingService;

import cm.gojeck.ServiceDAO.ParkingServiceDAO;

public class ParkingServiceImpl implements ParkingService{

	private ParkingServiceDAO parkingServiceDAO;
	
	public ParkingServiceImpl(ParkingServiceDAO parkingServiceDAO) {
		// TODO Auto-generated constructor stub
		this.parkingServiceDAO = parkingServiceDAO;
	}
	
	@Override
	public String addEntry(String entryDetails) throws Exception {
		// TODO Auto-generated method stub
		return parkingServiceDAO.addEntry(entryDetails);
	}

	@Override
	public String createParkingLot(String slotDetails) throws Exception {
		// TODO Auto-generated method stub
		return parkingServiceDAO.createParkingLot(slotDetails);
	}

	@Override
	public String makeFreeSlot(String freeSlotDetail) throws Exception {
		// TODO Auto-generated method stub
		return parkingServiceDAO.makeFreeSlot(freeSlotDetail);
	}

	@Override
	public String vehicleListsWithColor(String colorName) throws Exception {
		// TODO Auto-generated method stub
		return parkingServiceDAO.vehicleListsWithColor(colorName);
	}

	@Override
	public String vehicleListsWithSlot(String slotNumber) throws Exception {
		// TODO Auto-generated method stub
		return parkingServiceDAO.vehicleListsWithSlot(slotNumber);
	}

	@Override
	public String slotNumberWithReg(String regDetails) throws Exception {
		// TODO Auto-generated method stub
		return parkingServiceDAO.slotNumberWithReg(regDetails);
	}

	@Override
	public String summary() throws Exception {
		// TODO Auto-generated method stub
		return parkingServiceDAO.summary();
	}
	
}
