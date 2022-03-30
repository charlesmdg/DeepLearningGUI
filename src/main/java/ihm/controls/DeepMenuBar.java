package ihm.controls;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import common.Constants;

public class DeepMenuBar extends MenuBar {
    public DeepMenuBar(){
        super();
        Menu fileMenu = new Menu(Constants.FILE);
        Menu editMenu = new Menu(Constants.EDIT);
        Menu helpMenu = new Menu(Constants.HELP);
    }
}
