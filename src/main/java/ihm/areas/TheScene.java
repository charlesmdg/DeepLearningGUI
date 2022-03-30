package ihm.areas;

import common.Constants;
import common.CsvLoader;
import common.Tools;
import ihm.controls.DeepHBox;
import ihm.controls.DeepVBox;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TheScene extends Scene {

    private final DatasetArea datasetArea = new DatasetArea(this);
    private final PredictionTypeArea predictionTypeArea = new PredictionTypeArea(this);
    private final ButtonArea buttonArea;
    private final ArchitectureArea architectureArea = new ArchitectureArea();
    private final OptimizationArea optimizationArea = new OptimizationArea();
    private final VisualisationArea visualisationArea = new VisualisationArea();
    private final TrainingArea trainingArea = new TrainingArea();
    private final EvaluationArea evaluationArea = new EvaluationArea();
    private Stage stage;
    private String csvFile;


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

    public void setStage(Stage stage) {
        this.stage = stage;
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

    private void classificationPedictionTypeSelected() {
        this.optimizationArea.setLossFunction(Constants.NEGATIVE_LOG_LIKELIHOOD);
        this.selectDefaultOptimisationParameters();
    }

    private void regressionPedictionTypeSelected() {
        this.optimizationArea.setLossFunction(Constants.MEAN_SQUARED_ERROR);
        this.selectDefaultOptimisationParameters();
    }

    private void selectDefaultOptimisationParameters() {
        this.optimizationArea.setOptimizer(Constants.STOCHASTIC_GRADIENT);
        this.optimizationArea.setParameter(Constants.DEFAULT_LEARNING_RATE);
        this.optimizationArea.setIterationCount(Constants.DEFAULT_ITERATION_COUNT);
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
            case Constants.CHOOSE_AND_DOTS:
                this.chooseCSVFile();
                break;
            default:
        }
    }

    private void chooseCSVFile(){
        //Todo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Constants.FILE_CHOOSER_TITLE);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Constants.CSV, Constants.STAR_DOT_CSV),
                new FileChooser.ExtensionFilter("Tous", "*.*"));
        File file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            CsvLoader csvLoader = new CsvLoader();
            String filePath = file.getPath();
                    if(csvLoader.check(filePath)){
                        this.csvFile = filePath;
                        this.datasetArea.setCsvFile(file.getName());
                        this.datasetArea.setTargetVariableComboBoxItemList(csvLoader.getColumnNames());
                        String[] columnNames = csvLoader.getColumnNames();
                        //A ce stade, apres le csvLoader.check columnNames.length >=2
                        this.datasetArea.setTargetVariable(columnNames[columnNames.length - 1]);
                        this.datasetArea.setTraining(Constants.DEFAULT_TRAINING);
                        this.datasetArea.setPretreatment(Constants.STANDARD_SCALER);
                    }
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
        for (int ii = 1; ii <= iterationCount; ii++) {
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
