package ihm.controls;

import common.Constants;
import ihm.areas.TheScene;
import javafx.scene.control.Button;

public class DeepButton extends Button {
    private final TheScene scene;

    public DeepButton(String text, TheScene scene){
        super(text);
        this.scene = scene;
        this.setFont(Constants.NORMAL_FONT);

        this.setOnMouseClicked(mouseEvent -> DeepButton.this.scene.buttonClicked(DeepButton.this.getText()));
    }
}
