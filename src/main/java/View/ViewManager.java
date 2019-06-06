package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewManager {

    static Stage primaryStage;
    String kaart = "/gamescreenempty.png";
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    BordView bordAchtergrond = new BordView();
    Image achtergrond = bordAchtergrond.maakAchtergrond();

    ActieKnoppenView AKV = new ActieKnoppenView();
    GridPane actieKnoppen = AKV.maakActieKnoppen();

    public ViewManager(){
        loadPrimaryStageWithGridPane();
    }


    private void loadPrimaryStageWithGridPane() {
        try {
            Canvas canvas = new Canvas(width, height);
            Group group = new Group(canvas);
            Scene scene = new Scene(group);
            primaryStage.setScene(scene);
            primaryStage.setTitle("WELCOME TO THE GAME");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(achtergrond, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            /*Bord_Controller b = new Bord_Controller(root);*/
        } catch(Exception e) {
            e.printStackTrace();
            e.printStackTrace();
        }
    }

}
