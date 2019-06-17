package Controller.firebase_controllers;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Model.data.StaticData;
import firebase.FirebaseService;

import java.util.HashMap;
import java.util.Map;

public class UpdateFirebaseController {
    private static UpdateFirebaseController updateFirebaseController;
    private StaticData staticData;
    private PlayerController playerController;
    private StormController stormController;

    public UpdateFirebaseController(){
        staticData = StaticData.getInstance();
        stormController = StormController.getInstance();
        playerController = PlayerController.getInstance();

    }

    public static UpdateFirebaseController getInstance(){

        if(updateFirebaseController == null){
            updateFirebaseController = new UpdateFirebaseController();
        }
        return updateFirebaseController;
    }

    public void updateFirebase(){
        StaticData staticData = StaticData.getInstance();
        Map<String, Object> data = new HashMap<String, Object>();
        Object classes = ((Map) staticData.getRoomInfo()).get("Selectable_classes");
        Map<String, Object> myObject = new HashMap<String, Object>();
        for(int i = 0; i < ((Map) classes).size(); i++) {
            Object singeClass = ((Map) classes).get(Integer.toString(i));
            if(((((Map) singeClass).get("name")).toString()).equals(staticData.getClassName())){
                Map<String, Object> obj = new HashMap<String, Object>() {
                    {
                        put("name", staticData.getClassName());
                        put("maxWater", playerController.getPlayer().getMaxWater());
                        put("water", playerController.getPlayer().getWater());
                    }
                };
                myObject.put(String.valueOf(i), obj);
            }else{
                Map<String, Object> obj = new HashMap<String, Object>() {
                    {
                        put("name", ((((Map) singeClass).get("name")).toString()));
                        put("maxWater", (((Map) singeClass).get("maxWater")));
                        put("water", (((Map) singeClass).get("water")));
                    }
                };
                myObject.put(String.valueOf(i), obj);
            }
        }
        data.put("Selectable_classes", myObject);
        (FirebaseService.getInstance()).addSpel(staticData.getRoomName(), data);

    }
}
