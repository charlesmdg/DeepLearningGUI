package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepHBox;
import ihm.controls.DeepVBox;
import ihm.network.HiddenNeuron;
import ihm.network.InputOuputNeuron;
import ihm.network.Neuron;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TheScene extends Scene {

    DatasetArea datasetArea = new DatasetArea();
    PredictionTypeArea predictionTypeArea = new PredictionTypeArea();
    ButtonArea buttonArea;
    ArchitectureArea architectureArea = new ArchitectureArea();
    OptimizationArea optimizationArea = new OptimizationArea();
    VisualisationArea visualisationArea = new VisualisationArea();
    TrainingArea trainingArea = new TrainingArea();
    EvaluationArea evaluationArea = new EvaluationArea();

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
        }
    }

    private void visualizeButtonClicked() {
        //Todo
        this.visualisationArea.drawNetwork(this.architectureArea.getInputCount(),
                                            this.architectureArea.getOutputCount(),
                                            this.architectureArea.getHiddenLayerCount(),
                                            this.architectureArea.getActivationFunction());
    }

    private void trainButtonClicked() {
        //Todo
        this.trainingArea.println("trainButtonClicked");
    }

    private void evaluateButtonClicked() {
        //Todo
        this.evaluationArea.println("evaluateButtonClicked");
    }
}
