package bag.parkinggaragepos;

import java.io.IOException;

/**
 * This driver class makes an example of what this project is supposed to do. It
 * is the main runnable class in this project.
 *
 * @author Ben
 * @Version 1
 */
public class Driver {
    /*
     * TODO:
     * Receipt output strategy (gui, console, etc), belonds in automatedparkingmachine class
     * Build receipt for each receipt upon checkout
     */

    public static void main(String[] args) throws Exception{
//        Garage garage = GarageFactory
//                .getInstance()
//                .createGarage(GarageFactory.Garages.THRIFTY);
       Garage garage = AbstractGarageFactory.getGarageInstance();
        System.out.println(ThriftyFeeCalculator.class);
        garage.checkCarIn();
        garage.checkCarIn();
        garage.checkCarIn();
        garage.checkCarIn();

        garage.checkCarOut(1, 5);
        garage.checkCarOut(5, 10); //invalid car id, only 4 cars have been checked in...will display error to user
        garage.checkCarOut(2, 10.5);
        garage.checkCarOut(4, 15.2);
        
        //Output the total hours and fees the garage has recorded
        garage.getTotalHoursAndFees();

    }
}
