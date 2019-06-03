package Controller;

import Controller.Bord_Controllers.Bord_Controller;
import Controller.Player_Controllers.*;
import Model.player.Users;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import View.*;

public class Controller/* extends Application  */{
/*
    private int screenWidth = 1600;
    private int screenHeight = 900;
    private Users users = new Users();
    private String self = "Klimmer";
    private Bord_Controller bordController;

    public Controller(){
        Users.archeoloogController = new Archeoloog_Controller("a");
        Users.klimmerController = new Klimmer_Controller("b");
        Users.meteooroloogController = new Meteooroloog_Controller("c");
        Users.navigatorController = new Navigator_controller("d");
        Users.verkennerController = new Verkenner_Controller("e");
        Users.waterdragerController = new Waterdrager_Controller("f");
        System.out.println("Meteooroloog Water: " + Users.meteooroloogController.getPlayer().getWater());
        System.out.println("Acrcheoloog Water: " + Users.archeoloogController.getPlayer().getWater());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Bord bord = new Bord();
        Canvas canvas = new Canvas(screenWidth, screenHeight);
        Group root = new Group(canvas);
        Scene scene = new Scene(root);
        //bordController = new Bord_Controller();



        primaryStage.setTitle("De Vergeten Stad");
        primaryStage.setScene(scene);
        primaryStage.show();

        ////////////////////////// Main Loop //////////////////////////
        new AnimationTimer() {
            @Override
            public void handle(long now) {

                GraphicsContext gc = canvas.getGraphicsContext2D();
                bord.render(gc);

            }
        }.start();





    }
    */
}
