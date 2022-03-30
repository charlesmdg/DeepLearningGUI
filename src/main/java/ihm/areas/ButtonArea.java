package ihm.areas;

import common.Constants;
import ihm.controls.DeepHInputArea;
import ihm.controls.DeepButton;
import javafx.scene.layout.Region;

public class ButtonArea extends DeepHInputArea {
    public ButtonArea(TheScene scene){
        super(Constants.PARAMETER_AREA_WIDTH, Constants.BUTTON_AREA_HEIGHT, false);

        DeepButton visualizeButton = new DeepButton(Constants.VISUALIZE, scene);
        DeepButton trainButton = new DeepButton(Constants.TRAIN, scene);
        DeepButton evaluateButton = new DeepButton(Constants.EVALUATE, scene);

        Region[] regions = {visualizeButton, trainButton, evaluateButton};
        this.fill(regions);
    }
}
