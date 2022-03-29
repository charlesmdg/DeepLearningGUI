package ihm.app;

import common.Constants;
import ihm.controls.MComboBox;
import ihm.controls.MRadioGroup;
import ihm.controls.MTextField;
import ihm.controls.VInputArea;
import javafx.scene.layout.Region;

public class PredictionTypeArea extends VInputArea {

    MRadioGroup radioGroup = new MRadioGroup(Constants.PREDICTION_TYPES);

    public PredictionTypeArea() {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.PREDICTION_TYPE_AREA_HEIGHT, true);

        Region[] regions = {radioGroup.getBox()};

        this.fill(regions);
    }

}
