package bag.parkinggaragepos;

import java.io.*;
import java.util.*;

import java.util.Properties;

public abstract class AbstractGarageFactory {

    private final static String invalidConfigurationError = "You have an error in your configuration file."
            + " You may be missing a property. To continue please fix it.";
    private final static String invalidConfigurationFileError = "There is a problem with your configuration file."
            + " Verify it exists";
    private final static String configFilePath = System.getProperty("user.home")
            + File.separatorChar + "BAG-ParkingGaragePOS" + File.separatorChar;

    /**
     *This method pulls properties from a config.properties file 
     * to create an instance of a Garage Object.
     * @return @throws IOException - when trying to read the config.properties file
     */
    public static Garage getGarageInstance() throws IOException {
        Garage g = null;
        AutomatedParkingMachine apm = null;
        String garageName = null;
        String garageStreet = null;
        String garageCity = null;
        String garageState = null;
        String garageZip = null;
        String calculatorName = null;
        
        

        File file = new File(configFilePath, "config.properties");
        

        Properties props = new Properties();
        FileInputStream inFile;

        try {
            inFile = new FileInputStream(file);
            props.load(inFile);
            inFile.close();

            calculatorName = props.getProperty("feeCalculator");
            garageName = props.getProperty("garageName");
            garageStreet = props.getProperty("garageStreet");
            garageCity = props.getProperty("garageCity");
            garageState = props.getProperty("garageState");
            garageZip = props.getProperty("garageZip");

            Class calculatorStrategyClass = Class.forName(calculatorName);
            ParkingFeeCalculatorStrategy parkingFeeCalculator = (ParkingFeeCalculatorStrategy) calculatorStrategyClass.newInstance();
            apm = new AutomatedParkingMachine(parkingFeeCalculator);

            g = new Garage(garageName, garageStreet, garageCity, garageState, garageZip, apm);
        } catch (InvalidConfigException npe) {

            throw new InvalidConfigException(invalidConfigurationError);
        } catch (Exception ex) {
            throw new IOException(invalidConfigurationFileError);
        }

        return g;

    }


//    /*
//     * Test
//     */
//    public static void main(String[] args) throws IOException {
//        Garage g = AbstractGarageFactory.getGarageInstance();
//    }
}
