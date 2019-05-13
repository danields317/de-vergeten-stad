package Model.player;

import Controller.Player_Controllers.*;

public class Users {
    private static Archeoloog_Controller archeoloogController = new Archeoloog_Controller("test");
    private static Klimmer_Controller klimmerController;
    private static Meteooroloog_Controller meteooroloogController;
    private static Navigator_controller navigatorController;
    private static Verkenner_Controller verkennerController;
    private static Waterdrager_Controller waterdragerController;
    private static Object local;

    public static Archeoloog_Controller getArcheoloogController() {
        return archeoloogController;
    }

    public static void setArcheoloogController(Archeoloog_Controller archeoloogController) {
        Users.archeoloogController = archeoloogController;
    }

    public static Klimmer_Controller getKlimmerController() {
        return klimmerController;
    }

    public static void setKlimmerController(Klimmer_Controller klimmerController) {
        Users.klimmerController = klimmerController;
    }

    public static Meteooroloog_Controller getMeteooroloogController() {
        return meteooroloogController;
    }

    public static void setMeteooroloogController(Meteooroloog_Controller meteooroloogController) {
        Users.meteooroloogController = meteooroloogController;
    }

    public static Navigator_controller getNavigatorController() {
        return navigatorController;
    }

    public static void setNavigatorController(Navigator_controller navigatorController) {
        Users.navigatorController = navigatorController;
    }

    public static Verkenner_Controller getVerkennerController() {
        return verkennerController;
    }

    public static void setVerkennerController(Verkenner_Controller verkennerController) {
        Users.verkennerController = verkennerController;
    }

    public static Waterdrager_Controller getWaterdragerController() {
        return waterdragerController;
    }

    public static void setWaterdragerController(Waterdrager_Controller waterdragerController) {
        Users.waterdragerController = waterdragerController;
    }

    public static Object getLocal() {
        return local;
    }

    public static void setLocal(Object local) {
        Users.local = local;
    }
}
