package util;

public class Grade {
    private int score;
    private String letterGrade;

    public Grade(int score) {
        this.score = score;
        letterGrade = score >= 90 ? "A" : score >= 85 ? "A-" : score >= 80 ? "B+" : score >= 75 ? "B" : score >= 70 ? "B-" :
                score >= 65 ? "C+" : score >= 60 ? "C" : score >= 50 ? "D" : "F";
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score){ this.score = score;}

    public String getLetterGrade() {
        return letterGrade;
    }
}