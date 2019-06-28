package Controller.Bord_Controllers;

import Model.Bord.Player_Menu;
import Model.player.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

public class Player_Menu_Controller implements Player_Menu_Observer {
    static Player_Menu_Controller player_menu_controller;
    Player_Menu player_menu;

    public Player_Menu_Controller() {
        this.player_menu = new Player_Menu();
    }

    public static Player_Menu_Controller getInstance(){
        if (player_menu_controller == null) {
            player_menu_controller = new Player_Menu_Controller();

        }
        return player_menu_controller;
    }

    public void registerObserver(Player_Menu_Observer observer){
        player_menu.register(observer);
    }

    public void begin(){
        player_menu.spelerBlokOpbouwen();
    }
    @Override
    public void update(Player_Menu_Observable sb){
        player_menu.spelerBlokOpbouwen();
    }

/*
    private Users users = new Users();
    private String character;
    private Button button1 = new Button("Archeoloog");
    private Button button2 = new Button("Klimmer");
    private Button button3 = new Button("Meteooroloog");
    private Button button4 = new Button("Navigator");
    private Button button5 = new Button("Verkenner");
    private Button button6 = new Button("Waterdrager");

    public Player_Menu_Controller(Group root, String self) {

        GridPane gridPane= new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(40, 40);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);




        switch (self){
            case "Archeoloog":
                setButtonsArcheoloog();
                break;
            case "Klimmer":
                setButtonsKlimmer();
                break;
            case "Meteooroloog":
                break;
            case "Navigator":
                break;
            case "Verkenner":
                break;
            case "Waterdrager":
                break;
        }
        double smallButton = 125.0;
        button1.setMinWidth(smallButton);
        button2.setMinWidth(smallButton);
        button3.setMinWidth(smallButton);
        button4.setMinWidth(smallButton);
        button5.setMinWidth(smallButton);
        button6.setMinWidth(smallButton);

        gridPane.add(button1, 0 ,0);
        gridPane.add(button2, 0, 1);
        gridPane.add(button3, 0 ,2);
        gridPane.add(button4, 0, 3);
        gridPane.add(button5, 0 ,4);
        gridPane.add(button6, 0, 5);
        root.getChildren().add(gridPane);



    }

    public void setButtonsArcheoloog(){
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                System.out.println("You cant give yourself water.");
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.archeoloogController.giveWater(Users.klimmerController.getPlayer(), 1);
                System.out.println("Klimmer Water: " + Users.klimmerController.getPlayer().getWater());
                System.out.println("Archeoloog Water: " + Users.archeoloogController.getPlayer().getWater());
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.archeoloogController.giveWater(Users.meteooroloogController.getPlayer(), 1);
                System.out.println("Meteooroloog Water: " + Users.meteooroloogController.getPlayer().getWater());
                System.out.println("Archeoloog Water: " + Users.archeoloogController.getPlayer().getWater());
            }
        });
        button4.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.archeoloogController.giveWater(Users.navigatorController.getPlayer(), 1);
                System.out.println("Navigator Water: " + Users.navigatorController.getPlayer().getWater());
                System.out.println("Archeoloog Water: " + Users.archeoloogController.getPlayer().getWater());
            }
        });
        button5.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.archeoloogController.giveWater(Users.verkennerController.getPlayer(), 1);
                System.out.println("Verkenner Water: " + Users.verkennerController.getPlayer().getWater());
                System.out.println("Acrcheoloog Water: " + Users.archeoloogController.getPlayer().getWater());
            }
        });
        button6.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.archeoloogController.giveWater(Users.waterdragerController.getPlayer(), 1);
                System.out.println("Waterdrager Water: " + Users.waterdragerController.getPlayer().getWater());
                System.out.println("Acrcheoloog Water: " + Users.archeoloogController.getPlayer().getWater());
            }
        });
    }

    public void setButtonsKlimmer(){
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.klimmerController.giveWater(Users.archeoloogController.getPlayer(), 1);
                System.out.println("Archeoloog Water: " + Users.archeoloogController.getPlayer().getWater());
                System.out.println("Klimmer Water: " + Users.klimmerController.getPlayer().getWater());

            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                System.out.println("You cant give yourself water.");
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.klimmerController.giveWater(Users.meteooroloogController.getPlayer(), 1);
                System.out.println("Meteooroloog Water: " + Users.meteooroloogController.getPlayer().getWater());
                System.out.println("Klimmer Water: " + Users.klimmerController.getPlayer().getWater());
            }
        });
        button4.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.klimmerController.giveWater(Users.navigatorController.getPlayer(), 1);
                System.out.println("Navigator Water: " + Users.navigatorController.getPlayer().getWater());
                System.out.println("Klimmer Water: " + Users.klimmerController.getPlayer().getWater());
            }
        });
        button5.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.klimmerController.giveWater(Users.verkennerController.getPlayer(), 1);
                System.out.println("Verkenner Water: " + Users.verkennerController.getPlayer().getWater());
                System.out.println("Klimmer Water: " + Users.klimmerController.getPlayer().getWater());
            }
        });
        button6.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                Users.klimmerController.giveWater(Users.waterdragerController.getPlayer(), 1);
                System.out.println("Waterdrager Water: " + Users.waterdragerController.getPlayer().getWater());
                System.out.println("Klimmer Water: " + Users.klimmerController.getPlayer().getWater());
            }
        });
    }



*/

}