package bag.parkinggaragepos;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;


/**
 * The Garage class is an object that stores information about a specific garage. All garages have a name, 
 * full address, and an AutomatedParkingMachine object. This object is the first object in a chain of delegation
 * that checks cars in and out of the garage. The only class that it delegates responsibilities to is the
 * AutomateeParkingMachine class.
 * @author Ben
 */
public class Garage {
    private final String INVALID_INPUT = "Invalid input entered";
    
    private String name;
    private String street1;
    private String city;
    private String state;
    private String zip;
    private AutomatedParkingMachine apm;
    
    public Garage(){
    }

    public Garage(String name, String street1, String city, String state, String zip, AutomatedParkingMachine apm) {
        if(name.length() == 0 || street1.length() == 0
                ||city.length() == 0 || state.length() == 0
                ||zip.length() == 0 || apm == null){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.name = name;
        this.street1 = street1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.apm = apm;
    }
    
    public void checkCarIn() throws IOException{
        apm.checkCarIn();
        
    }
    
    public int getTicketNumber() throws IOException {
        return apm.getTicketNumber();
    }
    
    
    public void checkAllCarsOut(){
        apm.checkAllCarsOut();
    }
    
    public String checkCarOut(int carID, double hours) {
        if(carID < 0 || hours < 0){
            throw new IllegalArgumentException(INVALID_INPUT);
            
        }
        return apm.checkCarOut(carID, hours);
        
    }
    
    public String readDataContentsAndOutput() throws IOException {
        return apm.readDataContentsAndOutput();
    }

    public String getTotalHoursAndFees(){
        return apm.getTotalHoursAndFees();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.street1);
        hash = 53 * hash + Objects.hashCode(this.city);
        hash = 53 * hash + Objects.hashCode(this.state);
        hash = 53 * hash + Objects.hashCode(this.zip);
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
        final Garage other = (Garage) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.street1, other.street1)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Garage{name=" + name + ", street1=" + street1 + ", city=" + city + ", state=" + state + ", zip=" + zip + '}';
    }
    

    
    
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() == 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        this.name = name;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        if(street1.length() == 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        this.street1 = street1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city.length() == 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(state.length() == 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if(zip.length() == 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.zip = zip;
    }
    
    // </editor-fold>

    
    
    
    
    
}
