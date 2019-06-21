package View.bord_views;

import Controller.Bord_Controllers.Player_Menu_Controller;
import Controller.Player_Controllers.PlayerController;
import Model.data.StaticData;
import Model.player.Player;
import javafx.scene.Group;
import javafx.scene.Node;
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
    static GridPane view2 = new GridPane();
//    static VBox vbox;
    PlayerController playerController;

    public Player_Menu_View(){
        this.player_menu_controller = Player_Menu_Controller.getInstance();
        player_menu_controller.registerObserver(this);
        player_menu_controller.registerObservert();
        playerController = PlayerController.getInstance();
    }


    public void maakView(){
        GridPane gp= new GridPane();
        GridPane classespic = new GridPane();
        StaticData staticData = StaticData.getInstance();
        Object classes = ((Map) staticData.getRoomInfo()).get("Selectable_classes");
        for(int i = 0; i < ((Map) classes).size(); i++){
            GridPane grid = new GridPane();
            Object me = ((Map) classes).get(Integer.toString(i));
            Text text = new Text(((Map) me).get("water").toString());
            text.setFont(Font.font(null, FontWeight.BOLD, 25));
            grid.add(text, 0, 0);
            text = new Text(" / ");
            text.setFont(Font.font(null, FontWeight.BOLD, 25));
            grid.add(text, 1, 0);
            text = new Text((String)((Map) me).get("maxWater").toString());
            text.setFont(Font.font(null, FontWeight.BOLD, 25));
            grid.add(text, 2, 0);
            gp.add(grid, 0, i);

            gp.setLayoutX(1135);
            gp.setLayoutY(175);
            gp.setVgap(123);
        }
        for (int i = 0; i < 4; i++) {
            Object me = ((Map) classes).get(Integer.toString(i));
            String text = ((Map) me).get("name").toString();
            System.out.println("/Players/"+((Map) me).get("name").toString()+".png");
            ImageView player_Menu_Image = new ImageView(new Image("/Players/"+text+".png"));
            classespic.add(player_Menu_Image,0,i);
            classespic.setLayoutY(80);
            classespic.setLayoutX(1110);
            classespic.setVgap(54);
            player_Menu_Image.setFitHeight(100);
            player_Menu_Image.setFitWidth(100);

            System.out.println("Gezet");
        }
        view = gp;
        view2 = classespic;
    }

    @Override
    public void update(Player_Menu_Observable sb){
        System.out.println("CHECKCHECK UPDATE");maakView();
    }

    public static GridPane getView() {
        return view;
    }

    public static GridPane getView2() {
        return view2;
    }

    private Rectangle makeRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(150);
        rectangle.setHeight(150);
        rectangle.setOpacity(0);
        return rectangle;
    }
}
