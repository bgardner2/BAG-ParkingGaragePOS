package bag.parkinggaragepos;

/**
 * BestValueFeeCalculator is a low-level module to calculate fees based on Best Value garages required fee 
 * specifications.
 * @author Ben
 */
public class BestValueFeeCalculator implements ParkingFeeCalculatorStrategy{
    private final String INVALID_INPUT ="You entered invalid input into the BestValueFeeCalculator object";
    private double minimumFee = 2.00;
    private double maximumFee = 10.00;
    private double minimumHours = 3.00;
    private double hourlyFee = 0.50;
    
    
    /**
     * This method is a work in progress. In order to work
     * it needs to calculate hours based off a Date.
     * @param ticket
     * @return 
     */
    @Override
    public double calculateFee(ParkingTicket ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*double fee = Math.ceil(ticket.getHours() - minimumHours) * hourlyFee + minimumFee;
                
        if(ticket.getHours() <= minimumHours){
            return minimumFee;
        }
        
        if(fee >= maximumFee){
            return maximumFee;
        }
        
        return fee;
        */
    }
    

    /**
     * This method returns a parking fee based on an explicit number of hours
     * @param hours
     * @return 
     */
    @Override
    public double calculateFee(double hours) {
        if(hours < 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        double fee = Math.ceil(hours - minimumHours) * hourlyFee + minimumFee;
                
        if(hours <= minimumHours){
            return minimumFee;
        }
        
        if(fee >= maximumFee){
            return maximumFee;
        }
        
        return fee;
    }
    
    
    
}
