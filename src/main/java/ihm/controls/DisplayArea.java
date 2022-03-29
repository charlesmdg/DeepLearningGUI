package ihm.controls;

import common.Constants;

public class DisplayArea extends VInputArea {
    private  MTextArea textArea = new MTextArea();

    public DisplayArea(double height){
        super(Constants.DISPLAY_AREA_WIDTH, height, true);
        this.box.getChildren().add(this.textArea);
    }
}
