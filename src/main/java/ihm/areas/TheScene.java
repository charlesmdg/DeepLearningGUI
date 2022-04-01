package ihm.areas;

import common.Constants;
import common.Message;
import common.Tools;
import data.CsvLoader;
import data.Evaluation;
import data.IaModel;
import ihm.controls.DeepHBox;
import ihm.controls.DeepVBox;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TheScene extends Scene {

    private final PredictionTypeArea predictionTypeArea = new PredictionTypeArea(this);
    private final DatasetArea datasetArea = new DatasetArea(this);
    private final ArchitectureArea architectureArea = new ArchitectureArea();
    private final OptimizationArea optimizationArea = new OptimizationArea();
    private final VisualisationArea visualisationArea = new VisualisationArea();
    private final EvaluationArea evaluationArea = new EvaluationArea();
    private final TrainingArea trainingArea = new TrainingArea();
    private final ButtonArea buttonArea = new ButtonArea(this);

    private Stage stage;
    private IaModel iaModel;

    public TheScene(DeepHBox group) {
        super(group, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);

        this.initScene(group);

        this.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R) {
                TheScene.this.reset();
            }
        });
    }

    private void reset() {
        this.iaModel = null;
        this.predictionTypeArea.getChildren().clear();
        Tools.inform("JK", "jkljlk");
    }

    private void initScene(DeepHBox group) {
        DeepVBox parameterBox = new DeepVBox(false);
        DeepVBox visualisationBox = new DeepVBox(false);

        this.fillGroup(group, parameterBox, visualisationBox);
        this.fillParameterBox(parameterBox);
        this.fillVisualisationBox(visualisationBox);

        this.datasetArea.setChildrenDisabled(true);
        this.architectureArea.setChildrenDisabled(true);
        this.optimizationArea.setChildrenDisabled(true);
        this.buttonArea.setChildrenDisabled(true);
    }

    private void fillGroup(DeepHBox group, DeepVBox parameterBox, DeepVBox visualisationBox) {
        group.add(Tools.createHExpandableSpacer());
        group.add(parameterBox);
        group.add(Tools.createHExpandableSpacer());
        group.add(visualisationBox);
        group.add(Tools.createHExpandableSpacer());
    }

    private void fillParameterBox(DeepVBox parameterBox) {
        parameterBox.add(Tools.createVExpandableSpacer());
        parameterBox.add(this.predictionTypeArea.getBox());
        parameterBox.add(Tools.createVExpandableSpacer());
        parameterBox.add(this.datasetArea.getBox());
        parameterBox.add(Tools.createVExpandableSpacer());
        parameterBox.add(this.buttonArea.getBox());
        parameterBox.add(Tools.createVExpandableSpacer());
        parameterBox.add(this.architectureArea.getBox());
        parameterBox.add(Tools.createVExpandableSpacer());
        parameterBox.add(this.optimizationArea.getBox());
        parameterBox.add(Tools.createVExpandableSpacer());
    }

    private void fillVisualisationBox(DeepVBox visualisationBox) {
        visualisationBox.add(Tools.createVExpandableSpacer());
        visualisationBox.add(this.visualisationArea);
        visualisationBox.add(Tools.createVExpandableSpacer());
        visualisationBox.add(this.trainingArea.getBox());
        visualisationBox.add(Tools.createVExpandableSpacer());
        visualisationBox.add(this.evaluationArea.getBox());
        visualisationBox.add(Tools.createVExpandableSpacer());
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

        this.datasetArea.setChildrenDisabled(false);
        this.optimizationArea.setChildrenDisabled(false);
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

    private void chooseCSVFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Constants.FILE_CHOOSER_TITLE);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Constants.CSV, Constants.STAR_DOT_CSV),
                new FileChooser.ExtensionFilter(Constants.ALL, Constants.STAR_DOT_STAR));
        File file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            String filePath = file.getPath();
            if (this.datasetArea.checkCsvFile(filePath)) {
                this.prepareTraining(file);
            }
        }
    }

    private void prepareTraining(File file) {
        this.architectureArea.setChildrenDisabled(false);
        this.fillDatasetArea(file);
        this.fillArchitectureArea();
        this.buttonArea.getVisualizeButton().setDisable(false);
        this.buttonArea.getTrainButton().setDisable(false);
    }

    private void fillDatasetArea(File file) {
        this.datasetArea.setCsvFile(file.getName());
        CsvLoader csvLoader = this.datasetArea.getCsvLoader();
        this.datasetArea.setTargetVariableComboBoxItemList(csvLoader.getColumnNames());
        String[] columnNames = csvLoader.getColumnNames();
        //A ce stade, apres le csvLoader.check columnNames.length >=2
        this.datasetArea.setTargetVariable(columnNames[columnNames.length - 1]);
        this.datasetArea.setTraining(Constants.DEFAULT_TRAINING);
        this.datasetArea.setPretreatment(Constants.STANDARD_SCALER);
    }

    private void fillArchitectureArea() {
        CsvLoader csvLoader = this.datasetArea.getCsvLoader();
        int inputCount = csvLoader.getDataset().getColumnNames().length - 1;
        int outputCount;
        int hiddenLayerCount = Constants.DEFAULT_HIDDEN_LAYER_COUNT;
        String activationFunction;
        String predictionType = this.predictionTypeArea.getPredictionType();

        if (predictionType.equals(Constants.CLASSIFICATION)) {
            activationFunction = Constants.TANH;

            String targetVariableName = this.datasetArea.getTargetVariableName();

            outputCount = csvLoader.getDataset().getValueCount(targetVariableName);
            if (outputCount > Constants.DIFFERENT_VALUE_MAX_COUNT) {
                Tools.inform(Message.TOO_MANY_DIFFERENT_VALUES, targetVariableName);
            }
        } else {
            activationFunction = Constants.RELU;
            outputCount = 1;
        }

        this.architectureArea.setInputCount(inputCount);
        this.architectureArea.setOutputCount(outputCount);
        this.architectureArea.sethiddenLayerCount(hiddenLayerCount);
        this.architectureArea.setActivationFunction(activationFunction);
    }

    private void visualizeButtonClicked() {
        this.visualisationArea.drawNetwork(this.architectureArea.getInputCount(),
                this.architectureArea.getOutputCount(),
                this.architectureArea.getHiddenLayerCount());
    }

    private void trainButtonClicked() {
        //Todo
        try {
            if (iaModel == null) {
                this.iaModel = new IaModel(this.predictionTypeArea.getPredictionType(),
                        this.architectureArea.getInputCount(),
                        this.architectureArea.getOutputCount(),
                        this.architectureArea.getHiddenLayerCount(),
                        this.architectureArea.getActivationFunction(),
                        this.optimizationArea.getLossFunction(),
                        this.optimizationArea.getOptimizer(),
                        this.optimizationArea.getLearningRate());
            }

            if (!this.iaModel.dataReady()) {
                this.iaModel.splitData(this.datasetArea.getCsvLoader(),
                        this.datasetArea.getTargetVariableName(),
                        this.datasetArea.getTrainingProportion(),
                        this.datasetArea.getPretreatment());
            }

            new Thread(() -> {
                int interationCount = this.optimizationArea.getIterationCount();
                for (int ii = 1; ii <= interationCount; ii++) {
                    try {
                        Thread.sleep(100);
                        data.Evaluation evaluation = this.iaModel.train1();
                        this.trainingArea.println(evaluation.toStringWithIteration(ii));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            this.buttonArea.getEvaluateButton().setDisable(false);

        } catch (Exception e) {

            Tools.error(Message.TRAINING_ERROR);
        }
    }

    private void evaluateButtonClicked() {
        try {
            Evaluation evaluation = this.iaModel.evaluate();

            this.evaluationArea.clear();
            this.evaluationArea.println(evaluation);

        } catch (Exception e) {
            Tools.error(Message.TRAINING_ERROR);
        }
    }
}
