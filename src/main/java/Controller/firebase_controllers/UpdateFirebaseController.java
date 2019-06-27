package Controller.firebase_controllers;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Controller.Tile_Controllers.TileController;
import Model.Bord.Onderdeel;
import Model.Tiles.*;
import Model.data.StaticData;
import Model.player.Player;
import Model.storm.Storm;
import Model.storm.StormEvent;
import Model.storm.StormEventBeweging;
import firebase.FirebaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 *
 */

public class UpdateFirebaseController {
    private static UpdateFirebaseController updateFirebaseController;
    private StaticData staticData;
    private PlayerController playerController;
    private StormController stormController;
    private TileController tc;
    private boolean start = false;

    private boolean startGame = false;

    private int counter = 0;
    private String[] testSpelers = {"Archeoloog", "Klimmer"};

    public UpdateFirebaseController(){
        staticData = StaticData.getInstance();
    }

    public static UpdateFirebaseController getInstance(){

        if(updateFirebaseController == null){
            updateFirebaseController = new UpdateFirebaseController();
        }
        return updateFirebaseController;
    }

    public void updateFirebase(){
        start = false;
        stormController = StormController.getInstance();
        playerController = PlayerController.getInstance();
        tc = TileController.getInstance();
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
        data.put("onderdelen", makeOnderdelenMap());

        data.put("Selectable_classes", myObject);

        data.put("activePlayer", activePlayer);

//        data.put("activePlayer", "Archeoloog");
        (FirebaseService.getInstance()).addSpel(staticData.getRoomName(), data);

    }

    private Map<String, Object> makeOnderdelenMap(){
        Map<String, Object> onderdelenMap = new HashMap<>();
        TileController tileController = TileController.getInstance();
        ArrayList<Onderdeel> onderdelen = tileController.getOnderdelen();

        int counter = 0;
        for (Onderdeel onderdeel : onderdelen){
            Map<String, Object> onderdeelMap = new HashMap<>();
            onderdeelMap.put("x", onderdeel.getX());
            onderdeelMap.put("y", onderdeel.getY());
            onderdeelMap.put("soort", onderdeel.getSoort().toString());
            onderdeelMap.put("opgepakt", onderdeel.isOpgepakt());
            onderdelenMap.put(Integer.toString(counter), onderdeelMap);
            counter++;
        }

        return onderdelenMap;
    }

    private Map<String, Object> makeStormMap(){
        Map<String, Object> stormMap = new HashMap<>();
        StormController stormController = StormController.getInstance();
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
            boolean start = false;
            Map<String, Object> tile0 = new HashMap<>();

            tile0.put("discovered", tile.isDiscovered());
            tile0.put("aantalZandTegels", tile.getZand());
            tile0.put("hasZonneSchild", tile.hasZonneSchild());

            tile0.put("x", tile.getX());
            tile0.put("y", tile.getY());
            String variant = tile.getVariant().toString();
            tile0.put("naam", variant);

            switch (variant){
                case "PART":
                    tile0.put("richting", ((PartTile)tile).getRichting().toString());
                    tile0.put("soort", ((PartTile)tile).getSoort().toString());
                    break;
                case "EQUIPMENT":
                    tile0.put("equipment", ((EquipmentTile)tile).getEquipment().getEquipmentType().toString());
                    break;
                case "TUNNEL":
                    tile0.put("equipment", ((Tunnel)tile).getEquipment().getEquipmentType().toString());
                    break;
                case "START":
                    tile0.put("equipment", ((StartTile)tile).getEquipment().getEquipmentType().toString());


                    start = true;
                    break;
            }

            if(!start || !this.start){
                tile0.put("Players", tile.getPlayers());
            }else{
                System.out.print("kkkkkkkkkkkkkkkgggggggggggg");
                ArrayList<String> a = new ArrayList<>();
                a.add("Archeoloog");
                a.add("Klimmer");
                a.add("Verkenner");
                a.add("Waterdrager");
                tile0.put("Players", a);
            }

            Map<String, Object> spelersMap = new HashMap<>();
            ArrayList<Player> players = tile.getSpelers();
            int playerCounter = 0;
            for (Player player : players){
                spelersMap.put(Integer.toString(playerCounter), player.getClassName());
            }

            Map<String, Object> onderdelenMap = new HashMap<>();
            ArrayList<Onderdeel> onderdelen = tile.getOnderdelen();
            for (Onderdeel onderdeel : onderdelen){
                onderdelenMap.put(Integer.toString(playerCounter), onderdeel.getSoort().toString());
            }

            if (!startGame && tile.getClass().equals(StartTile.class)){
//                String className, int maxWater, int water, String imagePath, Player.SpelerKlassen klasse
//                tile.addSpeler((new PlayerController("Archeoloog", 3, 3, "/Players/Archeoloog.png", Player.SpelerKlassen.ARCHEOLOOG)).getPlayer());
//                tile.addSpeler((new PlayerController("Klimmer", 3, 3, "/Players/Klimmer.png", Player.SpelerKlassen.KLIMMER)).getPlayer());
//                tile.addSpeler((new PlayerController("Verkenner", 4, 4, "/Players/Verkenner.png", Player.SpelerKlassen.VERKENNER)).getPlayer());
//                tile.addSpeler((new PlayerController("Waterdrager", 5, 5, "/Players/Waterdrager.png", Player.SpelerKlassen.WATERDRAGER)).getPlayer());
                spelersMap.put("0", "Archeoloog");
                spelersMap.put("1", "Klimmer");
                spelersMap.put("2", "Verkenner");
                spelersMap.put("3", "Waterdrager");
                startGame = true;
            }

            tile0.put("spelers", spelersMap);
            tile0.put("onderdelen", onderdelenMap);

            tilesMap.put(Integer.toString(tileCounter), tile0);
            tileCounter++;
        }
        return tilesMap;
    }

    public void makeFirebase(String roomName){
        start = true;
        Map<String, Object> players = new HashMap<String, Object>();

        tc = new TileController();

        Map<String, Object> obj = new HashMap<String, Object>() {
            {
                put("name", "Archeoloog");
                put("maxWater", 3);
                put("water", 3);
            }
        };
        players.put("0", obj);
        obj = new HashMap<String, Object>() {
            {
                put("name", "Klimmer");
                put("maxWater", 3);
                put("water", 3);
            }
        };
        players.put("1", obj);
        obj = new HashMap<String, Object>() {
            {
                put("name", "Verkenner");
                put("maxWater", 4);
                put("water", 4);
            }
        };
        players.put("2", obj);
        obj = new HashMap<String, Object>() {
            {
                put("name", "Waterdrager");
                put("maxWater", 5);
                put("water", 5);
            }
        };
        players.put("3", obj);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("Selectable_classes", players);
        data.put("tiles", makeTileMap());
        StaticData.getInstance().setRoomInfo(data);
        data.put("storm", makeStormMap());
        data.put("onderdelen", makeOnderdelenMap());
        data.put("activePlayer", "Archeoloog");

        (FirebaseService.getInstance()).addSpel(roomName, data);
        StaticData.getInstance().setRoomInfo(data);
    }
}
