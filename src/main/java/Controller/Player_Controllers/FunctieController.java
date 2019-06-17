package Controller.Player_Controllers;

import Controller.Bord_Controllers.SoundController;
import Controller.Tile_Controllers.StormController;
import Model.data.StaticData;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class FunctieController {
    private static FunctieController functieController;
    private StaticData staticData;
    private PlayerController playerController;
    private StormController stormController;

    public FunctieController(){
        staticData = StaticData.getInstance();
        stormController = StormController.getInstance();
        playerController = PlayerController.getInstance();

    }

    public static FunctieController getInstance(){

        if(functieController == null){
            functieController = new FunctieController();
        }
        return functieController;
    }

    public void updateInfo(){
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
                if((((Long) (((Map) singeClass).get("water"))).intValue() - 1) < 0){
                    //endLose();
                }
                Map<String, Object> obj = new HashMap<String, Object>() {
                    {
                        put("name", ((((Map) singeClass).get("name")).toString()));
                        put("maxWater", (((Map) singeClass).get("maxWater")) );
                        put("water", (((Long) (((Map) singeClass).get("water"))).intValue() - 1));
                    }
                };
                myObject.put(String.valueOf(i), obj);
            }
        }
        data.put("Selectable_classes", myObject);
        data.put("activePlayer", ((Map) staticData.getRoomInfo()).get("activePlayer"));

        staticData.setRoomInfo(data);

    }

    public void endLose(){
        SoundController sound = new SoundController();
        sound.play("Sound/n.wav");
    }
}
