package ihm.areas;

import common.Constants;
import ihm.controls.DeepComboBox;
import ihm.controls.DeepSpinner;
import ihm.controls.DeepTitleLabel;
import ihm.controls.DeepVInputArea;
import javafx.scene.layout.Region;

public class ArchitectureArea extends DeepVInputArea {
    private final DeepSpinner inputCountSpinner = new DeepSpinner(Constants.INPUT_MIN_VALUE, Constants.INPUT_MAX_VALUE);
    private final DeepSpinner outputCountSpinner = new DeepSpinner(Constants.OUTPUT_MIN_VALUE, Constants.OUTPUT_MAX_VALUE);
    private final DeepSpinner hiddenLayerCountSpinner = new DeepSpinner(Constants.HIDDEN_LAYER_MIN_VALUE, Constants.HIDDEN_LAYER_MAX_VALUE);
    private final DeepComboBox activationFunctionComboBox = new DeepComboBox(Constants.ACTIVATION_FUNCTIONS);

    public ArchitectureArea() {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.ARCHITECTURE_AREA_HEIGHT, true);

        this.add(new DeepTitleLabel(Constants.ARCHITECTURE));

        String[] labelTexts = {Constants.INPUTS, Constants.OUTPUTS, Constants.HIDDEN_LAYERS, Constants.ACTIVATION_FUNCTION};
        Region[] regions = {this.inputCountSpinner, this.outputCountSpinner, this.hiddenLayerCountSpinner, this.activationFunctionComboBox};
        this.fill(labelTexts, regions);
    }

    public void setChildrenDisabled(boolean disabled){
        this.inputCountSpinner.setDisable(disabled);
        this.outputCountSpinner.setDisable(disabled);
        this.hiddenLayerCountSpinner.setDisable(disabled);
        this.activationFunctionComboBox.setDisable(disabled);
    }

    public int getInputCount(){
        return this.inputCountSpinner.getValue();
    }

    public int getOutputCount(){
        return this.outputCountSpinner.getValue();
    }

    public int getHiddenLayerCount(){
        return this.hiddenLayerCountSpinner.getValue();
    }

    public String getActivationFunction(){
        return this.activationFunctionComboBox.getSelectionModel().getSelectedItem();
    }

    public void setInputCount(int inputCount){
       this.inputCountSpinner.setValue(inputCount);
    }

    public void setOutputCount(int outputCount){
        this.outputCountSpinner.setValue(outputCount);
    }

    public void sethiddenLayerCount(int hiddenLayerCount){
        this.hiddenLayerCountSpinner.setValue(hiddenLayerCount);
    }

    public void setActivationFunction(String activationFunction){
        this.activationFunctionComboBox.setValue(activationFunction);
    }

}
