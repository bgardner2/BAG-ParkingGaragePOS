package bag.parkinggaragepos;

/**
 * Interface strategy for ParkingFeeManagers and ParkingFeeCalculator subclasses
 * @author Ben
 */
public interface ParkingFeeCalculatorStrategy {
    public abstract double calculateFee(ParkingTicket ticket);
    public abstract double calculateFee(double hours);
    
}
