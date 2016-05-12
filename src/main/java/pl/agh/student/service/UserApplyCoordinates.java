package pl.agh.student.service;

public class UserApplyCoordinates {

    private final String username;
    private int depth;
    private int numberOfPrimeReferences;
    private int numberOfSecondaryReferences;

    public UserApplyCoordinates(String username) {
        this.username = username;
    }

    public int getNumberOfPrimeReferences() {
        return numberOfPrimeReferences;
    }

    public void setNumberOfPrimeReferences(int numberOfPrimeReferences) {
        this.numberOfPrimeReferences = numberOfPrimeReferences;
    }

    public int getNumberOfSecondaryReferences() {
        return numberOfSecondaryReferences;
    }

    public void setNumberOfSecondaryReferences(int numberOfSecondaryReferences) {
        this.numberOfSecondaryReferences = numberOfSecondaryReferences;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getUsername() {
        return username;
    }

    //TODO implment me
    public boolean applly() {
        return true;
    }
}
