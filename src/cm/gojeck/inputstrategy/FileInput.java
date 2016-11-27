package cm.gojeck.inputstrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.gojeck.service.ParkingService;

import cm.gojeck.ServiceDAO.ParkingServiceDAO;
import cm.gojeck.ServiceDAOImpl.ParkingServiceDAOImpl;
import cm.gojeck.serviceImpl.ParkingServiceImpl;

public class FileInput implements InputStrategy{

	private ParkingService parkingLot;
	private ParkingServiceDAO parkingServiceDAO;
	
	@Override
	public void readInput(String inputDetail) throws Exception {
		// TODO Auto-generated method stub
		String[] inputDetails = inputDetail.trim().split(" ");
		FileReader frIn = new FileReader(new File(inputDetails[0]));
		//FileWriter fout = new FileWriter(new File(inputDetails[inputDetails.length - 1].trim()));
		BufferedReader br = new BufferedReader(frIn);
		String line = null;
		StringBuilder sb = new StringBuilder();
		
		while((line = br.readLine()) != null){
			String[] commandList = line.trim().split(" ");
			String message = null;
			if(commandList[0].trim().equals("create_parking_lot")){
				parkingServiceDAO = new ParkingServiceDAOImpl();
				parkingLot = new ParkingServiceImpl(parkingServiceDAO);
				message = parkingLot.createParkingLot(line);
			}else if(commandList[0].trim().equals("park")){
				message = parkingLot.addEntry(line);
			}else if(commandList[0].trim().equals("leave")){
				message = parkingLot.makeFreeSlot(line);
			}else if(commandList[0].trim().toLowerCase().equals("status")){
				message = parkingLot.summary();
			}else if(commandList[0].trim().equals("registration_numbers_for_cars_with_colour")){
				message = parkingLot.vehicleListsWithColor(line);
			}else if(commandList[0].trim().equals("slot_number_for_registration_number")){
				message = parkingLot.slotNumberWithReg(line);
			}else if(commandList[0].trim().equals("slot_numbers_for_cars_with_colour")){
				message = parkingLot.vehicleListsWithSlot(line);
			}
			sb = sb.append(message).append("\n");
		}
		frIn.close();
		br.close();
		System.out.println(sb.toString());
		//writeInotFile(sb.toString(),fout);
	}

	private void writeInotFile(String input,FileWriter fout) throws IOException{
		fout.write(input);
		fout.flush();
		fout.close();
	}
	
	
}
