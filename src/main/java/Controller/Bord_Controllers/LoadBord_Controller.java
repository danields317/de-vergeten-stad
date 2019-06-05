package Controller.Bord_Controllers;

import Model.Bord.LoadBord;
import observers.LoadBordObserver;
import observers.LoginObserver;

public class LoadBord_Controller {


    static LoadBord_Controller loadBordController;
    LoadBord loadBord;

    private LoadBord_Controller(){ loadBord = new LoadBord(); }

    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static LoadBord_Controller getInstance() {
        if (loadBordController == null) {
            loadBordController = new LoadBord_Controller();
        }
        return loadBordController;
    }

    public void registerObserver(LoadBordObserver sbv) {
        loadBord.register(sbv);
    }
}
