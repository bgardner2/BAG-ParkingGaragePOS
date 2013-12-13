package bag.parkinggaragepos;

import java.util.Objects;

/**
 * ThriftFeeCalculator is a low-level module to calculate fees based on Thrifty garages required fee 
 * specifications.
 * @author Ben
 */
public class ThriftyFeeCalculator implements ParkingFeeCalculatorStrategy{
    private final String INVALID_INPUT ="You entered invalid input into the ThriftyFeeCalculator object";
    private String id = "ThriftFeeCalculator";
    private double minimumFee = 1.50;
    private double minimumHours = 2.00;
    private double hourlyFee = 0.75;
    
    /**
     * This method is a work in progress. In order to work
     * it needs to calculate hours based off a Date.
     * @param ticket
     * @return
     * @throws UnsupportedOperationException - because the calculation of time is not supported yet.
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

    @Override
    public String toString() {
        return "Thrifty Fee Calculator";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final ThriftyFeeCalculator other = (ThriftyFeeCalculator) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
}
