package ihm.app;

import common.Constants;
import ihm.controls.MComboBox;
import ihm.controls.MSpinner;
import ihm.controls.MTextField;
import ihm.controls.VInputArea;
import javafx.scene.layout.Region;

public class ArchitectureArea extends VInputArea {
    private final MSpinner inputSpinner = new MSpinner(Constants.INPUT_MIN_VALUE, Constants.INPUT_MAX_VALUE);
    private final MSpinner outputSpinner = new MSpinner(Constants.OUTPUT_MIN_VALUE, Constants.OUTPUT_MAX_VALUE);
    private final MSpinner hiddenLayerSpinner = new MSpinner(Constants.HIDDEN_LAYER_MIN_VALUE, Constants.HIDDEN_LAYER_MAX_VALUE);
    private final MComboBox activationFunctionComboBox = new MComboBox(Constants.ACTIVATION_FUNCTIONS);

    public ArchitectureArea() {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.ARCHITECTURE_AREA_HEIGHT, true);


        String[] labelTexts = {Constants.INPUTS, Constants.OUTPUTS, Constants.HIDDEN_LAYERS, Constants.ACTIVATION_FUNCTION};
        Region[] regions = {this.inputSpinner, this.outputSpinner, this.hiddenLayerSpinner, this.activationFunctionComboBox};
        this.fill(labelTexts, regions);
    }

    public int getInputCount(){
        //Todo
        return this.inputSpinner.getValue();
    }

    public int getOutputCount(){
        //Todo
        return this.outputSpinner.getValue();
    }

    public int getHiddenLayerCount(){
        //Todo
        return this.hiddenLayerSpinner.getValue();
    }

    public String getActivationFunction(){
        //Todo
        return this.activationFunctionComboBox.getSelectionModel().getSelectedItem();
    }


}
