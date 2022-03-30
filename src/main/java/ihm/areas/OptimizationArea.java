package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepComboBox;
import ihm.controls.DeepSpinner;
import ihm.controls.DeepTextField;
import ihm.controls.DeepVInputArea;
import javafx.scene.layout.Region;

public class OptimizationArea extends DeepVInputArea {
    DeepComboBox lossFunctionComboBox = new DeepComboBox(Constants.LOSS_FUNCTIONS);
    DeepComboBox optimizerComboBox = new DeepComboBox(Constants.OPTIMIZERS);
    DeepTextField parameterTextField = new DeepTextField();
    DeepSpinner iterationSpinner = new DeepSpinner(Constants.ITERATION_MIN_VALUE, Constants.ITERATION_MAX_VALUE);

    public OptimizationArea(){
        super(Constants.PARAMETER_AREA_WIDTH, Constants.OPTIMISATION_AREA_HEIGHT, true);

        Tools.setWidth(this.parameterTextField, Constants.NUM_TEXTFIELD_WIDTH);

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
