package View.bord_views;

import Controller.Equipment_Controllers.Equipment_Controller;
import Controller.Player_Controllers.Archeoloog_Controller;
import Controller.Player_Controllers.Klimmer_Controller;
import Controller.Player_Controllers.Meteooroloog_Controller;
import Controller.Player_Controllers.Navigator_Controller;
import Controller.Player_Controllers.Verkenner_Controller;
import Controller.Player_Controllers.Waterdrager_Controller;
import Controller.Player_Controllers.Player_Controller;
import Model.equipment.Equipment;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class SpelerView {

    /*public enum spelerKlasse {ARCHEOLOOG, KLIMMER, METEOOROLOOG, NAVIGATOR, VERKENNER, WATERDRAGER };

    static HBox inventoryView;
    Player_Controller spelerController;

    public SpelerView(spelerKlasse klasse, String nickname) {

        switch(klasse) {
            case ARCHEOLOOG:
                spelerController = new Archeoloog_Controller(nickname);
                break;
            case KLIMMER:
                spelerController = new Klimmer_Controller(nickname);
                break;
            case METEOOROLOOG:
                spelerController = new Meteooroloog_Controller(nickname);
                break;
            case NAVIGATOR:
                spelerController = new Navigator_Controller(nickname);
                break;
            case VERKENNER:
                spelerController = new Verkenner_Controller(nickname);
                break;
            case WATERDRAGER:
                spelerController = new Waterdrager_Controller(nickname);
                break;
        }

    }

    public HBox loadInventory() {

        inventoryView = new HBox();

        updateInventory();

        return inventoryView;

    }

    public void updateInventory() {

        inventoryView.getChildren().removeAll();

        for (Equipment_Controller equipmentController: spelerController.getInventory()) {

            ImageView imageView = new ImageView(equipmentController.getImage());
            inventoryView.getChildren().add(imageView);

            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    //equipmentController.gebruikEquipment();

                    event.consume();
                }
            });

        }

    }*/

}
