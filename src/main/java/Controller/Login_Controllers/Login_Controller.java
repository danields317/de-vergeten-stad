package Controller.Login_Controllers;

import Controller.Controller;
import Model.Login.Login;
import javafx.stage.Stage;
import observers.*;

public class Login_Controller {

    static Login_Controller loginController;
    Login login;

    private Login_Controller() {
        login = new Login();
    }

    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static Login_Controller getInstance() {
        if (loginController == null) {
            loginController = new Login_Controller();
        }
        return loginController;
    }

    public void checkLogin(String uName, String pass){
        login.checkLogin(uName, pass);
    }

    public void startGame(String roomId){


    }

    public void loadGame(String roomId, Stage s){
        if(login.kijkOfKamerBestaat(roomId)){
            login.setError("Dat is wat je gaat doen zodra je vanavond thuiskomt");
            login.laadKamer(roomId, s);

        }else{
            login.setError("De kamer bestaat nog niet");
        }
    }

    public String getScore(){
        return login.getScore();
    }

    public void setScore() {
        login.increaseScore();
    }

    public void registerObserver(LoginObserver sbv) {
        login.register(sbv);
    }
}
