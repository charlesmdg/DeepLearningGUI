package ihm.controls;

import common.Constants;
import common.Tools;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DeepDisplayArea extends DeepVInputArea {
    private final DeepTextArea textArea = new DeepTextArea();

    public DeepDisplayArea(String title, double height){
        super(Constants.DISPLAY_AREA_WIDTH, height, true);
        this.textArea.setFont(Constants.NORMAL_FONT);

        DeepTitleLabel titleLabel = new DeepTitleLabel(title);

        this.box.getChildren().add(titleLabel);

        this.box.getChildren().add(Tools.createVExpandableSpacer());
        Tools.setHeight(this.textArea, height - 2 * Constants.AREA_PADDING - Constants.DISPLAY_AREA_TOP_MARGIN);
        this.box.getChildren().add(this.textArea);
        this.box.getChildren().add(Tools.createVExpandableSpacer());

        this.textArea.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.C) DeepDisplayArea.this.textArea.clear();
        });
    }

    public void println(String text){
        this.textArea.appendText(text + Constants.CR);
    }

    public void println(Object o){
        this.println(o.toString());
    }

    public void clear(){
        this.textArea.clear();
    }
}
