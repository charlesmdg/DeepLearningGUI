package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.*;
import javafx.scene.layout.Region;

public class OptimizationArea extends DeepVInputArea {
    private final DeepComboBox lossFunctionComboBox = new DeepComboBox(Constants.LOSS_FUNCTIONS);
    private final DeepComboBox optimizerComboBox = new DeepComboBox(Constants.OPTIMIZERS);
    private final DeepTextField parameterTextField = new DeepTextField(true);
    private final DeepSpinner iterationSpinner;
        ;

    public OptimizationArea(){
        super(Constants.PARAMETER_AREA_WIDTH, Constants.OPTIMISATION_AREA_HEIGHT, true);
        this.iterationSpinner = new DeepSpinner(Constants.ITERATIONS,
                                    Constants.ITERATION_MIN_VALUE, Constants.ITERATION_MAX_VALUE,
                                    null);

        this.add(new DeepTitleLabel(Constants.OPTIMIZATION));

        Tools.setWidth(this.parameterTextField, Constants.NUM_TEXTFIELD_WIDTH);

        String[] labelTexts = {Constants.LOSS_FUNCTION, Constants.OPTIMIZER, Constants.PARAMETERS, Constants.ITERATIONS};
        Region[] regions = {this.lossFunctionComboBox, this.optimizerComboBox, this.parameterTextField, this.iterationSpinner};

        this.fill(labelTexts, regions);
    }

    public void setChildrenDisabled(boolean disabled){
        this.lossFunctionComboBox.setDisable(disabled);
        this.optimizerComboBox.setDisable(disabled);
        this.parameterTextField.setDisable(disabled);
        this.iterationSpinner.setDisable(disabled);
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
        return this.iterationSpinner.getValue_();
    }

    public void setLossFunction(String lossFunction){
        this.lossFunctionComboBox.setValue(lossFunction);
    }

    public void setOptimizer(String optimizer){
        this.optimizerComboBox.setValue(optimizer);
    }

    public void setParameter(double learningRate){
        this.parameterTextField.setText(String.valueOf(learningRate));
    }

    public void setIterationCount(int iterationCount){
        this.iterationSpinner.setValue(iterationCount);
    }
}
