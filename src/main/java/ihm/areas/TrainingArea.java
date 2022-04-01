package ihm.areas;

import common.Constants;
import ihm.controls.DeepDisplayArea;
import ihm.controls.DeepTitleLabel;

public class TrainingArea extends DeepDisplayArea {
    public TrainingArea() {
        super(Constants.TRAINING, Constants.DISPLAY_AREA_WIDTH, Constants.TRAINING_AREA_HEIGHT);
        this.getChildren().add(new DeepTitleLabel(Constants.ARCHITECTURE));
    }
}
