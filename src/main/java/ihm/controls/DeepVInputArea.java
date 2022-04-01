package ihm.controls;

import common.Tools;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class DeepVInputArea extends DeepInputArea {

    public DeepVInputArea(double width, double height, boolean bordered) {
        super(new DeepVBox(bordered), width, height);
    }

    @Override
    protected Region createExpandableSpacer() {
        return Tools.createVExpandableSpacer();
    }
}
