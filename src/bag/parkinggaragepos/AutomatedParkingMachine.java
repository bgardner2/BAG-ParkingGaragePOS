package bag.parkinggaragepos;

import filesystem.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The AutomatedParkingMachine class's responsibilities are to keep track of all
 * Tickets issued, and receipts given. In addition it delegates responsibilities
 * to the ParkingFeeManager class to calculate parking fees.
 *
 * @author Ben
 */
public class AutomatedParkingMachine {

    private final String INVALID_INPUT = "Invalid input was entered into the AutomatedParkingMachine obect";
    private final String CAR_NOT_FOUND_PART1 = "*******A car with an ID of ";
    private final String CAR_NOT_FOUND_PART2 = " was not found in our system.*******";
    private String dataFilePath = System.getProperty("user.home") + File.separatorChar + "BAG-ParkingGaragePOS"
            + File.separatorChar + "garageData.txt";
    FileService fileService;
    private NumberFormat nf = NumberFormat.getCurrencyInstance();
    private ParkingReceipt[] receipts = new ParkingReceipt[1];
    private ParkingTicket[] tickets = new ParkingTicket[1];
    private ParkingFeeManager feeManager;

    /**
     * This constructor instantiates an AutomatedParkingMachine by passing in a
     * ParkingFeeCalculatorStrategy objected
     *
     * @param pcs
     */
    public AutomatedParkingMachine(ParkingFeeCalculatorStrategy pcs) throws IOException {
        try {
            this.fileService = AbstractFileServiceFactory.getFileServiceInstance();
        } catch (IOException ex) {
            throw new IOException("Error reading the config file.");
        }

        if (pcs == null) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        feeManager = new ParkingFeeManager(pcs);
    }

    /**
     * Check a car into the garage system. Automatically assigns the ticket an
     * id
     *
     */
    public void checkCarIn() throws IOException {
        int highestCarIdInDataFile = 1;


        int recordsInFile = fileService.readFile(dataFilePath).size();
        if (recordsInFile > 0) {
            highestCarIdInDataFile = recordsInFile + 1;
        }
        


        if (tickets[0] == null) {
            tickets[0] = new ParkingTicket(highestCarIdInDataFile);
            return;
        }
        this.addTicketToArray();
    }

    /**
     * This method reads in the data file and gets it's size to know what number
     * to assign to a car for check in.
     *
     * @return - the number of records in the file to be assigned as the next
     * car id
     * @throws IOException
     */
    public int getTicketNumber() throws IOException {
        int highestCarIdInDataFile = 1;


        int recordsInFile = fileService.readFile(dataFilePath).size();
        if (recordsInFile > 0) {
            highestCarIdInDataFile = recordsInFile + 1;
        }
        return highestCarIdInDataFile;

    }

    /**
     * This method adds a Ticket object to the existing Ticket object array
     */
    private void addTicketToArray() {
        //Add a ticket to the array
        ParkingTicket newTicket = new ParkingTicket();
        ParkingTicket[] tempArray = new ParkingTicket[tickets.length + 1];
        System.arraycopy(tickets, 0, tempArray, 0, tickets.length);
        tempArray[tempArray.length - 1] = newTicket;
        tickets = tempArray;

    }

