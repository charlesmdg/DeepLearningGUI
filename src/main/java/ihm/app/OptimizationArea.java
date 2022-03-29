package ihm.app;

import common.Constants;
import ihm.controls.MComboBox;
import ihm.controls.MSpinner;
import ihm.controls.MTextField;
import ihm.controls.VInputArea;
import javafx.scene.layout.Region;

public class OptimizationArea extends VInputArea {
    MComboBox lossFunctionComboBox = new MComboBox(Constants.LOSS_FUNCTIONS);
    MComboBox optimizerComboBox = new MComboBox(Constants.OPTIMIZERS);
    MTextField parameterTextField = new MTextField();
    MSpinner iterationSpinner = new MSpinner(Constants.ITERATION_MIN_VALUE, Constants.ITERATION_MAX_VALUE);

    public OptimizationArea(){
        super(Constants.PARAMETER_AREA_WIDTH, Constants.OPTIMISATION_AREA_HEIGHT, true);

        String[] labelTexts = {Constants.LOSS_FUNCTION, Constants.OPTIMIZER, Constants.PARAMETERS, Constants.ITERATIONS};
        Region[] regions = {this.lossFunctionComboBox, this.optimizerComboBox, this.parameterTextField, this.iterationSpinner};

        this.fill(labelTexts, regions);
    }

    public String getLossFunction(){
        return this.lossFunctionComboBox.getSelectionModel().getSelectedItem();
    }

    public String getOptimizer(){
        return this.optimizerComboBox.getSelectionModel().getSelectedItem();
    }

    public double getLearningRate(){
        return Double.parseDouble(this.parameterTextField.getText());
    }

    public int getIterationCount(){
        return this.iterationSpinner.getValue();
    }
}
