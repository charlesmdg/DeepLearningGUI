package ihm.controls;

import common.Constants;
import common.Tools;
import ihm.areas.TheScene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import static common.Constants.RADIO_BUTTON_GROUP_HEIGHT;
import static common.Constants.RADIO_BUTTON_GROUP_WIDTH;

public class DeepRadioButtonGroup extends DeepVInputArea {

    private final ToggleGroup toggleGroup = new ToggleGroup();

    public DeepRadioButtonGroup(String[] texts, TheScene scene) {
        super(RADIO_BUTTON_GROUP_WIDTH, RADIO_BUTTON_GROUP_HEIGHT, false);

        int length = texts.length;

        this.add(Tools.createVExpandableSpacer());
        for (int ii = 0; ii < length; ii++) {
            DeepRadioButton radioButton = new DeepRadioButton(texts[ii]);
            this.add(radioButton);
            if(ii<length - 1)
                this.add(Tools.createVerticalSpacer(Constants.RADIO_BUTTON_INTERSPACE));
            radioButton.setToggleGroup(this.toggleGroup);
        }
        this.add(Tools.createVExpandableSpacer());

        this.toggleGroup.selectedToggleProperty().addListener(
                (observableValue, oldToggle, newToggle) -> scene.radioButtonGoupChanged(((DeepRadioButton)newToggle).getText()));
    }

    public DeepRadioButton getSelectedRadioButton() {
        return (DeepRadioButton) this.toggleGroup.getSelectedToggle();
    }
}
