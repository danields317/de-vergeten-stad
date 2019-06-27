package View.bord_views;

import Controller.Bord_Controllers.Player_Menu_Controller;
import Controller.Player_Controllers.PlayerController;
import Model.player.Player;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

public class Player_Menu_View implements Player_Menu_Observer {
    Player_Menu_Controller player_menu_controller;
    //static GridPane view = new GridPane();
    static VBox vbox;
    PlayerController playerController;

    public Player_Menu_View(){
        this.player_menu_controller = Player_Menu_Controller.getInstance();
        player_menu_controller.registerObserver(this);
        playerController = PlayerController.getInstance();
    }


    public void maakView(Player_Menu_Observable sb){
        /*GridPane gp = new GridPane();
        for(int i = 0; i < 4; i++) {
            ImageView playerMenuImage = new ImageView(new Image("/Player_Menu/" + i + ".png"));
            playerMenuImage.setFitWidth(100);
            playerMenuImage.setFitHeight(100);
            gp.add(playerMenuImage, 0, i);

            playerMenuImage.setOnMouseClicked(e -> {

            });
        }

        gp.setLayoutX(1175);
        gp.setLayoutY(302);
        view = gp;*/


        StackPane archeoloogGroup = new StackPane(makeRectangle());
        StackPane klimmerGroup = new StackPane(makeRectangle());
        StackPane verkennerGroup = new StackPane(makeRectangle());
        StackPane waterdragerGroup = new StackPane(makeRectangle());

        archeoloogGroup.setOnMouseClicked(e->playerController.giveWater("Archeoloog", 1));
        klimmerGroup.setOnMouseClicked(e->playerController.giveWater("Klimmer", 1));
        verkennerGroup.setOnMouseClicked(e->playerController.giveWater("Verkenner", 1));
        waterdragerGroup.setOnMouseClicked(e->playerController.giveWater("Waterdrager", 1));

        archeoloogGroup.getStyleClass().add("playermenu");
        klimmerGroup.getStyleClass().add("playermenu");
        verkennerGroup.getStyleClass().add("playermenu");
        waterdragerGroup.getStyleClass().add("playermenu");

        vbox = new VBox(archeoloogGroup, klimmerGroup, verkennerGroup, waterdragerGroup);
        vbox.setPrefSize(150, 610);
        vbox.setSpacing(4);
        vbox.setLayoutX(1085);
        vbox.setLayoutY(60);

    }

    @Override
    public void update(Player_Menu_Observable sb){
        maakView(sb);
    }

    public static VBox getView() {
        return vbox;
    }

    private Rectangle makeRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(150);
        rectangle.setHeight(150);
        rectangle.setOpacity(0);
        return rectangle;
    }
}
