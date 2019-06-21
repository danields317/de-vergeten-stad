package Model.Login;

import Model.data.StaticData;
import View.LoadBordView;
import javafx.stage.Stage;
import observers.*;

import java.util.ArrayList;
import java.util.List;

public class Login implements LoginObservable {

    private String uName = "me";
    private String pass = "notme";
    private String givenUsername;
    private String givenPassword;
    private boolean loginCorrect = false;
    private String error = "";

    // List of all Observers of this Observable Objects
    private List<LoginObserver> observers = new ArrayList<LoginObserver>();

    public boolean kijkOfKamerBestaat(String kamerId, ArrayList<String> kamers) {
        setError("");
        if(kamers.contains(kamerId)){
            return true;
        }else{
            return false;
        }
    }

    public void laadKamer(String roomId, Stage s){
        new LoadBordView(s , roomId);

    }

    public void setError(String error){
        this.error = error;
        notifyAllObservers();
    }

    public String getError(){
        return error;
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


    public void checkLogin(String uName, String pass, String fbPass){
        setGivenUsername(uName);
        setGivenPassword(pass);
        if (pass.equals(fbPass)){
            StaticData staticData = StaticData.getInstance();
            staticData.setUsername(uName);
            LoginCorrect();
            setError("");
        }else{
            setError("Password of username klopt niet");
        }
    }

    // Add an observer to the list
    public void register(LoginObserver observer){
        observers.add(observer);
    }

    // Signal all observers that something has changed.
    // Also send <<this>> object to the observers.
    public void notifyAllObservers(){
        for (LoginObserver s : observers) {
            s.update(this);
        }
    }
}
