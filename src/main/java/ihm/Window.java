package ihm;

import ihm.MScene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import common.Constants;

public class Window extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        MScene scene = new MScene(root, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.setTitle(Constants.MAIN_WINDOW_TITLE);
    }
}
