package bag.parkinggaragepos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * The parking ticket class keeps track of all data pertaining to a specific car that is currently parked
 * in a garage. It holds information about the car's ID, the time that it was checked in, and if a 
 * specific car has already been checked out.
 * @author Ben
 */
public class ParkingTicket implements Serializable {
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
        ++ticketNumber;
        this.carID = ticketNumber;
        
    }  
    
    ParkingTicket(int ticketNumber){
        this.ticketNumber = ticketNumber;
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
    
    public static int getTicketNumber() {
        return ticketNumber;
    }

    public  void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    
     
    // </editor-fold>
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.carID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParkingTicket other = (ParkingTicket) obj;
        if (this.carID != other.carID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" + "carID=" + carID + ", calendar=" + calendar + ", date=" + date + ", carCheckedOut=" + carCheckedOut + '}';
    }
    
    
    
}
