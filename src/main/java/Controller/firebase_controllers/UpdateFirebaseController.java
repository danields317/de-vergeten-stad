package Controller.firebase_controllers;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Controller.Tile_Controllers.TileController;
import Model.Bord.Onderdeel;
import Model.Tiles.EquipmentTile;
import Model.Tiles.PartTile;
import Model.Tiles.Tile;
import Model.Tiles.Tunnel;
import Model.data.StaticData;
import Model.player.Player;
import Model.storm.Storm;
import Model.storm.StormEvent;
import Model.storm.StormEventBeweging;
import firebase.FirebaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateFirebaseController {
    private static UpdateFirebaseController updateFirebaseController;
    private StaticData staticData;
    private PlayerController playerController;
    private StormController stormController;
    private TileController tc = TileController.getInstance();

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

        data.put("tiles", makeTileMap());
        data.put("storm", makeStormMap());

        data.put("Selectable_classes", myObject);
//        data.put("activePlayer", activePlayer);
        data.put("activePlayer", "Archeoloog");
        (FirebaseService.getInstance()).addSpel(staticData.getRoomName(), data);

    }

    private Map<String, Object> makeStormMap(){
        Map<String, Object> stormMap = new HashMap<>();

        ArrayList<StormEvent> events = stormController.getStormEvents();
        Storm storm = stormController.getStorm();

        Map<String, Object> stormEventsMap = new HashMap<>();
        int stormEventCounter = 0;
        for (StormEvent event : events){
            switch (event.naam){
                case STERKER:
                    stormEventsMap.put(Integer.toString(stormEventCounter), event.naam.toString());
                    break;
                case BRANDT:
                    stormEventsMap.put(Integer.toString(stormEventCounter), event.naam.toString());
                    break;
                case BEWEGING:
                    stormEventsMap.put(Integer.toString(stormEventCounter), event.naam.toString()+((StormEventBeweging) event).richting.toString()+((StormEventBeweging) event).stappen.toString());
                    break;
            }
            stormEventCounter++;
        }

        stormMap.put("events", stormEventsMap);
        stormMap.put("sterkte", storm.getSterkte());
        stormMap.put("subSterkte", storm.getSubSterkte());
        stormMap.put("x", storm.getX());
        stormMap.put("y", storm.getY());
        stormMap.put("stapelCounter", stormController.getStapelCounter());

        return stormMap;
    }

    private Map<String, Object> makeTileMap(){
        ArrayList<Tile> randomTiles = tc.getTiles();
        Map<String, Object> tilesMap = new HashMap<>();
        int tileCounter = 0;
        for (Tile tile : randomTiles){

            Map<String, Object> tile0 = new HashMap<>();

            tile0.put("discovered", tile.isDiscovered());
            tile0.put("aantalZandTegels", tile.getZand());
            tile0.put("hasZonneSchild", tile.hasZonneSchild());
            tile0.put("x", tile.getX());
            tile0.put("y", tile.getY());
            tile0.put("naam", tile.getVariant().toString());

            Map<String, Object> spelersMap = new HashMap<>();
            ArrayList<Player> players = tile.getSpelers();
            int playerCounter = 0;
            for (Player player : players){
                spelersMap.put(Integer.toString(playerCounter), player.getClassName());
            }

            Map<String, Object> onderdelenMap = new HashMap<>();
            ArrayList<Onderdeel> onderdelen = tile.getOnderdelen();
            int onderdelenCounter = 0;
            for (Onderdeel onderdeel : onderdelen){
                onderdelenMap.put(Integer.toString(playerCounter), onderdeel.getSoort().toString());
            }

            tile0.put("spelers", spelersMap);
            tile0.put("onderdelen", onderdelenMap);

            tilesMap.put(Integer.toString(tileCounter), tile0);
            tileCounter++;
        }
        return tilesMap;
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
