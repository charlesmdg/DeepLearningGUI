package main;

import common.Tools;
import ihm.areas.TheScene;
import ihm.controls.DeepMenuBar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import common.Constants;

/**
 * Classe de lancement de l'application
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        DeepMenuBar menuBar =  new DeepMenuBar();
        VBox vBox = new VBox(menuBar);
        Tools.addBorder(vBox,false);

        TheScene scene = new TheScene(vBox);
        menuBar.setScene(scene);
        stage.setScene(scene);
        scene.setStage(stage);
        stage.show();
        stage.setTitle(Constants.MAIN_WINDOW_TITLE);
        stage.setOnCloseRequest(e -> {Platform.exit(); System.exit(0);});
    }
}
