package ihm.controls;

import common.Tools;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DeepHBox extends HBox {
    public DeepHBox(boolean bordered){
        super();
        Tools.addBorder(this, bordered);
        this.setAlignment(Pos.CENTER);
    }

    public void add(Region region){
        this.getChildren().add(region);
    }
}
