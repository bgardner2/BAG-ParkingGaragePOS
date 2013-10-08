package bag.parkinggaragepos;

import java.util.Calendar;
import java.util.Date;

/**
 * The parking ticket class keeps track of all data pertaining to a specific car that is currently parked
 * in a garage. It holds information about the car's ID, the time that it was checked in, and if a 
 * specific car has already been checked out.
 * @author Ben
 */
public class ParkingTicket {
    private final String INVALID_INPUT = "You entered invalid input into the ParkingReceipt object";
    private static int ticketNumber = 0; 
    
    private int carID;
    private Calendar calendar = Calendar.getInstance();
    private Date date = calendar.getTime();
    private boolean carCheckedOut = false;
    
    /**
     * The default constructor increments the ticketNumber field and assigns it to the carID field.
     * It does so this to ensure guaranteed unique carIDs
     */
    public ParkingTicket(){
        ticketNumber++;
        this.carID = ticketNumber;
        
    }    
    
    /**
     * Returns the date/time when the ticket was instantiated
     * @return 
     */
    public Date returnCheckinTime(){
        return date;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public static int getReceiptNumber() {
        return ticketNumber;
    }
        
    public int getCarID() {
        return carID;
    }
    
    public boolean isCarCheckedOut() {
        return carCheckedOut;
    }

    public void setCarCheckedOut(boolean carCheckedOut) {
        if(carCheckedOut != true && carCheckedOut != false){ //Do we need to validate boolean parameters?? They seem pretty type-safe.
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.carCheckedOut = carCheckedOut;
    }
     
    // </editor-fold>

    

   

    
    
    
    
}
