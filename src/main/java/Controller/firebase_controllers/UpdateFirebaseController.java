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
        boolean yesOrNo = true;
        String activePlayer = null;
        for(int i = 0; i < ((Map) classes).size(); i++) {
            Object singeClass = ((Map) classes).get(Integer.toString(i));
            if(yesOrNo){
                activePlayer = (((Map) singeClass).get("name")).toString();
                yesOrNo = false;
            }
            if(((((Map) singeClass).get("name")).toString()).equals(staticData.getClassName())){
                yesOrNo = true;
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
        //data.put("activePlayer", activePlayer);
        data.put("activePlayer", "Archeoloog");
        (FirebaseService.getInstance()).addSpel(staticData.getRoomName(), data);

    }

    public void makeFirebase(String roomName){
        Map<String, Object> myObject = new HashMap<String, Object>();
        Map<String, Object> obj = new HashMap<String, Object>() {
            {
                put("name", "Archeoloog");
                put("maxWater", 3);
                put("water", 3);
            }
        };
        myObject.put("0", obj);
        obj = new HashMap<String, Object>() {
            {
                put("name", "Klimmer");
                put("maxWater", 3);
                put("water", 3);
            }
        };
        myObject.put("1", obj);
        obj = new HashMap<String, Object>() {
            {
                put("name", "Verkenner");
                put("maxWater", 4);
                put("water", 4);
            }
        };
        myObject.put("2", obj);
        obj = new HashMap<String, Object>() {
            {
                put("name", "Waterdrager");
                put("maxWater", 5);
                put("water", 5);
            }
        };
        myObject.put("3", obj);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("Selectable_classes", data);

        (FirebaseService.getInstance()).addSpel(roomName, data);

    }
}
