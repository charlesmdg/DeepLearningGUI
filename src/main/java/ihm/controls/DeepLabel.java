package ihm.controls;

import common.Constants;
import javafx.scene.control.Label;

public class DeepLabel extends Label {
    public DeepLabel() {
        super();
        this.setFont(Constants.NORMAL_FONT);
    }

    public DeepLabel(String text) {
        super(text);
        this.setFont(Constants.NORMAL_FONT);
    }
}
