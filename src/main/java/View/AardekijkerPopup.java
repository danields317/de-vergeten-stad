package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AardekijkerPopup {

    Stage stage;
    Scene scene;
    Group group;

    public AardekijkerPopup(Image tile){
        ImageView discoTile = new ImageView(tile);
        discoTile.setFitHeight(200);
        discoTile.setFitWidth(200);
        group = new Group();
        group.getChildren().add(discoTile);
        stage = new Stage();
        scene = new Scene(group);

        stage.setScene(scene);
        stage.setTitle("Aardekijker");
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image("/placeholder.png"));
        stage.show();

    }

}
