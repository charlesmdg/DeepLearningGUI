package ihm.controls;

import ihm.app.MScene;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MButton extends Button {
    private final String text;
    private final MScene scene;

    public MButton(String text, MScene scene){
        super(text);
        this.text = text;
        this.scene = scene;

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MButton.this.scene.buttonClicked(MButton.this.text);
            }
        });
    }
}
