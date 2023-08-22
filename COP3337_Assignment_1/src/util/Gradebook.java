package util;


import java.util.*;

public class Gradebook {
    private ArrayList<Student> listOfStudents;
    public ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }
    public Gradebook() {
        listOfStudents = new ArrayList<Student>();
    }
    public double calculateAvg() {
        double sum = 0;
        for(Student s: listOfStudents)
            sum += s.getGrade().getScore();
        return sum / listOfStudents.size();
    }
    public float calculateMedian() {
        int i = 0, n = listOfStudents.size();
        int[] scores = new int[n];
        for(Student s: listOfStudents)
            scores[i++] = s.getGrade().getScore();
        Arrays.sort(scores);
        if (n % 2 == 0)
            return (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
        else
            return scores[n / 2];
    }

    public void printAllStudents() {
        for(Student s: listOfStudents)
            System.out.printf("%s\t\t%s\t\t%d\t\t%d\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore());
    }// Method to print all student with number scores

    public void printAllStudentsLetters(){
        for (Student s: listOfStudents)
            System.out.printf("%s\t%s\t%d\t%s\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade());
    }// Method to print all student with letter scores

    public void addStudent(Student s) {
        listOfStudents.add(s);
    }// Method to add a student into the lit of Students

    public int minScore() {
        int min = 101;
        for(Student s: listOfStudents)
            if(s.getGrade().getScore() < min)
                min = s.getGrade().getScore();
        return min;
    }//Method to find the lowest score out of the students that were inputted

    public int maxScore(){
        int max = 0;
        for(Student s: listOfStudents)
            if(s.getGrade().getScore() > max)
                max = s.getGrade().getScore();
        return max;
    }//Method to find the highest score out of the students that were inputted

    public String letter(int pid){
        String letterGrade = null;
        int counter = 0;
        for (Student s: listOfStudents){
            if(pid == s.getPid()) {
                letterGrade = s.getGrade().getLetterGrade();
                counter++;
            }
        }
        if (counter == 1)
            return letterGrade;
        else
            return "Invalid command! try again";
    }//Method to print letter grade of student given the pid

    public String name(int pid){
        String name = null;
        int counter = 0;
        for (Student s : listOfStudents) {
            if (pid == s.getPid()) {
                name = s.getFirstName() + " " + s.getLastName();
                counter++;
            }
        }
        if (counter == 1)
            return name;
        else
            return "Invalid command, try again";
    }//Method to print name of given panther ID

    public void change(int pid, int grade) {
        for (Student s : listOfStudents) {
            if (pid == s.getPid()){
                Grade g = new Grade(grade);
                s.setGrade(g);
            }
        }
    }//Method to change score with given pid and score
    //returns nothing

    public String avgLetter(){
        int avg = (int) calculateAvg();
        Grade g = new Grade(avg);
        return g.getLetterGrade();
    }//Method to return average letter grade with given inputs

    public String medLetter(){
        int median = (int) calculateMedian();
        Grade g = new Grade(median);
        return  g.getLetterGrade();
    }//Method to return median letter grade with given inputs
}