package main;

import ihm.controls.MHBox;
import ihm.app.MScene;
import javafx.application.Application;
import javafx.stage.Stage;
import common.Constants;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MScene scene = new MScene(new MHBox(false));
        stage.setScene(scene);
        stage.show();
        stage.setTitle(Constants.MAIN_WINDOW_TITLE);
    }
}
