package bag.parkinggaragepos;

public class CarNotFoundException extends IllegalArgumentException{
    
   public CarNotFoundException() {
    }

    public CarNotFoundException(String s) {
        super(s);
    }
}
