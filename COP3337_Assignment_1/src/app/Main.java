package app;
import java.util.*;
import util.*;

public class Main {
    private static boolean isValid(String firstName, String lastName, String pid, String grade) {
        if(!Character.isUpperCase(firstName.charAt(0)))
            return false;
        for(char c: firstName.toCharArray())
            if(!Character.isLetter(c))
                return false;
        if(!Character.isUpperCase(lastName.charAt(0)))
            return false;
        //firstName specifications

        int dot = 0;
        for(char c: firstName.toCharArray()) {
            if (c == '.')
                dot++;
        }//Allow dot to be created

        for(char c: lastName.toCharArray()){
            if (dot > 1) {
                return false;
            }else if(!Character.isUpperCase(lastName.charAt(0))) {
                return false;
            }else if(Character.isDigit(c))
                return false;
        }//lastName specifications

        if (pid.length() != 7 || Integer.parseInt(pid) < 1000000)
            return false;
        //pid specifications
        if (Integer.parseInt(grade) > 100 || Integer.parseInt(grade) < 0) {
            return false;
        }//pid specifications

        return true;
    }
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String command;
        Gradebook book = new Gradebook();
        System.out.println("Welcome to my grade book!\nPlease enter the information of the first student using the following format:");
        System.out.println("\"firstName lastName PID grade\".\nPress Enter when you are done.");
        //Starting input
        while(true) {
            command = keyboard.next();
            if(command.equals("DONE"))
                break;
            String firstName, lastName,pid, grade;
            //Variables
            firstName = command;
            lastName = keyboard.next();
            pid = keyboard.next();
            grade = keyboard.next();
            //Set inputs to variables
            if(isValid(firstName, lastName, pid, grade)) {
                book.addStudent(new Student(firstName, lastName, Integer.parseInt(pid), Integer.parseInt(grade)));
                System.out.println("Please enter the information of the next student using the same format.");
                System.out.println("If there is no more students, please enter the keyword \"DONE\".\nPress Enter when you are done.");
                //add Student to grade book
            }else
                System.out.println("Please try again!");
        }


        while(true) {
            command = keyboard.next();//input to call case
            switch(command) {
                case "min":
                    String secondWord = keyboard.next();
                    if(secondWord.equals("score"))
                        System.out.println(book.minScore());
                    else if(secondWord.equals("letter"))
                        System.out.println(new Grade(book.minScore()).getLetterGrade());
                    else
                        System.out.println("Invalid command! try again!");
                    break;
                    // Calling commands for "min" case
                case "max":
                    secondWord = keyboard.next();
                    if(secondWord.equals("score"))
                        System.out.println(book.maxScore());
                    else if(secondWord.equals("letter"))
                        System.out.println(new Grade(book.maxScore()).getLetterGrade());
                    else
                        System.out.println("Invalid command! try again");
                    break;
                // Calling commands for "max" case
                case "letter":
                    secondWord = keyboard.next();
                    System.out.println(book.letter(Integer.parseInt(secondWord)));
                    break;
                // Calling commands for "letter" case
                case "name":
                    secondWord = keyboard.next();
                    System.out.println(book.name(Integer.parseInt(secondWord)));
                    break;
                // Calling commands for "name" case
                case "change":
                    secondWord = keyboard.next();
                    String thirdWord = keyboard.next();
                    book.change(Integer.parseInt(secondWord), Integer.parseInt(thirdWord));
                    break;
                // Calling commands for "change" case
                case "average":
                    secondWord = keyboard.next();
                    if (secondWord.equals("score"))
                        System.out.println(book.calculateAvg());
                    else if (secondWord.equals("letter"))
                        System.out.println(book.avgLetter());
                    else
                        System.out.println("Invalid command! try again");
                    break;
                // Calling commands for "average" case
                case "median":
                    secondWord = keyboard.next();
                    if(secondWord.equals("score"))
                        System.out.println(book.calculateMedian());
                    else if (secondWord.equals("letter"))
                        System.out.println(book.medLetter());
                    else
                        System.out.println("Invalid command! try again");
                    break;
                // Calling commands for "median" case
                case "tab":
                    secondWord = keyboard.next();
                    if(secondWord.equals("scores"))
                        book.printAllStudents();
                    else if(secondWord.equals("letters"))
                        book.printAllStudentsLetters();
                    else
                        System.out.println("Invalid command! try again");
                    break;
                // Calling commands for "tab" case
                case "quit":
                    return;
                //Quit program
                default:
                    System.out.println("Invalid command! try again!");
                //If error, print statement
            }
        }
    }

}