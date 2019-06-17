package View.bord_views;

import Model.equipment.Aardekijker;
import Model.equipment.Duinkanon;
import Model.equipment.Equipment;
import Model.equipment.Jetpack;
import Model.player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import observers.PlayerObservable;
import observers.PlayerObserver;

import java.util.ArrayList;

public class EquipmentView implements PlayerObserver {

    private ArrayList<Equipment> inventory;
    private HBox hbox;

    public HBox getView() {

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

            ImageView imageView = new ImageView(equipment.getImage());
            hbox.getChildren().add(imageView);
        }

        return hbox;

    }

    public void updateView() {

    }

    public void update(PlayerObservable ob) {

        Player player = (Player) ob;
        inventory = player.getInventory();

    }

}
