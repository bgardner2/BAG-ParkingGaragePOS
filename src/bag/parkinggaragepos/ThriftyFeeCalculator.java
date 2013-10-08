package bag.parkinggaragepos;

/**
 * ThriftFeeCalculator is a low-level module to calculate fees based on Thrifty garages required fee 
 * specifications.
 * @author Ben
 */
public class ThriftyFeeCalculator implements ParkingFeeCalculatorStrategy{
    private final String INVALID_INPUT ="You entered invalid input into the ThriftyFeeCalculator object";
    private double minimumFee = 1.50;
    private double minimumHours = 2.00;
    private double hourlyFee = 0.75;
    
    /**
     * This method is a work in progress. In order to work
     * it needs to calculate hours based off a Date.
     * @param ticket
     * @return 
     */
@Override
    public double calculateFee(ParkingTicket ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*if(ticket == null){
           throw new IllegalArgumentException(INVALID_INPUT);
         }
        
         double fee = Math.ceil(ticket.getHours() - minimumHours) * hourlyFee + minimumFee;
                
         if(ticket.getHours() <= minimumHours){
            return minimumFee;
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
        
        return fee;
    }

    
    
    
}
