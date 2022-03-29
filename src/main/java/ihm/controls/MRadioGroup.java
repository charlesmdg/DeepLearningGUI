package ihm.controls;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class MRadioGroup {

    private final MVBox box;

    public MRadioGroup(String[] texts) {
        this.box = new MVBox(false);
        ToggleGroup group = new ToggleGroup();

        for (String text : texts) {
            MRadioButton radioButton = new MRadioButton(text);
            this.box.getChildren().add(radioButton);
            radioButton.setToggleGroup(group);
        }
    }

    public MVBox getBox() {
        return this.box;
    }
}
