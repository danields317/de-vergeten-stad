package View;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class EindigBeurtView {

    public Button maakEindigbeurtKnop(){

        Button eindigBeurt = new Button("Beëindig\nBeurt");

        eindigBeurt.setPrefSize(152,57);
        eindigBeurt.setLayoutX(1392);
        eindigBeurt.setLayoutY(732);

        return eindigBeurt;
    }

}
