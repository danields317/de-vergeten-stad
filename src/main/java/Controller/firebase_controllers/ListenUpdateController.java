package Controller.firebase_controllers;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Controller.Tile_Controllers.TileController;
import Model.data.StaticData;
import firebase.FirebaseService;

public class ListenUpdateController {

        private static ListenUpdateController listenUpdateController;
    private StaticData staticData;
    private PlayerController playerController;
    private StormController stormController;
    private TileController tileController;

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
        stormController = StormController.getInstance();
        tileController = TileController.getInstance();
        FirebaseService firebaseService = FirebaseService.getInstance();

        Object roomInfo = firebaseService.getSpel(staticData.getRoomName()).getData();
        (StaticData.getInstance()).setRoomInfo(roomInfo);
        System.out.println(roomInfo);

        playerController.updateData();

        stormController.updateData();

        tileController.updateData();
    }
}
