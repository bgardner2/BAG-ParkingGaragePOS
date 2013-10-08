package bag.parkinggaragepos;

import java.text.NumberFormat;


/**
 * The AutomatedParkingMachine class's responsibilities are to keep track of all Tickets issued, and receipts given.
 * In addition it delegates responsibilities to the ParkingFeeManager class to calculate parking fees.
 * @author Ben
 */
public class AutomatedParkingMachine {
    private final String INVALID_INPUT = "Invalid input was entered into the AutomatedParkingMachine obect";
    private final String CAR_NOT_FOUND_PART1 = "*******A car with an ID of ";
    private final String CAR_NOT_FOUND_PART2 = " was not found in our system.*******";
    
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
    public AutomatedParkingMachine(ParkingFeeCalculatorStrategy pcs) {
        if(pcs == null){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        feeManager = new ParkingFeeManager(pcs);
    }

    /**
     * Check a car into the garage system. Automatically assigns the ticket an
     * id
     *
     */
    public void checkCarIn() {

        if (tickets[0] == null) {
            tickets[0] = new ParkingTicket();
            return;
        }
        this.addTicketToArray();
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
     * @param carID
     * @param hours 
     */
    public void checkCarOut(int carID, double hours, Garage garage) {        
        boolean carFound = false;

        //Validate input
        if (carID < 0 || hours < 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }

        //This loop finds ticket in the tickets array by carID and calculates it's fee
        for (ParkingTicket pt : tickets) {
            if (carID == pt.getCarID()) {
                carFound = true;
                this.calculateFee(hours);
                pt.setCarCheckedOut(true);
                this.addReceiptToArray(pt, hours);
                this.outputReceipt(carID, garage);

            }
        }
        
        //If the car ID isn't found it outputs to the user that the car ID is invalid
        if (!carFound) {
            System.out.println(CAR_NOT_FOUND_PART1
                    + carID + CAR_NOT_FOUND_PART2 + '\n');
        }
    }
    
    /**
     * This method outputs the receipt for a specific car ID
     * @param carID 
     */
    private void outputReceipt(int carID, Garage garage){
        
        //Validate carID
        if (carID < 0 || garage == null) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        System.out.println("------------------------");
        System.out.println(garage.getName() + "\t\t|" + '\n'
                + garage.getStreet1() + "\t\t|" +'\n'
                + garage.getCity() + ", " + garage.getState() + " " + garage.getZip() + "\t|\n\t\t\t|");
        
        for (ParkingReceipt pr : receipts) {
            if (carID == pr.getCarID()) {
                 System.out.println(pr.getHoursParked() + " hours parked \t|\n"
                    + "fee charged: " + nf.format(pr.getFeePaid()) + "\t| \n"
                    + "--------------------" + '\n');
                 

            }
        }
        
    }
    
    /**
     * This method adds a Receipt object to the existing Receipt object array
     * by passing in a ParkingTicket object reference, and a specific number of
     * hours. Receipts are added the array only after a car has been checked out.
     * @param ticket
     * @param hours 
     */
    private void addReceiptToArray(ParkingTicket ticket, double hours) {
        //Validate input
        if(ticket == null || hours < 0){
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        
        //Check to see if there are any objercts in the ParkingReceipt array, if not add to first slot
        if(receipts[0] == null){
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
     * This method returns the total number of hours and fees the garage has collected
     */
    public void getTotalHoursAndFees() {
        double hoursParked = 0;
        double totalFees = 0;
        
        System.out.println("##### BEGIN DAILY CALCULATION #####");
        for (ParkingReceipt pr : receipts) {
            hoursParked += pr.getHoursParked();
            totalFees += pr.getFeePaid();
            System.out.println("Receipt for car with ID " + pr.getCarID() + ": "
                    + pr.getHoursParked() + " hours parked, "
                    + "fee charged: "
                    + nf.format(pr.getFeePaid()));
        }
        
        System.out.println("------------------------\n"
                    +"Totals for garage today: "
                    + hoursParked + " hours charged, "
                    + nf.format(totalFees) + " collected.\n");
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
    
    
    // <editor-fold defaultstate="collapsed" desc="Currently unsupported operations">
    /**
     * This method checks out a specific car by it's ID. It searches for the id,
     * if found it will calculate the fee based on the ticket that contains that 
     * specific ID
     * @param ticket 
     */
    public void checkCarOut(int carID) { 
        throw new UnsupportedOperationException("Not supported yet.");
        /*
        boolean carFound = false;

        //Validate input
        if (carID < 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }

        //This loop finds ticket in the tickets array by carID and calculates it's fee
        for (ParkingTicket pt : tickets) {
            if (carID == pt.getCarID()) {
                carFound = true;
                this.calculateFee(pt);
                pt.setCarCheckedOut(true);
                this.addReceiptToArray(pt);

            }
        }
        
        //If the car ID isn't found it outputs to the user that the car ID is invalid
        if (!carFound) {
            System.out.println("*******A car with an ID of "
                    + carID + " was not found in our system.*******");
        }
        */
    }
    
    /**
     * This method will add a reciept to the array by passing in a 
     * ParkingTicket object reference. Receipts are added the array
     * only after a car has been checked out.
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

