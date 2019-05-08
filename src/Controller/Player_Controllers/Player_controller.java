package Controller.Player_Controllers;

import Model.player.Navigator;

public class Player_controller {

    private boolean klimmer = false;
    private boolean verkenner = false;
    private boolean meteooroloog = false;
    private boolean archeoloog = false;
    private boolean waterdrager = false;
    private boolean navigator = false;
    private Klimmer_Controller klimmerCon;
    private Verkenner_Controller verkennerCon;
    private Meteooroloog_Controller meteooroloogCon;
    private boolean Archeoloog = false;
    private boolean Waterdrager = false;
    //private Navigator_Controller navigatorCon;

    public Player_controller(String nickname, String className){
        switch(className){
            case "Klimmer":
                this.klimmer = true;
                this.klimmerCon = new Klimmer_Controller(nickname);
                break;
            case "Verkenner":
                this.verkenner = true;
                this.verkennerCon = new Verkenner_Controller(nickname);
                break;

        }

    }


}
