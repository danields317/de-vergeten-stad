package View.bord_views;

import Controller.Bord_Controllers.Player_Menu_Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

public class Player_Menu_View implements Player_Menu_Observer {
    Player_Menu_Controller player_menu_controller;
    static GridPane view = new GridPane();

    public Player_Menu_View(){
        this.player_menu_controller = Player_Menu_Controller.getInstance();
        player_menu_controller.registerObserver(this);
    }


    public void maakView(Player_Menu_Observable sb){
        GridPane gp = new GridPane();
        for(int i = 0; i < 4; i++){
                ImageView playerMenuImage = new ImageView(new Image("/Player_Menu/"+ i +".png"));
                playerMenuImage.setFitWidth(100);
                playerMenuImage.setFitHeight(100);
                gp.add(playerMenuImage, 0, i);
        }
        gp.setLayoutX(1175);
        gp.setLayoutY(302);
        view = gp;
    }

    @Override
    public void update(Player_Menu_Observable sb){
        maakView(sb);
    }

    public static GridPane getView() {
        return view;
    }
}
