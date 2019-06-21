package View.bord_views;

import View.ViewManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EndgameView {

    public EndgameView(ViewManager.endConditions avengers){

        Image image = new Image("/placeholder.png");

        switch (avengers){
            case VICTORYROYALE:
                image = new Image("/Endscreen/victoryroyale.png");
                break;
            case DEHYDRATION:
                image = new Image("/Endscreen/dehydration.png");
                break;
            case SUFFOCATION:
                image = new Image("/Endscreen/suffocation.png");
                break;
            case STERFDOORSTORM:
                image = new Image("/Endscreen/sterfdoorstorm.png");
                break;
        }

        ImageView endView = new ImageView(image);

        Group group = new Group();
        group.getChildren().add(endView);
        Scene scene = new Scene(group);
        Stage endScreen = new Stage();
        endScreen.setScene(scene);
        endScreen.setAlwaysOnTop(true);
        endScreen.setX(50);
        endScreen.setY(50);
        endScreen.show();

        endView.setOnMouseClicked(e -> {
            endScreen.close();
        });
    }

}
