package ihm.controls;

import common.Tools;
import ihm.areas.TheScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import common.Constants;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

public class DeepMenuBar extends MenuBar {

    ArrayList<DeepMenuItem> menuItems = new ArrayList<>();

    public DeepMenuBar(){
        super();
        Tools.setStyle(this);

        Menu fileMenu = new Menu(Constants.FILE);
//        Menu editMenu = new Menu(Constants.EDIT);
//        Menu helpMenu = new Menu(Constants.HELP);
        this.getMenus().add(fileMenu);
//        this.getMenus().add(editMenu);
//        this.getMenus().add(helpMenu);

        DeepMenuItem newMenuItem = new DeepMenuItem(Constants.NEW);
        DeepMenuItem openMenuItem = new DeepMenuItem(Constants.OPEN);
        DeepMenuItem saveMenuItem = new DeepMenuItem(Constants.SAVE);
        DeepMenuItem saveAsMenuItem = new DeepMenuItem(Constants.SAVE_AS);
        fileMenu.getItems().add(newMenuItem);
        fileMenu.getItems().add(openMenuItem);
        fileMenu.getItems().add(saveMenuItem);
        fileMenu.getItems().add(saveAsMenuItem);
        menuItems.add(newMenuItem);
        menuItems.add(openMenuItem);
        menuItems.add(saveMenuItem);
        menuItems.add(saveAsMenuItem);
    }

    public void setScene(MenuItemListener menuItemListener) {
        for (DeepMenuItem menuItem:menuItems){
            menuItem.setMenuItemListener(menuItemListener);
        }
    }
}
