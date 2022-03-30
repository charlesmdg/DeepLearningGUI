package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepHBox;
import ihm.controls.DeepVBox;
import javafx.scene.Scene;

public class TheScene extends Scene {

    private final DatasetArea datasetArea = new DatasetArea();
    private final PredictionTypeArea predictionTypeArea = new PredictionTypeArea(this);
    private final ButtonArea buttonArea;
    private final ArchitectureArea architectureArea = new ArchitectureArea();
    private final OptimizationArea optimizationArea = new OptimizationArea();
    private final VisualisationArea visualisationArea = new VisualisationArea();
    private final TrainingArea trainingArea = new TrainingArea();
    private final EvaluationArea evaluationArea = new EvaluationArea();



    public TheScene(DeepHBox group) {
        super(group, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        this.buttonArea = new ButtonArea(this);

        DeepVBox parameterBox = new DeepVBox(false);
        DeepVBox visualisationBox = new DeepVBox(false);
        group.getChildren().add(Tools.createHExpandableSpacer());
        group.getChildren().add(parameterBox);
        group.getChildren().add(Tools.createHExpandableSpacer());
        group.getChildren().add(visualisationBox);
        group.getChildren().add(Tools.createHExpandableSpacer());

        parameterBox.getChildren().add(Tools.createVExpandableSpacer());
        parameterBox.getChildren().add(this.predictionTypeArea.getBox());
        parameterBox.getChildren().add(Tools.createVExpandableSpacer());
        parameterBox.getChildren().add(this.datasetArea.getBox());
        parameterBox.getChildren().add(Tools.createVExpandableSpacer());
        parameterBox.getChildren().add(this.buttonArea.getBox());
        parameterBox.getChildren().add(Tools.createVExpandableSpacer());
        parameterBox.getChildren().add(this.architectureArea.getBox());
        parameterBox.getChildren().add(Tools.createVExpandableSpacer());
        parameterBox.getChildren().add(this.optimizationArea.getBox());
        parameterBox.getChildren().add(Tools.createVExpandableSpacer());

        visualisationBox.getChildren().add(Tools.createVExpandableSpacer());
        visualisationBox.getChildren().add(this.visualisationArea);
        visualisationBox.getChildren().add(Tools.createVExpandableSpacer());
        visualisationBox.getChildren().add(this.trainingArea.getBox());
        visualisationBox.getChildren().add(Tools.createVExpandableSpacer());
        visualisationBox.getChildren().add(this.evaluationArea.getBox());
        visualisationBox.getChildren().add(Tools.createVExpandableSpacer());
    }

    public void radioButtonGoupChanged(String text) {
        switch (text) {
            case Constants.CLASSIFICATION:
                this.classificationPedictionTypeSelected();
                break;
            case Constants.REGRESSION:
                this.regressionPedictionTypeSelected();
                break;
            default:
        }
    }

    //Todo
    private void classificationPedictionTypeSelected(){
    }

    //Todo
    private void regressionPedictionTypeSelected(){
    }

    public void buttonClicked(String buttonText) {
        switch (buttonText) {
            case Constants.VISUALIZE:
                this.visualizeButtonClicked();
                break;
            case Constants.TRAIN:
                this.trainButtonClicked();
                break;
            case Constants.EVALUATE:
                this.evaluateButtonClicked();
                break;
            default:
        }
    }

    private void visualizeButtonClicked() {
        this.visualisationArea.drawNetwork(this.architectureArea.getInputCount(),
                                            this.architectureArea.getOutputCount(),
                                            this.architectureArea.getHiddenLayerCount(),
                                            this.architectureArea.getActivationFunction());
    }

    private void trainButtonClicked() {
        //Todo
        int iterationCount = this.optimizationArea.getIterationCount();
        for (int ii=1; ii<=iterationCount; ii++){
            this.trainingArea.println(Constants.ITERATION +
                                            Constants.SPACED_SHARP +
                                            ii +
                                            Constants.SPACED_COLON);
        }
    }

    private void evaluateButtonClicked() {
        //Todo
        this.evaluationArea.println("evaluateButtonClicked");
    }
}
