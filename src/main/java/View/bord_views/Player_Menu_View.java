package View.bord_views;

import Controller.Bord_Controllers.Player_Menu_Controller;
import Controller.Player_Controllers.PlayerController;
import Model.data.StaticData;
import Model.player.Player;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

import java.util.Map;

public class Player_Menu_View implements Player_Menu_Observer {
    Player_Menu_Controller player_menu_controller;
    static GridPane view = new GridPane();
    static VBox vbox;
    PlayerController playerController;

    public Player_Menu_View(){
        this.player_menu_controller = Player_Menu_Controller.getInstance();
        player_menu_controller.registerObserver(this);
        playerController = PlayerController.getInstance();
    }


    public void maakView(){
        StaticData staticData = StaticData.getInstance();
        System.out.println("SpelerBlokOpbouwen : Player_Menu");
        Object roomInfo = ((Map)staticData.getRoomInfo()).get("Selectable_classes");
        System.out.println(roomInfo);
        GridPane gp = new GridPane();
        for (int i = 0; i < 4; i++) {
            GridPane inLoop = new GridPane();

            Map newGrid = (Map)((Map) roomInfo).get(Integer.toString( i));
            inLoop.add(new Text(newGrid.get("water").toString()),0,2);
            inLoop.add(new Text(" / "),1,2);
            inLoop.add(new Text(newGrid.get("maxWater").toString()),2,2);

            gp.add(inLoop, 0, i);
        }
        gp.setLayoutX(1147);
        gp.setLayoutY(185);
        gp.setVgap(137);
        view = gp;
//        for(int i = 0; i < 4; i++) {
//            ImageView playerMenuImage = new ImageView(new Image("/Player_Menu/" + i + ".png"));
//            playerMenuImage.setFitWidth(100);
//            playerMenuImage.setFitHeight(100);
//            gp.add(playerMenuImage, 0, i);
//
//            playerMenuImage.setOnMouseClicked(e -> {
//
//            });
//        }

//        gp.setLayoutX(1175);
//        gp.setLayoutY(302);
//        view = gp;
//
//
//        StackPane archeoloogGroup = new StackPane(makeRectangle());
//        StackPane klimmerGroup = new StackPane(makeRectangle());
//        StackPane verkennerGroup = new StackPane(makeRectangle());
//        StackPane waterdragerGroup = new StackPane(makeRectangle());
//
//        archeoloogGroup.setOnMouseClicked(e->playerController.giveWater(Player.SpelerKlassen.ARCHEOLOOG, 1));
//        klimmerGroup.setOnMouseClicked(e->playerController.giveWater(Player.SpelerKlassen.KLIMMER, 1));
//        verkennerGroup.setOnMouseClicked(e->playerController.giveWater(Player.SpelerKlassen.VERKENNER, 1));
//        waterdragerGroup.setOnMouseClicked(e->playerController.giveWater(Player.SpelerKlassen.WATERDRAGER, 1));
//
//        archeoloogGroup.getStyleClass().add("playermenu");
//        klimmerGroup.getStyleClass().add("playermenu");
//        verkennerGroup.getStyleClass().add("playermenu");
//        waterdragerGroup.getStyleClass().add("playermenu");
//
//        vbox = new VBox(archeoloogGroup, klimmerGroup, verkennerGroup, waterdragerGroup);
//        vbox.setPrefSize(150, 610);
//        vbox.setSpacing(4);
//        vbox.setLayoutX(1085);
//        vbox.setLayoutY(60);

    }

    @Override
    public void update(Player_Menu_Observable sb){
        maakView();
    }

    public static GridPane getView() {
        return view;
    }

    private Rectangle makeRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(150);
        rectangle.setHeight(150);
        rectangle.setOpacity(0);
        return rectangle;
    }
}
