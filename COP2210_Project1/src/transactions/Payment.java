package transactions;

public class Payment {

    private static final double DOLLARVALUE = 1.00;
    private static final double QUATERVALUE = 0.25;
    private static final double DIMEVALUE = 0.10;
    private static final double NICKELVALUE = 0.05;
    private static final double PENNYVALUE = 0.01;

    private int numberOfOneDollarBills;
    private int numberOfQuarters;
    private int numberOfDimes;
    private int numberOfNickels;
    private int numberOfPennies;

    public Payment(int numberOfOneDollarBills,
                   int numberOfQuarters,
                   int numberOfDimes,
                   int numberOfNickels,
                   int numberOfPennies) {

        this.numberOfOneDollarBills = numberOfOneDollarBills;
        this.numberOfQuarters = numberOfQuarters;
        this.numberOfDimes = numberOfDimes;
        this.numberOfNickels = numberOfNickels;
        this.numberOfPennies = numberOfPennies;
    }

    public int getNumberOfOneDollarBills() {
        return numberOfOneDollarBills;
    }

    public int getNumberOfQuarters() {
        return numberOfQuarters;
    }

    public int getNumberOfDimes() {
        return numberOfDimes;
    }

    public int getNumberOfNickels() {
        return numberOfNickels;
    }

    public int getNumberOfPennies() {
        return numberOfPennies;
    }

    public void displayInfo(){
        System.out.println("==========================================");
        System.out.println("Payment Info");
        System.out.println("==========================================");
        System.out.printf("Number of Dollar:\t\t %-15d\n", numberOfOneDollarBills);
        System.out.printf("Number of Quarters:\t\t %-15d \n", numberOfQuarters);
        System.out.printf("Number of Dimes:\t\t %-15d\n", numberOfDimes);
        System.out.printf("Number of Nickels:\t\t %-15d\n", numberOfNickels );
        System.out.printf("Number of Pennies:\t\t %-15d\n", numberOfPennies );
        System.out.printf("Total Payment:\t\t\t $%-15.2f\n", paymentValue() );
        System.out.println("");
    }

    public double paymentValue(){
        double total = numberOfOneDollarBills * DOLLARVALUE +
                numberOfQuarters * QUATERVALUE +
                numberOfDimes * DIMEVALUE +
                numberOfNickels * NICKELVALUE +
                numberOfPennies * PENNYVALUE;

        return total;
    }
}
