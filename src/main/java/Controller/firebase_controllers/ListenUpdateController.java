package Controller.firebase_controllers;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Model.data.StaticData;
import firebase.FirebaseService;

public class ListenUpdateController {

        private static ListenUpdateController listenUpdateController;
    private StaticData staticData;
    private PlayerController playerController;
    private StormController stormController;

    public ListenUpdateController(){
        staticData = StaticData.getInstance();
        stormController = StormController.getInstance();

    }

    public static ListenUpdateController getInstance(){

        if(listenUpdateController == null){
            listenUpdateController = new ListenUpdateController();
        }
        return listenUpdateController;
    }

    public void setFirebaseData(){
        playerController = PlayerController.getInstance();
        FirebaseService firebaseService = FirebaseService.getInstance();
        Object roomInfo = firebaseService.getSpel(staticData.getRoomName()).getData();
        (StaticData.getInstance()).setRoomInfo(roomInfo);
        playerController.updateData();


    }
}
