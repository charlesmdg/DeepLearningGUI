package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepHBox;
import ihm.controls.DeepRadioButtonGroup;
import ihm.controls.DeepTitleLabel;
import ihm.controls.DeepVInputArea;
import javafx.scene.layout.Region;

public class PredictionTypeArea extends DeepVInputArea {
    private final DeepRadioButtonGroup radioButtonGroup;

    public PredictionTypeArea(TheScene scene) {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.PREDICTION_TYPE_AREA_HEIGHT, true);
        this.add(new DeepTitleLabel(Constants.PREDICTION_TYPE));

        radioButtonGroup = new DeepRadioButtonGroup(Constants.PREDICTION_TYPES, scene);

        DeepHBox hBox = new DeepHBox(false);
        hBox.getChildren().add(Tools.createHExpandableSpacer());
        hBox.getChildren().add(radioButtonGroup.getBox());
        hBox.getChildren().add(Tools.createHExpandableSpacer());
        Region[] regions = {hBox};

        this.fill(regions);
    }

    public void setPredictionType(String predictionType){
        this.radioButtonGroup.setSelectedRadioButton(predictionType);
    }

    public void setChildrenDisabled(boolean disabled){
        this.radioButtonGroup.setChildrenDisabled(disabled);
    }

    public String getPredictionType(){
        return this.radioButtonGroup.getSelectedRadioButton().getText();
    }

}
