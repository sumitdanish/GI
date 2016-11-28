package cm.gojeck.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.gojeck.service.ParkingService;

import cm.gojeck.ServiceDAO.ParkingServiceDAO;
import cm.gojeck.ServiceDAOImpl.ParkingServiceDAOImpl;
import cm.gojeck.serviceImpl.ParkingServiceImpl;
@RunWith(Parameterized.class)
public class ParkingLotMain {
	private static ParkingService parkingLot;
	private static ParkingServiceDAO parkingServiceDAO;
	private String carDetail;
	private String message;
	public ParkingLotMain(String carDetail,String message) {
		// TODO Auto-generated method stub
		this.carDetail = carDetail;
		this.message = message;

	}
	@Parameterized.Parameters
	   public static Collection primeNumbers() {
	      return Arrays.asList(new Object[][] {
	         { "park KA-­01-­HH-­1234 White",  "Allocated slot number: 1" },
	         { "park KA-­01-­HH-­9999 White",  "Allocated slot number: 2" },
	         { "park KA­-01-­BB-­0001 Black",  "Allocated slot number: 3" },
	         { "park KA-­01-­HH-­7777 Red", 	"Allocated slot number: 4" },
	         { "park KA­-01-­HH-­2701 Blue",   "Allocated slot number: 5" },
	         { "park KA­-01­-HH­-3141 Black",  "Allocated slot number: 6" }
	      });
	   }
	@BeforeClass
	public static void init() throws Exception{
		parkingServiceDAO = new ParkingServiceDAOImpl();
		parkingLot = new ParkingServiceImpl(parkingServiceDAO);
		assertEquals("Created a parking lot with slots 6", parkingLot.createParkingLot("create_parking_lot 6"));
	}
	@Test
	public void test() throws Exception {
		assertEquals(message, parkingLot.addEntry(carDetail));
	}
	
	@AfterClass
	public static void freeParking() throws Exception{
		
		assertEquals("Slot number 4 is free", parkingLot.makeFreeSlot("leave 4"));
		assertEquals("Allocated slot number: 4", parkingLot.addEntry("park KA­-01­-HH­-3148 White"));
		System.out.println(parkingLot.summary());
		assertEquals("KA-­01-­HH-­1234,KA-­01-­HH-­9999,KA­-01­-HH­-3148", parkingLot.vehicleListsWithColor("registration_numbers_for_cars_with_colour White"));
		assertEquals("1,2,4", parkingLot.vehicleListsWithSlot("slot_numbers_for_cars_with_colour White"));
		assertEquals("6", parkingLot.slotNumberWithReg("slot_number_for_registration_number KA­-01­-HH­-3141"));
		assertEquals("Not found",  parkingLot.slotNumberWithReg("slot_number_for_registration_number MH­-04-­AY-­1111"));
	}

}
