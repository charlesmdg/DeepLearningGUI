package ihm.controls;

import common.Constants;
import ihm.areas.TheScene;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class DeepButton extends Button {
    private final String text;
    private final TheScene scene;

    public DeepButton(String text, TheScene scene){
        super(text);
        this.text = text;
        this.scene = scene;
        this.setFont(Constants.NORMAL_FONT);

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                DeepButton.this.scene.buttonClicked(DeepButton.this.text);
            }
        });
    }
}
