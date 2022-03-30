package ihm.controls;

import common.Constants;
import common.Tools;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class DeepInputArea extends DeepPane {
    protected Pane box;
    public DeepInputArea(Pane box, double width, double height) {
        super(false);
        this.getChildren().add(box);
        this.box = box;
        Tools.setSize(this.box, width, height);
        this.box.setPadding(new Insets(Constants.AREA_PADDING));
    }

    protected void add(Region region){
        this.box.getChildren().add(region);
    }

    public Pane getBox() {
        return box;
    }

    protected void fill(String[] labelTexts,  Region[] regions) {
        int length= labelTexts.length;

        for (int ii = 0 ; ii<length ; ii++) {
            this.add(this.createExpandableSpacer());
            this.add(new DeepLabeledRegion(labelTexts[ii], regions[ii]));
        }
        this.add(this.createExpandableSpacer());
    }

    protected abstract Region createExpandableSpacer();

    protected void fill(Region[] regions) {

        for (Region region: regions) {
            this.add(this.createExpandableSpacer());
            this.add(region);
        }
        this.add(this.createExpandableSpacer());
    }

}
