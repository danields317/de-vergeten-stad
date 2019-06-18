package View.bord_views;

import Controller.Equipment_Controllers.EquipmentController;
import Model.equipment.Aardekijker;
import Model.equipment.Duinkanon;
import Model.equipment.Equipment;
import Model.equipment.Jetpack;
import Model.player.Player;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import observers.PlayerObservable;
import observers.PlayerObserver;

import java.util.ArrayList;

public class EquipmentView implements PlayerObserver {

    EquipmentController equipmentController;

    private ArrayList<Equipment> inventory;
    private StackPane group;
    private VBox upBox;
    private VBox downBox;

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




    public StackPane createEquipmentView(){
        equipmentController = EquipmentController.getInstance();
        ImageView aardekijker = new ImageView(new Image("/Equipment/Ground_Watcher.png"));
        aardekijker.setFitWidth(136);
        aardekijker.setFitHeight(192);
        ImageView duinkanon = new ImageView(new Image("/Equipment/Dune_Cannon.png"));
        duinkanon.setFitWidth(136);
        duinkanon.setFitHeight(192);
        ImageView jetpack = new ImageView(new Image("/Equipment/Jetpack.png"));
        jetpack.setFitWidth(136);
        jetpack.setFitHeight(192);
        ImageView tijdschakelaar = new ImageView(new Image("/Equipment/Time_Switch.png"));
        tijdschakelaar.setFitWidth(136);
        tijdschakelaar.setFitHeight(192);
        ImageView waterreserve = new ImageView(new Image("/Equipment/Water_Reserve.png"));
        waterreserve.setFitWidth(136);
        waterreserve.setFitHeight(192);
        ImageView zonneschild = new ImageView(new Image("/Equipment/Sun_Shield.png"));
        zonneschild.setFitWidth(136);
        zonneschild.setFitHeight(192);

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



        Button switchknopDown = new Button("1/2");
        Button switchknopUp = new Button("2/2");

        hboxUp = new HBox(aardekijkerStack, duinkanonStack, jetpackStack);
        hboxUp.setSpacing(7);
        hboxDown = new HBox(tijdschakelaarStack, waterreserveStack, zonneschildStack);
        hboxDown.setSpacing(7);
        upBox = new VBox(hboxUp, switchknopDown);
        downBox = new VBox(hboxDown, switchknopUp);


        group = new StackPane(downBox, upBox);
        downBox.setOpacity(0);
        downBox.setDisable(true);

        group.setLayoutX(721);
        group.setLayoutY(677);


        switchknopDown.setOnMouseClicked(e -> {
            upBox.setOpacity(0);
            upBox.setDisable(true);
            downBox.setOpacity(1);
            downBox.setDisable(false);
        });

        switchknopUp.setOnMouseClicked(e -> {
            downBox.setOpacity(0);
            downBox.setDisable(true);
            upBox.setOpacity(1);
            upBox.setDisable(false);
        });

        aardekijkerStack.setOnMouseClicked(e -> equipmentController.setAardekijkerStatus());
        duinkanonStack.setOnMouseClicked(e -> equipmentController.setDuinkanonStatus());
        jetpackStack.setOnMouseClicked(e -> equipmentController.setJetpackStatus());
        tijdschakelaarStack.setOnMouseClicked(e -> equipmentController.gebruikTijdschakelaar());
        waterreserveStack.setOnMouseClicked(e -> equipmentController.gebruikWaterreserve());
        zonneschildStack.setOnMouseClicked(e -> equipmentController.gebruikZonneschild());

        return group;
    }

    public StackPane getUitrusting(){
        if (group == null){
            createEquipmentView();
        }
        return group;
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
