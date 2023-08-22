package machine;

import food.Apple;
import food.Sandwich;
import transactions.Payment;

public class Register {

    private static int registerCounter = 0;
    private static final  String REGISTERCODE =  "US-FL-032020-";
    private static final double DOLLARVALUE = 1.00 ;
    private static final double QUATERVALUE = 0.25 ;
    private static final double DIMEVALUE = 0.10 ;
    private static final double NICKELVALUE = 0.05 ;
    private static final double PENNYVALUE = 0.01 ;
    private String registerID;
    private int numberOfOneDollarBills;
    private int numberOfQuarters;
    private int numberOfDimes;
    private int numberOfNickels;
    private int numberOfPennies;
    private double currentTotal;

    public Register(int numberOfOneDollarBills, int numberOfQuarters, int numberOfDimes, int numberOfNickels, int numberOfPennies) {
        registerCounter++;
        registerID = REGISTERCODE + registerCounter ;



        currentTotal = 0.0;
        this.numberOfOneDollarBills = numberOfOneDollarBills;
        this.numberOfQuarters = numberOfQuarters;
        this.numberOfDimes = numberOfDimes;
        this.numberOfNickels = numberOfNickels;
        this.numberOfPennies = numberOfPennies;
    }

    //----------------------------------------------------------
    // Utility methods
    // Check UML Diagram
    //----------------------------------------------------------

    private double cashValue(){

        double total = numberOfOneDollarBills * DOLLARVALUE +
                numberOfQuarters * QUATERVALUE +
                numberOfDimes * DIMEVALUE +
                numberOfNickels * NICKELVALUE +
                numberOfPennies * PENNYVALUE;

        return total;

    }

    public void cashInfo(String personal){

        // if the personal is a Manager
        // then output the cashValue of the register
        // hint code:
        if(personal == "Manager") {
            System.out.println("==========================================");
            System.out.println("Register Cash Info");
            System.out.println("==========================================");

            System.out.println("Access Level:\t\t\t Valid");
            System.out.printf("Cash in the Register:\t $%-15.2f\n", cashValue());
            System.out.printf("Dollars:\t\t\t\t %-15d\n", numberOfOneDollarBills);
            System.out.printf("Quarters:\t\t\t\t %-15d\n", numberOfQuarters);
            System.out.printf("Dimes: \t\t\t\t\t %-15d\n", numberOfDimes);
            System.out.printf("Nickels: \t\t\t\t %-15d\n", numberOfNickels);
            System.out.printf("Pennies: \t\t\t\t %-15d\n\n", numberOfPennies);
        }else {
            System.out.println("==========================================");
            System.out.println("Register Cash Info");
            System.out.println("==========================================");
            System.out.println("Access Level:\t\t\t Not Valid by " + personal);
            System.out.println("");
        }

    }


    public void buyApple(Apple apple, Payment payment){
        System.out.println("==========================================");
        System.out.println("Register Buy Apple");
        System.out.println("==========================================");
        System.out.printf("Apple Price:\t\t\t $%-15.2f\n" , apple.price() );
        System.out.printf("Payment:\t\t\t\t $%-15.2f\n" , payment.paymentValue() );

        // check if you have enough payment to buy the apple
        // if your payment is less the apple price calculate the amount short
        // and output to the console
        // Sorry but you do not have enough money to buy the Apple
        // hint code:
         /*   System.out.printf("You need:\t\t $%-15.2f\n",(apple.price() - payment.paymentValue()));
            System.out.println("");
            System.out.println("Sorry but you do not have enough money to buy the Apple");
            System.out.println("==========================================");
            System.out.println("\n");
 */
        // else you have enough payment then give change to buyer
        // hence call the giveChange method with the apple price and payment
        // hint: use an if else statement
        // YOUR CODE HERE
        if(payment.paymentValue() < apple.price()) {
            System.out.printf("You need:\t\t\t\t $%-15.2f\n",(apple.price() - payment.paymentValue()));
            System.out.println("");
            System.out.println("Sorry but you do not have enough money to buy the Apple");
            System.out.println("==========================================");
            System.out.println("\n");
        }else {
            giveChange(apple.price(), payment);

        }

    }//end buyApple()


