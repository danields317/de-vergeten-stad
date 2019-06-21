package View.bord_views;

import Controller.Equipment_Controllers.EquipmentController;
import Controller.Player_Controllers.PlayerController;
import Model.equipment.Equipment;
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
        equipmentController.registerObserver(this);
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

        ImageView switchknopRight = new ImageView(new Image("Buttons/buttonEqRight.png"));
        ImageView switchknopLeft = new ImageView(new Image("/Buttons/buttonEqLeft.png"));

        switchknopRight.getStyleClass().add("eqButtonRight");
        switchknopLeft.getStyleClass().add("eqButtonLeft");

        aardekijkerStack = new StackPane(aardekijker, aardekijkerLabel);
        duinkanonStack = new StackPane(duinkanon, duinkanonLabel);
        jetpackStack = new StackPane(jetpack, jetpackLabel, switchknopRight);
        tijdschakelaarStack = new StackPane(tijdschakelaar, tijdschakelaarLabel, switchknopLeft);
        waterreserveStack = new StackPane(waterreserve, waterreserveLabel);
        zonneschildStack = new StackPane(zonneschild, zonneschildLabel);

        aardekijkerStack.getStyleClass().add("kaart");
        duinkanonStack.getStyleClass().add("kaart");
        jetpackStack.getStyleClass().add("kaart");
        tijdschakelaarStack.getStyleClass().add("kaart");
        waterreserveStack.getStyleClass().add("kaart");
        zonneschildStack.getStyleClass().add("kaart");

        hboxUp = new HBox(aardekijkerStack, duinkanonStack, jetpackStack);
        hboxUp.setSpacing(7);
        hboxDown = new HBox(tijdschakelaarStack, waterreserveStack, zonneschildStack);
        hboxDown.setSpacing(7);
        upBox = new VBox(hboxUp);
        downBox = new VBox(hboxDown);

        group = new StackPane(downBox, upBox);
        downBox.setOpacity(0);
        downBox.setDisable(true);

        group.setLayoutX(721);
        group.setLayoutY(677);


        switchknopRight.setOnMouseClicked(e -> {
            stelKnoppenIn(upBox, downBox);
        });

        switchknopLeft.setOnMouseClicked(e -> {
            stelKnoppenIn(downBox, upBox);
        });

        aardekijkerStack.setOnMouseClicked(e -> {
            if (checkInventory(Equipment.EquipmentKaarten.AARDEKIJKER)){
                equipmentController.setAardekijkerStatus();
                deselectKaarten();
                aardekijkerStack.getStyleClass().add("geselecteerdeKaart");
            }
        });
        duinkanonStack.setOnMouseClicked(e -> {
            if (checkInventory(Equipment.EquipmentKaarten.DUINKANON)){
                equipmentController.setDuinkanonStatus();
                deselectKaarten();
                duinkanonStack.getStyleClass().add("geselecteerdeKaart");
            }
        });
        jetpackStack.setOnMouseClicked(e -> {
            if (checkInventory(Equipment.EquipmentKaarten.JETPACK)){
                equipmentController.setJetpackStatus();
                deselectKaarten();
                jetpackStack.getStyleClass().add("geselecteerdeKaart");
            }
        });
        tijdschakelaarStack.setOnMouseClicked(e -> {
            if (checkInventory(Equipment.EquipmentKaarten.TIJDSCHAKELAAR)){
                equipmentController.gebruikTijdschakelaar();
                deselectKaarten();
                tijdschakelaarStack.getStyleClass().add("geselecteerdeKaart");
            }
        });
        waterreserveStack.setOnMouseClicked(e -> {
            if (checkInventory(Equipment.EquipmentKaarten.WATERRESERVE)){
                equipmentController.gebruikWaterreserve();
                deselectKaarten();
                waterreserveStack.getStyleClass().add("geselecteerdeKaart");
            }
        });
        zonneschildStack.setOnMouseClicked(e -> {
            if (checkInventory(Equipment.EquipmentKaarten.ZONNESCHILD)){
                equipmentController.gebruikZonneschild();
                deselectKaarten();
                zonneschildStack.getStyleClass().add("geselecteerdeKaart");
            }
        });

        return group;
    }

    private boolean checkInventory(Equipment.EquipmentKaarten kaart){
        ArrayList<Equipment> inventory = PlayerController.getInstance().getPlayer().getInventory();
        for (Equipment eq : inventory){
            if (eq.getEquipmentType().equals(kaart)){
                return true;
            }
        }
        return false;
    }

    private void deselectKaarten() {
        aardekijkerStack.getStyleClass().remove("geselecteerdeKaart");
        duinkanonStack.getStyleClass().remove("geselecteerdeKaart");
        jetpackStack.getStyleClass().remove("geselecteerdeKaart");
        tijdschakelaarStack.getStyleClass().remove("geselecteerdeKaart");
        waterreserveStack.getStyleClass().remove("geselecteerdeKaart");
        zonneschildStack.getStyleClass().remove("geselecteerdeKaart");
    }

    public void stelKnoppenIn(VBox vBox1, VBox vBox2){
        vBox1.setOpacity(0);
        vBox1.setDisable(true);
        vBox2.setOpacity(1);
        vBox2.setDisable(false);
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

    public void update(PlayerObservable ob) {

        Player player = (Player) ob;
        inventory = player.getInventory();
        updateInventory(inventory);
        deselectKaarten();

    }

}
