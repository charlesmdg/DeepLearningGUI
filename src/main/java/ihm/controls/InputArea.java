package ihm.controls;

import common.Constants;
import common.Tools;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class InputArea {
    protected Pane box;

    public InputArea(Pane box, double width, double height) {
        this.box = box;
        Tools.setSize(this.box, width, height);
    }

    public Pane getBox() {
        return box;
    }

    protected void fill(String[] labelTexts,  Region[] regions) {
        int length= labelTexts.length;

        for (int ii = 0 ; ii<length ; ii++) {
            this.box.getChildren().add(new LabeledRegion(labelTexts[ii], regions[ii]));
        }
    }

    protected void fill(Region[] regions) {

        for (Region region: regions) {
            this.box.getChildren().add(region);
        }
    }

}
