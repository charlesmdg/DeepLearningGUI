package ihm;

import common.Tools;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Panel {
    protected Pane box;

    public Panel() {
    }

    public void add(Region region) {
        this.box.getChildren().add(region);
    }

    public void setSize(double width, double heigth) {
        Tools.setSize(this.box, width, heigth);
    }

    public Pane getBox() {
        return box;
    }
}
