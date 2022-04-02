package ihm.areas;

import ihm.controls.DeepComboBox;
import ihm.controls.DeepSpinner;
import ihm.controls.DeepTitleLabel;
import ihm.controls.DeepVInputArea;
import javafx.scene.layout.Region;

import static common.Constants.*;

public class ArchitectureArea extends DeepVInputArea {
    private final DeepSpinner inputCountSpinner;
    private final DeepSpinner outputCountSpinner;
    private final DeepSpinner hiddenLayerCountSpinner;
    private final DeepComboBox activationFunctionComboBox = new DeepComboBox(ACTIVATION_FUNCTIONS);

    public ArchitectureArea(TheScene scene) {
        super(PARAMETER_AREA_WIDTH, ARCHITECTURE_AREA_HEIGHT, true);
        this.inputCountSpinner = new DeepSpinner(INPUTS, INPUT_MIN_VALUE, INPUT_MAX_VALUE, scene);
        this.outputCountSpinner = new DeepSpinner(OUTPUTS, OUTPUT_MIN_VALUE, OUTPUT_MAX_VALUE, scene);
        this.hiddenLayerCountSpinner = new DeepSpinner(HIDDEN_LAYERS, HIDDEN_LAYER_MIN_VALUE, HIDDEN_LAYER_MAX_VALUE, scene);

        this.add(new DeepTitleLabel(ARCHITECTURE));

        String[] labelTexts = {INPUTS, OUTPUTS, HIDDEN_LAYERS, ACTIVATION_FUNCTION};
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
        return this.inputCountSpinner.getValue_();
    }

    public int getOutputCount(){
        return this.outputCountSpinner.getValue_();
    }

    public int getHiddenLayerCount(){
        return this.hiddenLayerCountSpinner.getValue_();
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
