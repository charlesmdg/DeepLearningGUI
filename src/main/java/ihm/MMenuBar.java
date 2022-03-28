package ihm;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import common.Constants;

public class MMenuBar extends MenuBar {
    public MMenuBar(){
        super();
        Menu fileMenu = new Menu(Constants.FILE);
        Menu editMenu = new Menu(Constants.EDIT);
        Menu helpMenu = new Menu(Constants.HELP);
    }
}
