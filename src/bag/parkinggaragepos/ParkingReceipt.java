package bag.parkinggaragepos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The ParkingReceipt class is designed to track car ID's, 
 * number of hours a car was parked, and the fee that was 
 * collected from each car. It is useful when figuring out 
 * the total of a specific car, or to store receipts in an 
 * array and figure out the totals of all cars.
 * @author Ben
 */
public class ParkingReceipt {
    private final String INVALID_INPUT = "Invalid input entered into ParkingReceipt object";
    private Calendar calendar = Calendar.getInstance();
    private Date dateTime = calendar.getTime();
    private SimpleDateFormat sdf = new SimpleDateFormat("EE MMMM d, yyyy");
    private int carID;
    private double hoursParked = 0;
    private double feePaid = 0;
    
    /**
     * This constructor instatiates a ParkinReceipt object with a car ID, the number of hours parked
     * and the amount of fee that was paid.
     * @param carID
     * @param hoursParked
     * @param feePaid 
     */
    public ParkingReceipt(int carID, double hoursParked, double feePaid){
        //Validate input
        if(carID < 0 || hoursParked < 0 || feePaid < 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        this.hoursParked = hoursParked;
        this.carID = carID;
        this.feePaid = feePaid;
        
    }
    
    /**
     * 
     * This method will create a receipt based on a ticket object that is passed in.
     * It will get the car ID, hours parked, and fee paid all from the ticket Object.
     * @throws UnsupportedOperationException - This operation uses dates to calculate time parked and is not supported yet
     * @param ticket 
     */
    public ParkingReceipt(ParkingTicket ticket){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**
     * This method is not fully functioning yet.
     * @throws UnsupportedOperationException - This operation uses dates to calculate time parked and is not supported yet
     * @param garage 
     */
    public void outputReceipt(Garage garage){
        throw new UnsupportedOperationException("Not supported yet.");
        /*
        if(garage == null){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        System.out.println(garage.getName() + "\t\t" + sdf.format(dateTime)
                + '\n' + garage.getStreet1()
                + '\n' + garage.getCity() + ", " + garage.getState() + ' ' + garage.getState());
        */
        
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public double getHoursParked() {
        return hoursParked;
    }
    
    public void setCarID(int carID){
        if(carID > 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        this.carID = carID;
    }
    
    public int getCarID(){
        return carID;
    }
    
    public double getFeePaid(){
        return feePaid;
    }
    
    // </editor-fold>
    
    
    
}
