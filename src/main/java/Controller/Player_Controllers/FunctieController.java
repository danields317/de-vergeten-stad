package Controller.Player_Controllers;

import Controller.Bord_Controllers.SoundController;
import Controller.Tile_Controllers.StormController;
import Controller.Tile_Controllers.TileController;
import Model.Tiles.Tile;
import Model.data.StaticData;
import View.ViewManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
//                if((((Long) (((Map) singeClass).get("water"))).intValue() - 1) < 0){
//                    //endLose();
//                }
                Map<String, Object> obj = new HashMap<String, Object>() {
                    {
                        String name = ((((Map) singeClass).get("name")).toString());
                        put("name", name);
                        put("maxWater", (((Map) singeClass).get("maxWater")) );
                        if (checkSpelersZonBrandt(name)){
                            put("water", (Integer.valueOf(((Map)singeClass).get("water").toString())));
                        } else {
                            put("water", (Integer.valueOf(((Map)singeClass).get("water").toString()))-1);
                        }
                    }
                };
                myObject.put(String.valueOf(i), obj);
            }
        }
        data.put("Selectable_classes", myObject);
        data.put("activePlayer", ((Map) staticData.getRoomInfo()).get("activePlayer"));

        staticData.setRoomInfo(data);

    }

    /**
     * Omdat spelers geen objecten zijn checkt de functie voor een string met een playerklasse naam of die in een
     * tunnel staat of onder een zonneschild staat.
     *
     * @param speler Een String met een spelerklasse
     * @return Een boolean of er bij de speler water af moet.
     */
    private boolean checkSpelersZonBrandt(String speler){
        TileController tileController = TileController.getInstance();
        ArrayList<Tile> tiles = tileController.getTiles();
        for (Tile tile : tiles){
            if ((tile.getVariant() == Tile.Varianten.TUNNEL || tile.hasZonneSchild()) && tile.getPlayers().contains(speler)){
                return true;
            }
        }
        return false;
    }

    public void endLose(){
        SoundController sound = new SoundController();
        sound.play("Sound/nTest.wav");
        ViewManager.getInstance().loadEndGame(ViewManager.endConditions.DEHYDRATION);
    }
}
