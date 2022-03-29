package ihm;

import common.Constants;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class MScene extends Scene {

    public MScene(MGroup group, double width, double height) {
        super(group, width, height);
        MVBox parameterBox = new MVBox(true);
        group.getChildren().add(parameterBox);
        parameterBox.getChildren().add(this.createPredictionTypePanel().getBox());
        parameterBox.getChildren().add(this.createDatasetPanel().getBox());
        parameterBox.getChildren().add(this.createButtonPanel().getBox());
        parameterBox.getChildren().add(this.createArchitecturePanel().getBox());
        parameterBox.getChildren().add(this.createOptimizationPanel().getBox());

        System.out.println();
    }

    private Panel createPredictionTypePanel() {
        MRadioGroup radioGroup = new MRadioGroup(Constants.PREDICTION_TYPES);

        Region[] regions = {radioGroup.getBox()};

        return this.createPanel(regions, true, true);
    }

    private Panel createDatasetPanel() {
        MTextField csvFileTextField = new MTextField();
        MComboBox targetVariableComboBox = new MComboBox();
        MTextField trainingTextField = new MTextField();
        MComboBox preTeatmentComboBox = new MComboBox(Constants.PRETREATMENT_OPTIONS);

        String[] labelTexts = {Constants.CSV_FILE, Constants.TARGET_VARIABLE, Constants.TRAINING, Constants.PRETREATMENT};
        Region[] regions = {csvFileTextField, targetVariableComboBox, trainingTextField, preTeatmentComboBox};

        return this.createPanel(labelTexts, regions, true, true);
    }

    private Panel createArchitecturePanel() {
        MSpinner inputSpinner = new MSpinner();
        MSpinner outputSpinner = new MSpinner();
        MSpinner hiddenLayerSpinner = new MSpinner();
        MComboBox activationFunctionComboBox = new MComboBox(Constants.ACTIVATION_FUNCTIONS);

        String[] labelTexts = {Constants.INPUTS, Constants.OUTPUTS, Constants.HIDDEN_LAYERS, Constants.ACTIVATION_FUNCTION};
        Region[] regions = {inputSpinner, outputSpinner, hiddenLayerSpinner, activationFunctionComboBox};

        return this.createPanel(labelTexts, regions, true, true);
    }

    private Panel createOptimizationPanel() {
        MComboBox lossFunctionComboBox = new MComboBox(Constants.LOSS_FUNCTIONS);
        MComboBox optimizerComboBox = new MComboBox(Constants.OPTIMIZERS);
        MTextField parameterTextField = new MTextField();
        MSpinner iterationSpinner = new MSpinner();

        String[] labelTexts = {Constants.LOSS_FUNCTION, Constants.OPTIMIZER, Constants.PARAMETERS, Constants.ITERATIONS};
        Region[] regions = {lossFunctionComboBox, optimizerComboBox, parameterTextField, iterationSpinner};

        return this.createPanel(labelTexts, regions, true, true);
    }


    private Panel createPanel(String[] labelTexts, Region[] regions, boolean bordered, boolean verticalBox) {
        Panel panel;
        if (verticalBox)
            panel = new VPanel(bordered);
        else
            panel = new HPanel(bordered);

        panel.setSize(Constants.PARAMTER_FRAME_WIDTH, 150);

        int length = labelTexts.length;

        for (int ii = 0; ii < length; ii++) {
            LabeledRegion labeledRegion = new LabeledRegion(labelTexts[ii], regions[ii]);
            panel.add(labeledRegion);
        }
        return panel;
    }

    private Panel createPanel(Region[] regions, boolean bordered, boolean verticalBox) {
        Panel panel;
        if (verticalBox)
            panel = new VPanel(bordered);
        else
            panel = new HPanel(bordered);

        panel.setSize(Constants.PARAMTER_FRAME_WIDTH, 150);

        for (Region region : regions) {
            panel.add(region);
        }
        return panel;
    }


    private Panel createButtonPanel() {
        Button visualizeButton = new Button(Constants.VISUALIZE);
        Button trainButton = new Button(Constants.TRAIN);
        Button evaluateButton = new Button(Constants.EVALUATE);

        Region[] regions = {visualizeButton, trainButton, evaluateButton};

        return this.createPanel(regions, false, false);
    }

}
