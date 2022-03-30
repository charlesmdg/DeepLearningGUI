package ihm.controls;

import common.Constants;
import common.Tools;

public class DeepDisplayArea extends DeepVInputArea {
    private final DeepTextArea textArea = new DeepTextArea();

    public DeepDisplayArea(double width, double height){
        super(Constants.DISPLAY_AREA_WIDTH, height, true);
        this.box.getChildren().add(Tools.createVExpandableSpacer());
        Tools.setHeight(this.textArea, height - 2 * Constants.AREA_PADDING - 1);
        this.box.getChildren().add(this.textArea);
        this.box.getChildren().add(Tools.createVExpandableSpacer());
    }

    public void println(String text){
        this.textArea.setText(this.textArea.getText() + Constants.CR + text);
    }

    public void println(double x){
        this.println(String.valueOf(x));
    }

    public void println(float x){
        this.println(String.valueOf(x));
    }

    public void println(int x){
        this.println(String.valueOf(x));
    }

    public void println(Object o){
        this.println(o.toString());
    }
}
