package bag.parkinggaragepos;

/**
 * This custom exception can be used if a property is not set in the config.properties file.
 * @author Ben
 */
public class InvalidConfigException extends NullPointerException {

    public InvalidConfigException() {
    }

    public InvalidConfigException(String s) {
        super(s);
    }
    
}
