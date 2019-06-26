package Model.player;

import Controller.Player_Controllers.*;

public class Users {
    public static Archeoloog_Controller archeoloogController = new Archeoloog_Controller("Archeoloog");
    public static Klimmer_Controller klimmerController = new Klimmer_Controller("Klimmer");
    public static Meteooroloog_Controller meteooroloogController;
    public static Navigator_Controller navigatorController;
    public static Verkenner_Controller verkennerController;
    public static Waterdrager_Controller waterdragerController;
    static Object local;

//    public static Archeoloog_Controller getArcheoloogController() {
//        return archeoloogController;
//    }
//
//    public static void setArcheoloogController(Archeoloog_Controller archeoloogController) {
//        Users.archeoloogController = archeoloogController;
//    }
//
//    public static Klimmer_Controller getKlimmerController() {
//        return klimmerController;
//    }
//
//    public static void setKlimmerController(Klimmer_Controller klimmerController) {
//        Users.klimmerController = klimmerController;
//    }
//
//    public static Meteooroloog_Controller getMeteooroloogController() {
//        return meteooroloogController;
//    }
//
//    public static void setMeteooroloogController(Meteooroloog_Controller meteooroloogController) {
//        Users.meteooroloogController = meteooroloogController;
//    }
//
//    public static Navigator_Controller getNavigatorController() {
//        return navigatorController;
//    }
//
//    public static void setNavigatorController(Navigator_Controller navigatorController) {
//        Users.navigatorController = navigatorController;
//    }
//
//    public static Verkenner_Controller getVerkennerController() {
//        return verkennerController;
//    }
//
//    public static void setVerkennerController(Verkenner_Controller verkennerController) {
//        Users.verkennerController = verkennerController;
//    }
//
//    public static Waterdrager_Controller getWaterdragerController() {
//        return waterdragerController;
//    }
//
//    public static void setWaterdragerController(Waterdrager_Controller waterdragerController) {
//        Users.waterdragerController = waterdragerController;
//    }

    public static Object getLocal() {
        return local;
    }

    public static void setLocal(Object local) {
        Users.local = local;
    }
}
