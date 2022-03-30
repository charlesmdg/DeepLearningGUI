package ihm.areas;

import common.Constants;
import ihm.controls.DeepComboBox;
import ihm.controls.DeepSpinner;
import ihm.controls.DeepVInputArea;
import javafx.scene.layout.Region;

public class ArchitectureArea extends DeepVInputArea {
    private final DeepSpinner inputSpinner = new DeepSpinner(Constants.INPUT_MIN_VALUE, Constants.INPUT_MAX_VALUE);
    private final DeepSpinner outputSpinner = new DeepSpinner(Constants.OUTPUT_MIN_VALUE, Constants.OUTPUT_MAX_VALUE);
    private final DeepSpinner hiddenLayerSpinner = new DeepSpinner(Constants.HIDDEN_LAYER_MIN_VALUE, Constants.HIDDEN_LAYER_MAX_VALUE);
    private final DeepComboBox activationFunctionComboBox = new DeepComboBox(Constants.ACTIVATION_FUNCTIONS);

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
