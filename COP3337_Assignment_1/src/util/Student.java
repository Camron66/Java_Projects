package util;

public class Student extends Grade {
    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getPid() {
        return pid;
    }
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    public Student (String firstName, String lastName, int pid, int grade) {
        super(grade);
        this.firstName = firstName;
        this.lastName = lastName;
        this.pid = pid;
        this.grade = new Grade(grade);
    }
}