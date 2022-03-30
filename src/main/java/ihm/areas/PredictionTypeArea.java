package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepHBox;
import ihm.controls.DeepRadioButtonGroup;
import ihm.controls.DeepVBox;
import ihm.controls.DeepVInputArea;
import javafx.scene.layout.Region;

public class PredictionTypeArea extends DeepVInputArea {

    DeepRadioButtonGroup radioButtonGroup = new DeepRadioButtonGroup(Constants.PREDICTION_TYPES);

    public PredictionTypeArea() {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.PREDICTION_TYPE_AREA_HEIGHT, true);

        DeepHBox hBox = new DeepHBox(false);
        hBox.getChildren().add(Tools.createHExpandableSpacer());
        hBox.getChildren().add(radioButtonGroup.getBox());
        hBox.getChildren().add(Tools.createHExpandableSpacer());
        Region[] regions = {hBox};

        this.fill(regions);
    }

    public String getPredictionType(){
        return this.radioButtonGroup.getSelectedRadioButton().getText();
    }

}
