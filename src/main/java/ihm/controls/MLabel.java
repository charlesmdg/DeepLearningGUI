package ihm.controls;

import common.Constants;
import javafx.scene.control.Label;

public class MLabel extends Label {
    public MLabel() {
        super();
        this.setFont(Constants.NORMAL_FONT);
    }

    public MLabel(String text) {
        super(text);
        this.setFont(Constants.NORMAL_FONT);
    }
}
