package Controller.Login_Controllers;

import Controller.Controller;
import Model.Login.Login;
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