    /**
     * This method checks out a specific car by it's ID and does so by passing
     * an explicit number of hours the car was parked.
     *
     * @param carID
     * @param hours
     * @param garage
     */
    public String checkCarOut(int carID, double hours, Garage garage) throws IllegalArgumentException{
        boolean carFound = false;
        String output = "";
        //Validate input
        if (carID < 0 || hours < 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        //This loop finds ticket in the tickets array by carID and calculates it's fee
        for (ParkingTicket pt : tickets) {
            if (carID == pt.getCarID() && !pt.isCarCheckedOut()) {
                carFound = true;
                this.calculateFee(hours);
                pt.setCarCheckedOut(true);
                this.addReceiptToArray(pt, hours);
                output = this.outputReceipt(carID);
                addCarTotaltoFile(carID, hours);
            }
        }

        //If the car ID isn't found it outputs to the user that the car ID is invalid
        
        if (!carFound) {
            throw new CarNotFoundException(CAR_NOT_FOUND_PART1
                    + carID + CAR_NOT_FOUND_PART2);
        }
        

        return output;
    }

    /**
     * This method checks out a specific car by it's ID and does so by passing
     * an explicit number of hours the car was parked.
     *
     * @param carID
     * @param hours
     */
    public String checkCarOut(int carID, double hours) {
        boolean carFound = false;
        String output = "";
        //Validate input
        if (carID < 0 || hours < 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        //This loop finds ticket in the tickets array by carID and calculates it's fee
        for (ParkingTicket pt : tickets) {
            if (carID == pt.getCarID() && !pt.isCarCheckedOut()) {
                carFound = true;
                this.calculateFee(hours);
                pt.setCarCheckedOut(true);
                this.addReceiptToArray(pt, hours);
                output = this.outputReceipt(carID);
                addCarTotaltoFile(carID, hours);
            }

        }

        //If the car ID isn't found it outputs to the user that the car ID is invalid
        if (!carFound) {
            throw new CarNotFoundException(CAR_NOT_FOUND_PART1
                    + carID + CAR_NOT_FOUND_PART2);
        }

        return output;
    }

    /**
     * This method adds a line record to the data file. It writes the car id,
     * the hours parked, and the fee charged to each line.
     *
     * @param carID - the id of the car that was checked in
     * @param hours - the amount of the car was parked.
     */
    private void addCarTotaltoFile(int carID, double hours) {
        List carEntryData = new ArrayList<Map<String, String>>();
        Map record = new LinkedHashMap<String, String>();

        record.put("id", String.valueOf(carID));
        record.put("totalHours", String.valueOf(hours));
        record.put("totalFee", String.valueOf(this.calculateFee(hours)));

        carEntryData.add(record);
        fileService.appendToFile(dataFilePath, carEntryData);

    }

    /**
     * This method outputs the receipt for a specific car ID in a specific
     * garage
     *
     * @param carID
     * @param garage
     * @deprecated 
     */
    private void outputReceipt(int carID, Garage garage) {
//        String output = "";
//        //Validate carID
//        if (carID < 0 || garage == null) {
//            throw new IllegalArgumentException(INVALID_INPUT);
//        }
//
//        System.out.println("------------------------");
//        System.out.println(garage.getName() + "\t\t|" + '\n'
//                + garage.getStreet1() + "\t\t|" + '\n'
//                + garage.getCity() + ", " + garage.getState() + " " + garage.getZip() + "\t|\n\t\t\t|");
//
//        for (ParkingReceipt pr : receipts) {
//            if (carID == pr.getCarID()) {
//                System.out.println(pr.getHoursParked() + " hours parked \t|\n"
//                        + "fee charged: " + nf.format(pr.getFeePaid()) + "\t| \n"
//                        + "--------------------" + '\n');
//
//
//            }
//        }

    }

    /**
     * This method outputs the receipt for a specific car ID in a specific
     * garage
     *
     * @param carID
     * @param garage
     */
    private String outputReceipt(int carID) {
        String output = "";
        //Validate carID
        if (carID < 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }

        for (ParkingReceipt pr : receipts) {
            if (pr.getCarID() == carID) {
                output += pr.getCarID() + "\t\t" + pr.getHoursParked() + "\t\t" + nf.format(pr.getFeePaid()) + "\n";
            }
        }

        return output;

    }

    /**
     * This method reads all the current data in the file and makes it a big
     * string.
     *
     * @return - the output string of all the records in the data file.
     * @throws IOException
     */
    public String readDataContentsAndOutput() throws IOException {
        String output = "";
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        data = fileService.readFile(dataFilePath);
        for (Map<String, String> map : data) {
            output += map.get("id") + "\t\t" + map.get("totalHours") + "\t\t" + map.get("totalFee") + '\n';
        }
        return output;
    }

    /**
     * This method adds a Receipt object to the existing Receipt object array by
     * passing in a ParkingTicket object reference, and a specific number of
     * hours. Receipts are added the array only after a car has been checked
     * out.
     *
     * @param ticket
     * @param hours
     */
    private void addReceiptToArray(ParkingTicket ticket, double hours) {
        //Validate input
        if (ticket == null || hours < 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }

        //Check to see if there are any objercts in the ParkingReceipt array, if not add to first slot
        if (receipts[0] == null) {
            receipts[0] = new ParkingReceipt(ticket.getCarID(), hours, this.calculateFee(hours));
            return;
        }

        //Increase array size, copy the old array into the new array
        ParkingReceipt newReceipt = new ParkingReceipt(ticket.getCarID(), hours, this.calculateFee(hours));
        ParkingReceipt[] tempArray = new ParkingReceipt[receipts.length + 1];
        System.arraycopy(receipts, 0, tempArray, 0, receipts.length);
        tempArray[tempArray.length - 1] = newReceipt;
        receipts = tempArray;
    }

    /**
     * This method returns the total number of hours and fees the garage has
     * collected
     *
     * 
     */
    public String getTotalHoursAndFees() {
        double hoursParked = 0;
        double totalFees = 0;
        String output = null;

        for (ParkingReceipt pr : receipts) {
            hoursParked += pr.getHoursParked();
            totalFees += pr.getFeePaid();
        }

        output = "Totals for garage today: "
                + hoursParked + " hours charged, "
                + nf.format(totalFees) + " collected.";

        return output;
    }

    /**
     * This will calculate a fee based on an input number of hours
     *
     * @param hours
     * @return
     */
    private double calculateFee(double hours) {
        if (hours < 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        return feeManager.calculateFee(hours);
    }

    /**
     * This method checks all cars out of the garage. It has not real world
     * application, but is useful in testing the application to see the totals
     * of all cars that have been checked in
     */
    public void checkAllCarsOut() {
        for (ParkingTicket pt : tickets) {
            if (pt.isCarCheckedOut() == false) {
                this.calculateFee(pt);
                pt.setCarCheckedOut(true);
            }
        }
    }

    
    public String getDataFilePath() {
        return dataFilePath;
    }

    public void setDataFilePath(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.fileService);
        hash = 59 * hash + Objects.hashCode(this.feeManager);
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
        final AutomatedParkingMachine other = (AutomatedParkingMachine) obj;
        if (!Objects.equals(this.fileService, other.fileService)) {
            return false;
        }
        if (!Objects.equals(this.feeManager, other.feeManager)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AutomatedParkingMachine{" + "dataFilePath=" + dataFilePath + ", nf=" + nf + ", receipts=" + receipts + ", tickets=" + tickets + ", feeManager=" + feeManager + '}';
    }

    
    // <editor-fold defaultstate="collapsed" desc="Currently unsupported operations">
    /**
     * This method checks out a specific car by it's ID. It searches for the id,
     * if found it will calculate the fee based on the ticket that contains that
     * specific ID
     *
     * @param ticket
     * @throws UnsupportedOperationException
     */
    public void checkCarOut(int carID) {
        throw new UnsupportedOperationException("Not supported yet.");
        
    }

    /**
     * This method will add a reciept to the array by passing in a ParkingTicket
     * object reference. Receipts are added the array only after a car has been
     * checked out.
     *
     * @param ticket
     */
    private void addReceiptToArray(ParkingTicket ticket) {
        throw new UnsupportedOperationException("Not supported yet.");

        /*
         //double hours; //This will hold the hours, calculated based on difference in checkin time and checkout time
         double fee = this.calculateFee(ticket);
        
        
         //Validate input
         if(ticket == null){
         throw new IllegalArgumentException(INVALID_INPUT);
         }
        
         //Check to see if there are any objercts in the ParkingReceipt array, if not add to first slot
         if(receipts[0] == null){
         receipts[0] = new ParkingReceipt(ticket);
         return;
         }
        
         //Increase array size, copy the old array into the new array
         ParkingReceipt newReceipt = new ParkingReceipt(ticket);
         ParkingReceipt[] tempArray = new ParkingReceipt[receipts.length + 1];
         System.arraycopy(receipts, 0, tempArray, 0, receipts.length);
         tempArray[tempArray.length - 1] = newReceipt;
         receipts = tempArray;
         */
    }

    /**
     * This will calculate the fee for a specific ticket
     *
     * @param ticket
     * @return
     */
    private double calculateFee(ParkingTicket ticket) {
        throw new UnsupportedOperationException("Not supported yet.");
        /*
         if (ticket == null) {
         throw new IllegalArgumentException(INVALID_INPUT);
         }
         return feeManager.calculateFee(ticket);
         */
    }
    // </editor-fold>
}
