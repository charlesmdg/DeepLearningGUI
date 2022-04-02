package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepHInputArea;
import ihm.controls.DeepButton;
import javafx.scene.layout.Region;

public class ButtonArea extends DeepHInputArea {
    private final DeepButton trainButton;
    private final DeepButton cancelTrainingButton;

    public ButtonArea(TheScene scene){
        super(Constants.PARAMETER_AREA_WIDTH, Constants.BUTTON_AREA_HEIGHT, false);

        this.cancelTrainingButton = new DeepButton(Constants.CANCEL_TRAINING, scene);
        Tools.setSize(this.cancelTrainingButton, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        this.trainButton = new DeepButton(Constants.START_TRAINING, scene);
        Tools.setSize(this.trainButton, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);

        Region[] regions = {this.cancelTrainingButton, this.trainButton};
        this.fill(regions);
    }

    public void setChildrenDisabled(boolean disabled){
        this.trainButton.setDisable(disabled);
        this.cancelTrainingButton.setDisable(disabled);
    }

    public DeepButton getTrainButton() {
        return this.trainButton;
    }

    public DeepButton getcancelTrainingButton() {
        return this.cancelTrainingButton;
    }
}
