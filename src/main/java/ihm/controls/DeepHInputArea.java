package ihm.controls;

import common.Tools;
import javafx.scene.layout.Region;

public class DeepHInputArea extends DeepInputArea {

    public DeepHInputArea(double width, double height, boolean bordered) {
        super(new DeepHBox(bordered), width, height);
    }

    @Override
    protected Region createExpandableSpacer() {
        return Tools.createHExpandableSpacer();
    }
}
