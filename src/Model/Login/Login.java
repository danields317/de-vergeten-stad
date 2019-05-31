package Model.Login;

import observers.SpelbordObservable;
import observers.SpelbordObserver;

import java.util.ArrayList;
import java.util.List;

public class Login implements SpelbordObservable {

    private int scorePlayer1 = 0; 		// Should be in player model - but out of time;
    private String uName = "me";
    private String pass = "notme";
    private String givenUsername;
    private String givenPassword;
    private boolean loginCorrect = false;

    // List of all Observers of this Observable Objects
    private List<SpelbordObserver> observers = new ArrayList<SpelbordObserver>();

    public Login() {
    }

    public String getScore(){
        return Integer.toString(scorePlayer1);
    }

    public void LoginCorrect(){
        loginCorrect = true;
    }
    public boolean isLoginCorrect(){
        return loginCorrect;
    }


    public String getGivenUsername() {
        return givenUsername;
    }

    public void setGivenUsername(String givenUsername) {
        this.givenUsername = givenUsername;
    }

    public String getGivenPassword() {
        return givenPassword;
    }

    public void setGivenPassword(String givenPassword) {
        this.givenPassword = givenPassword;
    }


    public void checkLogin(String uName, String pass){
        setGivenUsername(uName);
        setGivenPassword(pass);
        if((this.uName.equals(uName)) && (this.pass.equals(pass))){
            LoginCorrect();
        }
        notifyAllObservers();
    }

    public void increaseScore(){
        System.out.println("Spelbord - score increased");
        scorePlayer1++;

        // SOMETHING Changed !!  - We need to notify the observers !
        notifyAllObservers();
    }

    // Add an observer to the list
    public void register(SpelbordObserver observer){
        observers.add(observer);
    }

    // Signal all observers that something has changed.
    // Also send <<this>> object to the observers.
    public void notifyAllObservers(){
        for (SpelbordObserver s : observers) {
            s.update(this);
        }
    }
}
