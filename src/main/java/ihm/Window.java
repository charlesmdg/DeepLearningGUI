package ihm;

import javafx.application.Application;
import javafx.stage.Stage;
import common.Constants;

public class Window extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MScene scene = new MScene(new MHBox(false), Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.setTitle(Constants.MAIN_WINDOW_TITLE);
    }
}