    public void buySandwich(Sandwich sandwich, Payment payment){
        System.out.println("==========================================");
        System.out.println("Register Buy Sandwich");
        System.out.println("==========================================");

        // check if you have enough payment to buy the sandwich
        // if your payment is less the sandwich price calculate the amount short
        // and output to the console
        // Sorry but you do not have enough money to buy the Sandwich
        // hint code:
        /*

            System.out.printf("Sandwich Price:\t\t $%-15.2f\n" , sandwich.getPrice() );
            System.out.printf("Payment:\t\t $%-15.2f\n" , payment.paymentValue() );
            System.out.println("");
            System.out.println("Sorry but you do not have enough money to buy the Sandwich");
            System.out.println("==========================================");
            System.out.println("\n");
*/
        // else you have enough payment then give change to buyer
        // hence call the giveChange method with the sandwich price and payment
        // hint: use an if else statement
        // YOUR CODE HERE
        if(sandwich.getPrice() > payment.paymentValue()){
            System.out.printf("Sandwich Price:\t\t\t $%-15.2f\n" , sandwich.getPrice() );
            System.out.printf("Payment:\t\t\t\t $%-15.2f\n" , payment.paymentValue() );
            System.out.printf("You need: \t\t\t\t $%-15.2f\n", sandwich.getPrice() - payment.paymentValue());
            System.out.println("");
            System.out.println("Sorry but you do not have enough money to buy the Sandwich");
            System.out.println("==========================================");
            System.out.println("\n");
        }else{
            System.out.printf("Sandwich Price:\t\t $%-15.2f\n" , sandwich.getPrice() );
            System.out.printf("Payment:\t\t $%-15.2f\n" , payment.paymentValue() );
            giveChange(sandwich.getPrice(), payment);
        }


    }//end buySandwich()
    private void giveChange(double price, Payment payment){

        // add payment to register
        // hint code:

        // YOUR CODE HERE
        numberOfOneDollarBills += payment.getNumberOfOneDollarBills();
        numberOfQuarters += payment.getNumberOfQuarters();
        numberOfDimes += payment.getNumberOfDimes();
        numberOfNickels += payment.getNumberOfNickels();
        numberOfPennies += payment.getNumberOfPennies();
        // calculate needed change
        // YOUR CODE HERE
        double neededChange = payment.paymentValue() - price;
        // rounded to whole number so you can use the % operator for the change
        // example 9.65 becomes 965
        int neededChangeWhole = (int)Math.round(neededChange * 100);

        System.out.printf("Change:\t\t\t\t\t $%-15.2f\n", neededChange);


        // figure out the dollar to give back
        // hint: 965 /100 = 9 because of the int/ int
        // so you have 9 dollars
        // update the remaining change to give back
        // 965 – 900 = 65 this is the cents you need to give back
        int giveDollars = neededChangeWhole / 100 ;
        neededChangeWhole -= giveDollars * 100;
        // figure out the quarters to give back
        // YOUR CODE HERE
        int giveQuarters = neededChangeWhole / 25 ;
        // update the remaining change to give back
        // YOUR CODE HERE
        neededChangeWhole -= giveQuarters * 25;
        // figure out the dimes to give back
        // YOUR CODE HERE
        int giveDimes = neededChangeWhole / 10 ;
        // update the remaining change to give back
        // YOUR CODE HERE
        neededChangeWhole -= giveDimes * 10;
        // figure out the nickels to give back
        // YOUR CODE HERE
        int giveNickels = neededChangeWhole / 5;
        // update the remaining change to give back
        // YOUR CODE HERE
        neededChangeWhole -= giveNickels * 5;
        // figure out the pennies to give back
        // YOUR CODE HERE
        int givePennies = neededChangeWhole ;

        // give the change back
        // remove the dollars, quarters, dimes, nickels, pennies
        // from the register
        // Hint code:
        // numberOfOneDollarBills -= changeDollars;
        // YOUR CODE HERE
        numberOfOneDollarBills -= giveDollars;
        numberOfQuarters -= giveQuarters;
        numberOfDimes -= giveDimes;
        numberOfNickels -= giveNickels;
        numberOfPennies -= givePennies;

        // output to the console the change:
        // dollars, quarters, dimes, nickels, pennies
        // Hint code:
        // System.out.printf("Dollars:\t\t %-15d\n", changeDollars);
        // System.out.printf("Quarters:\t\t %-15d\n", changeQuarters);
        // YOUR CODE HERE
        System.out.printf("Dollars: \t\t\t\t %-15d\n", giveDollars);
        System.out.printf("Quarters: \t\t\t\t %-15d\n", giveQuarters);
        System.out.printf("Dimes: \t\t\t\t\t %-15d\n", giveDimes);
        System.out.printf("Nickels: \t\t\t\t %-15d\n", giveNickels);
        System.out.printf("Pennies: \t\t\t\t %-15d\n", givePennies);


    }

}
