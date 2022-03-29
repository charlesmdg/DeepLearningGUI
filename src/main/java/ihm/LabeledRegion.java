package ihm;

import common.Tools;
import common.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class LabeledRegion extends MHBox {
    public LabeledRegion(String labelText, Region region){
        super(false);
        MLabel label = new MLabel(labelText + Constants.SPACED_COLUMN);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().add(label);
        this.getChildren().add(Tools.createHorizontalSpacer(10));
        this.getChildren().add(region);
    }
}
