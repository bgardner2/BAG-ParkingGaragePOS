package bag.parkinggaragepos;

/**
 * The GarageFactory class is a factory that produces a returns a garage object. It creates and returns a garage
 * based on a public enumeration list that gives users the options of what garage/company to create an instance of.
 * @author Ben
 */

public class GarageFactory {
    private final String INVALID_INPUT = "Invalid input entered into the GarageFactory object";
    
    public static enum Garages {

        BEST_VALUE, THRIFTY
    }
    
    public static GarageFactory instance;

    private GarageFactory() {
    }
    
    public static GarageFactory getInstance(){
        if(instance == null){
            instance = new GarageFactory();
        }
        
        return instance;
    }
    
    public Garage createGarage(Garages garage){
        if(garage == null){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        Garage g = null;
        switch(garage){
            case BEST_VALUE:
                    g = new Garage("Best Value Garage", "1234 Main st"
                            , "Miami", "FL", "85145",new AutomatedParkingMachine(new BestValueFeeCalculator()));
                break;
            case THRIFTY:
                g = new Garage("Thrifty Garage", "526 Water st"
                            , "Milwaukee", "WI", "53955",new AutomatedParkingMachine(new ThriftyFeeCalculator()));
                break;
                
        }
        return g;
    }
}
