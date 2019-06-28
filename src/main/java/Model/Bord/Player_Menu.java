package Model.Bord;

import Model.data.StaticData;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player_Menu implements Player_Menu_Observable {
    private List<Player_Menu_Observer> observers = new ArrayList<Player_Menu_Observer>();
    private StaticData staticData = StaticData.getInstance();
    String[] players = new String[4];

    public void spelerBlokOpbouwen(){
        notifyAllObservers();
    }

//    private GridPane createUpdatedGridPane(LoadBordObservable sb){
//        FirebaseService firebaseService = FirebaseService.getInstance();
//        Object roomInfo = firebaseService.getSpel(roomId).getData();
//        (StaticData.getInstance()).setRoomInfo(roomInfo);
//        Object classes = ((Map) roomInfo).get("Selectable_classes");
//        GridPane gridPane = new GridPane();
//        gridPane.setMinSize(400, 200);
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setVgap(5);
//        gridPane.setHgap(5);
//        gridPane.setAlignment(Pos.CENTER);
//
//        int count = 0;
//        for(int i = 0; i < ((Map) classes).size(); i++){
//
//            Object killMe = ((Map) classes).get(Integer.toString(i));
//            final String tempString = ( ((Map) killMe).get("name")).toString();
//            ImageView image = createImageView(tempString + ".png");
//            image.setOnMouseClicked(e -> {
//                Player_Controller player = Player_Controller.getInstance(true, killMe);
//                player.update();
//                ViewManager view = new ViewManager();
//                view.loadGameView();
//            });
//            gridPane.add(image, count, 0);
//            count++;
//        }
//
//        return gridPane;
//    }

    @Override
    public void register(Player_Menu_Observer observer) {
        observers.add(observer);
    }


    @Override
    public void notifyAllObservers() {
        for (Player_Menu_Observer s : observers) {
            s.update(this);
        }
    }
}
