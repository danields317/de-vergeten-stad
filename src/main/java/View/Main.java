package View;

import Controller.Controller;
import firebase.FirebaseService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class  Main {
	
	private int screenWidth = 1600;
	private int screenHeight = 900;
    public static void main(String[] args) {
        ViewManager viewManager = new ViewManager();
        Application.launch(ViewManager.class, args);
    }
}
