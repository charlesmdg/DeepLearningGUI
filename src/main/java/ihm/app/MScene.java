package ihm.app;

import common.Constants;
import common.Tools;
import ihm.controls.MHBox;
import ihm.controls.MVBox;
import javafx.scene.Scene;

public class MScene extends Scene {

    DatasetArea datasetArea = new DatasetArea();
    PredictionTypeArea predictionTypeArea = new PredictionTypeArea();
    ButtonArea buttonArea;
    ArchitectureArea architectureArea = new ArchitectureArea();
    OptimizationArea optimizationArea = new OptimizationArea();
    VisualisationArea visualisationArea = new VisualisationArea();
    TrainingArea trainingArea = new TrainingArea();
    EvaluationArea evaluationArea = new EvaluationArea();

    public MScene(MHBox group) {
        super(group, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        this.buttonArea = new ButtonArea(this);

        MVBox parameterBox = new MVBox(false);
        group.getChildren().add(parameterBox);
        parameterBox.getChildren().add(this.predictionTypeArea.getBox());
        parameterBox.getChildren().add(this.datasetArea.getBox());
        parameterBox.getChildren().add(this.buttonArea.getBox());
        parameterBox.getChildren().add(this.architectureArea.getBox());
        parameterBox.getChildren().add(this.optimizationArea.getBox());

        MVBox visualisationBox = new MVBox(false);
        group.getChildren().add(visualisationBox);
        visualisationBox.getChildren().add(this.visualisationArea.getBox());
        visualisationBox.getChildren().add(this.trainingArea.getBox());
        visualisationBox.getChildren().add(this.evaluationArea.getBox());
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
        Tools.println("visualizeButtonClicked");
    }

    private void trainButtonClicked() {
        //Todo
        Tools.println("trainButtonClicked");
    }

    private void evaluateButtonClicked() {
        //Todo
        Tools.println("evaluateButtonClicked");
    }
}
