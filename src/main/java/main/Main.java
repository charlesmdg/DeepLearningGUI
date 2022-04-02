package main;

import ihm.controls.DeepHBox;
import ihm.areas.TheScene;
import javafx.application.Application;
import javafx.stage.Stage;
import common.Constants;

/**
 * Classe de lancement de l'application
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        TheScene scene = new TheScene(new DeepHBox(false));
        stage.setScene(scene);
        scene.setStage(stage);
        stage.show();
        stage.setTitle(Constants.MAIN_WINDOW_TITLE);
    }
}
