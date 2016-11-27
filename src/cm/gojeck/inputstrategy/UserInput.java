package cm.gojeck.inputstrategy;

import java.util.Scanner;

import com.gojeck.service.ParkingService;

import cm.gojeck.ServiceDAO.ParkingServiceDAO;
import cm.gojeck.ServiceDAOImpl.ParkingServiceDAOImpl;
import cm.gojeck.serviceImpl.ParkingServiceImpl;


public class UserInput implements InputStrategy {

	private ParkingService parkingLot;
	private ParkingServiceDAO parkingServiceDAO;
	@Override
	public void readInput(String inputDetails) throws Exception {
		// TODO Auto-generated method stub
		String output = null;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Input:");
			String command = sc.nextLine();
			output = reponse(command);
			System.out.println("\nOutput : ");
			if(output.equals("Not found")){
				System.out.println("Not found");
				break;
			}
			if(null == output || output.equals("") || output.equals("No Command Found")){
				System.out.println("No Command Found !");
				break;
			}
			System.out.println(output);
			System.out.println("\n");
		}

	}

	private String reponse(String command) throws Exception {
		String[] commondDetails = command.trim().split(" ");
		if (commondDetails[0].trim().equals("create_parking_lot")) {
			parkingServiceDAO = new ParkingServiceDAOImpl();
			parkingLot = new ParkingServiceImpl(parkingServiceDAO);
			return parkingLot.createParkingLot(command.trim());
		}
		if (commondDetails[0].trim().toLowerCase().equals("leave")) {
			return parkingLot.makeFreeSlot(command.trim());
		}
		if (commondDetails[0].trim().toLowerCase().equals("status")) {
			return parkingLot.summary();
		}
		if (commondDetails[0].trim().toLowerCase().equals("park")) {
			return parkingLot.addEntry(command.trim());
		}
		if (commondDetails[0].trim().equals("registration_numbers_for_cars_with_colour")) {
			return parkingLot.vehicleListsWithColor(command.trim());
		}
		if (commondDetails[0].trim().equals("slot_number_for_registration_number")) {
			return parkingLot.slotNumberWithReg(command.trim());
		}
		if (commondDetails[0].trim().equals("slot_numbers_for_cars_with_colour")) {
			return parkingLot.vehicleListsWithSlot(command.trim());
		}
		return "No Command Found";
	}
}
