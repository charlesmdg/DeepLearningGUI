package ihm.areas;

import common.Constants;
import ihm.controls.DeepHInputArea;
import ihm.controls.DeepButton;
import javafx.scene.layout.Region;

public class ButtonArea extends DeepHInputArea {
    private final DeepButton trainButton;
    private final DeepButton evaluateButton;

    public ButtonArea(TheScene scene){
        super(Constants.PARAMETER_AREA_WIDTH, Constants.BUTTON_AREA_HEIGHT, false);

        this.trainButton = new DeepButton(Constants.START_TRAINING, scene);
        this.evaluateButton = new DeepButton(Constants.EVALUATE, scene);

        Region[] regions = {trainButton, evaluateButton};
        this.fill(regions);
    }

    public void setChildrenDisabled(boolean disabled){
        this.trainButton.setDisable(disabled);
        this.evaluateButton.setDisable(disabled);
    }

    public DeepButton getTrainButton() {
        return trainButton;
    }

    public DeepButton getEvaluateButton() {
        return evaluateButton;
    }
}
