package ihm.controls;

import common.Tools;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class DeepPane extends Pane {
    public DeepPane(boolean bordered){
        super();
        if(bordered)
            Tools.addBorder(this);
    }

    public void draw(Shape shape) {
        this.getChildren().add(shape);
    }

}
