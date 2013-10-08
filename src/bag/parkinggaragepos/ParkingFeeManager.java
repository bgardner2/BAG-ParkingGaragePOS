package bag.parkinggaragepos;

/**
 * The sole purposed of the ParkingFeeManager class is to delegate responsibilities to low-level module
 * ParkingFeeCalculator objects.
 * @author Ben
 */
public class ParkingFeeManager {
    private final String INVALID_INPUT = "You have entered invalid input in the ParkingFeeManager object";
    private ParkingFeeCalculatorStrategy feeCalculator;
    
    /**
     * This constructor takes in a ParkingFeeCalculatorStrategy subclass
     * @param feeCalculator 
     */
    public ParkingFeeManager(ParkingFeeCalculatorStrategy feeCalculator){
        if(feeCalculator == null){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        this.feeCalculator = feeCalculator;
    }
    
    /**
     * This calculateFee method takes in a ParkingTicket object to
     * attempt to calculate a fee based on a date/time difference of when
     * a car's ticket was issued, and when the checkout time was.
     * @param ticket
     * @return 
     */
    public double calculateFee(ParkingTicket ticket){
        if(ticket == null){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        return feeCalculator.calculateFee(ticket);
    }
    
    
    /**
     * This calculateFee method takes in a simple hours parameter 
     * to calculate a fee based on an explicit number of hours parked.
     * @param hours
     * @return 
     */
    public double calculateFee(double hours){
        if(hours < 0 ){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        return feeCalculator.calculateFee(hours);
    }
    
    /**
     * This method allows you to change the ParkingFeeCalculatorStrategy between companies
     * or other versions of a companies fee calculations.
     * @param feeCalculator 
     */
    public void setFeeCalculator(ParkingFeeCalculatorStrategy feeCalculator){
        if(feeCalculator == null){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        this.feeCalculator = feeCalculator;
    }
}
