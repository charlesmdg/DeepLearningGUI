package ihm.controls;

import common.Tools;
import common.Constants;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DeepLabeledRegion extends DeepHBox {
    public DeepLabeledRegion(String labelText, Region region){
        super(false);
        DeepLabel label = new DeepLabel(labelText + Constants.SPACED_COLON);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().add(label);
        this.getChildren().add(Tools.createHExpandableSpacer());
        HBox hbox = new HBox();
        hbox.getChildren().add(region);
        hbox.getChildren().add(Tools.createHExpandableSpacer());
        Tools.setWidth(hbox, Constants.INPUT_FIELD_MAX_WIDTH);
        this.getChildren().add(hbox);
    }
}
