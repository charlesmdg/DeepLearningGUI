package ihm.controls;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class DeepMenuItem extends MenuItem {

    MenuItemListener menuItemListener;

    public DeepMenuItem(String text){
        super(text);
        this.setOnAction(
                actionEvent -> DeepMenuItem.this.menuItemListener.menuItemclicked(DeepMenuItem.this.getText()));
    }

    public void setMenuItemListener(MenuItemListener menuItemListener) {
        this.menuItemListener = menuItemListener;
    }
}
