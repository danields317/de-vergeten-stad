package Model.player;

import Controller.Player_Controllers.*;

public class Users {
    public static Archeoloog_Controller archeoloogController = new Archeoloog_Controller("Archeoloog");
    public static Klimmer_Controller klimmerController = new Klimmer_Controller("Klimmer");
    public static Verkenner_Controller verkennerController;
    public static Waterdrager_Controller waterdragerController;
    static Object local;


    public static Object getLocal() {
        return local;
    }

    public static void setLocal(Object local) {
        Users.local = local;
    }
}
