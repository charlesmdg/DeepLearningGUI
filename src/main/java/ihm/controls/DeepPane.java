package ihm.controls;

import common.Tools;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class DeepPane extends Pane {
    public DeepPane(boolean bordered){
        super();
        Tools.addBorder(this, bordered);
    }

    public void draw(Shape shape) {
        this.getChildren().add(shape);
    }

}
