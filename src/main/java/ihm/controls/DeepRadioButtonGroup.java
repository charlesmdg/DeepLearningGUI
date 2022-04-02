package ihm.controls;

import common.Tools;
import ihm.areas.TheScene;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

import static common.Constants.*;

public class DeepRadioButtonGroup extends DeepVInputArea {

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final ArrayList<DeepRadioButton> deepRadioButtons = new ArrayList<>();

    public DeepRadioButtonGroup(String[] texts, TheScene scene) {
        super(RADIO_BUTTON_GROUP_WIDTH, RADIO_BUTTON_GROUP_HEIGHT, false);

        int length = texts.length;

        this.add(Tools.createVExpandableSpacer());
        for (int ii = 0; ii < length; ii++) {
            DeepRadioButton radioButton = new DeepRadioButton(texts[ii]);
            this.deepRadioButtons.add(radioButton);
            this.add(radioButton);
            if(ii<length - 1)
                this.add(Tools.createVerticalSpacer(RADIO_BUTTON_INTERSPACE));
            radioButton.setToggleGroup(this.toggleGroup);
        }
        this.add(Tools.createVExpandableSpacer());

        this.toggleGroup.selectedToggleProperty().addListener(
                (observableValue, oldToggle, newToggle) -> scene.radioButtonGoupChanged(((DeepRadioButton)newToggle).getText()));
    }

    public DeepRadioButton getSelectedRadioButton() {
        return (DeepRadioButton) this.toggleGroup.getSelectedToggle();
    }

    public void setChildrenDisabled(boolean disabled){
        for(DeepRadioButton radioButton: deepRadioButtons){
            radioButton.setDisable(disabled);
        }
    }
}
