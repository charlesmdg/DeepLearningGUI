package ihm.app;

import common.Constants;
import ihm.controls.HInputArea;
import ihm.controls.MButton;
import javafx.scene.layout.Region;

public class ButtonArea extends HInputArea {
    public ButtonArea(MScene scene){
        super(Constants.PARAMETER_AREA_WIDTH, Constants.BUTTON_AREA_HEIGHT, false);

        MButton visualizeButton = new MButton(Constants.VISUALIZE, scene);
        MButton trainButton = new MButton(Constants.TRAIN, scene);
        MButton evaluateButton = new MButton(Constants.EVALUATE, scene);

        Region[] regions = {visualizeButton, trainButton, evaluateButton};
        this.fill(regions);
    }
}
