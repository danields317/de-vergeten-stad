package View.bord_views;

import Model.equipment.Aardekijker;
import Model.equipment.Duinkanon;
import Model.equipment.Equipment;
import Model.equipment.Jetpack;
import Model.player.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import observers.PlayerObservable;
import observers.PlayerObserver;

import java.util.ArrayList;

public class EquipmentView implements PlayerObserver {

    private ArrayList<Equipment> inventory;
    private VBox box;

    private HBox hboxUp;
    private HBox hboxDown;

    private StackPane aardekijkerStack;
    private StackPane duinkanonStack;
    private StackPane jetpackStack;
    private StackPane tijdschakelaarStack;
    private StackPane waterreserveStack;
    private StackPane zonneschildStack;

    Label aardekijkerLabel;
    Label duinkanonLabel;
    Label jetpackLabel;
    Label tijdschakelaarLabel;
    Label waterreserveLabel;
    Label zonneschildLabel;




    public VBox createEquipmentView(){
        ImageView aardekijker = new ImageView(new Image("/Equipment/Ground_Watcher.png"));
        ImageView duinkanon = new ImageView(new Image("/Equipment/Dune_Cannon.png"));
        ImageView jetpack = new ImageView(new Image("/Equipment/Jetpack.png"));
        ImageView tijdschakelaar = new ImageView(new Image("/Equipment/Time_Switch.png"));
        ImageView waterreserve = new ImageView(new Image("/Equipment/Water_Reserve.png"));
        ImageView zonneschild = new ImageView(new Image("/Equipment/Sun_Shield.png"));

        aardekijkerLabel = new Label();
        duinkanonLabel = new Label();
        jetpackLabel = new Label();
        tijdschakelaarLabel = new Label();
        waterreserveLabel = new Label();
        zonneschildLabel = new Label();

        aardekijkerStack = new StackPane(aardekijker, aardekijkerLabel);
        duinkanonStack = new StackPane(duinkanon, duinkanonLabel);
        jetpackStack = new StackPane(jetpack, jetpackLabel);
        tijdschakelaarStack = new StackPane(tijdschakelaar, tijdschakelaarLabel);
        waterreserveStack = new StackPane(waterreserve, waterreserveLabel);
        zonneschildStack = new StackPane(zonneschild, zonneschildLabel);



        hboxUp = new HBox(aardekijker, duinkanon, jetpack);
        hboxDown = new HBox(tijdschakelaar, waterreserve, zonneschild);
        box = new VBox(hboxUp, hboxDown);

        return box;
    }

    public VBox getUitrusting(){
        return box;
    }

    public void updateInventory(ArrayList<Equipment> inventory){
        int aardekijkerTeller = 0;
        int duinkanonTeller = 0;
        int jetpackTeller = 0;
        int tijdschakelaarTeller = 0;
        int waterreserveTeller = 0;
        int zonneschildTeller = 0;

        for(Equipment equipment : inventory){
            switch (equipment.getEquipmentType()){
                case AARDEKIJKER:
                    aardekijkerTeller++;
                    break;
                case DUINKANON:
                    duinkanonTeller++;
                    break;
                case JETPACK:
                    jetpackTeller++;
                    break;
                case TIJDSCHAKELAAR:
                    tijdschakelaarTeller++;
                    break;
                case WATERRESERVE:
                    waterreserveTeller++;
                    break;
                case ZONNESCHILD:
                    zonneschildTeller++;
                    break;
            }
        }
        aardekijkerLabel.setText(aardekijkerTeller +"");
        duinkanonLabel.setText(duinkanonTeller + "");
        jetpackLabel.setText(jetpackTeller + "");
        tijdschakelaarLabel.setText(tijdschakelaarTeller + "");
        waterreserveLabel.setText(waterreserveTeller + "");
        zonneschildLabel.setText(zonneschildTeller + "");
    }



    /*public HBox getView() {

        inventory = new ArrayList<Equipment>();
        inventory.add(new Aardekijker());
        inventory.add(new Jetpack());
        inventory.add(new Duinkanon());

        StackPane stackPane_aardekijker = new StackPane();
        StackPane stackPane_duinkanon = new StackPane();
        StackPane stackPane_jetpack = new StackPane();
        StackPane stackPane_tijdschakelaar = new StackPane();
        StackPane stackPane_waterreserve = new StackPane();
        StackPane stackPane_zonneschild = new StackPane();

        hbox = new HBox( stackPane_aardekijker, stackPane_duinkanon, stackPane_jetpack, stackPane_tijdschakelaar, stackPane_waterreserve, stackPane_zonneschild );

        for (Equipment equipment : inventory) {
            switch(equipment.getEquipmentType())
            {
                case AARDEKIJKER:
                    break;
                case DUINKANON:
                    break;
                case JETPACK:
                    break;
                case TIJDSCHAKELAAR:
                    break;
                case WATERRESERVE:
                    break;
                case ZONNESCHILD:
                    break;
            }

            //ImageView imageView = new ImageView(equipment.getImage());
            //hbox.getChildren().add(imageView);
        }

        return hbox;

    }*/

    public void update(PlayerObservable ob) {

        Player player = (Player) ob;
        inventory = player.getInventory();
        updateInventory(inventory);

    }

}
