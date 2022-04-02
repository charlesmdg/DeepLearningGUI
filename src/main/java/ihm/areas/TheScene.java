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
    private final ArchitectureArea architectureArea;
    private final OptimizationArea optimizationArea = new OptimizationArea();
    private final VisualisationArea visualisationArea = new VisualisationArea();
    private final EvaluationArea evaluationArea = new EvaluationArea();
    private final TrainingArea trainingArea = new TrainingArea();
    private final ButtonArea buttonArea = new ButtonArea(this);

    private Stage stage;
    private IaModel iaModel;

    public TheScene(DeepHBox group) {
        super(group, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        this.architectureArea = new ArchitectureArea(this);

        Tools.setSceneBackground(this, Constants.SCENE_TOP_LEFT_COLOR, Constants.SCENE_BOTTOM_RIGHT_COLOR);

        this.initScene(group);

        this.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.R) {
                TheScene.this.resetModel();
            }
        });
    }

    private void resetModel() {
        this.iaModel.resetModel();

        Tools.inform("RESET MODEL");
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

    public void spinnerValueChanged(String spinnerText, int newValue) {
        //Attention fausse repetition, le newValue est positionne differemment
        //d'un cas a l'autre
        switch (spinnerText) {
            case Constants.INPUTS:
                this.visualisationArea.drawNetwork(newValue,
                        this.architectureArea.getOutputCount(),
                        this.architectureArea.getHiddenLayerCount());
                break;
            case Constants.OUTPUTS:
                this.visualisationArea.drawNetwork(this.architectureArea.getInputCount(),
                        newValue,
                        this.architectureArea.getHiddenLayerCount());
                break;
            case Constants.HIDDEN_LAYERS:
                this.visualisationArea.drawNetwork(this.architectureArea.getInputCount(),
                        this.architectureArea.getOutputCount(),
                        newValue);
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

    private boolean checkTrainingOptions() {
        int predictiveVariableCount = this.datasetArea.getCsvLoader().getDataset().getColumnNames().length - 1;

        boolean answer = this.architectureArea.getInputCount() == predictiveVariableCount;


        if (this.predictionTypeArea.getPredictionType().equals(Constants.CLASSIFICATION)) {
            int classCount = this.datasetArea.getCsvLoader().getDataset()
                    .getValueCount(this.datasetArea.getTargetVariableName());
            answer = answer && (classCount == this.architectureArea.getOutputCount());
        } else {
            answer = answer && (this.architectureArea.getOutputCount() == 1);
        }

        return answer;
    }

    public void initModelIfNecessary(){
        if (this.iaModel == null) {
            this.iaModel = new IaModel(this.predictionTypeArea.getPredictionType(),
                    this.architectureArea.getInputCount(),
                    this.architectureArea.getOutputCount(),
                    this.architectureArea.getHiddenLayerCount(),
                    this.architectureArea.getActivationFunction(),
                    this.optimizationArea.getLossFunction(),
                    this.optimizationArea.getOptimizer(),
                    this.optimizationArea.getLearningRate());
        }

        if (!this.iaModel.modelReady()) {
            this.iaModel.resetModel();
        }
    }

    public void splitDataIfNecessary() throws Exception{
        if (!this.iaModel.dataReady()) {
            this.iaModel.splitData(this.datasetArea.getCsvLoader(),
                    this.datasetArea.getTargetVariableName(),
                    this.datasetArea.getTrainingProportion(),
                    this.datasetArea.getPretreatment());
        }
    }

    private void disableControlsDuringTraining(){
        this.predictionTypeArea.setChildrenDisabled(true);
        this.datasetArea.setChildrenDisabled(true);
        this.architectureArea.setChildrenDisabled(true);
        this.optimizationArea.setChildrenDisabled(true);
        this.buttonArea.getEvaluateButton().setDisable(true);
        this.evaluationArea.clear();
    }

    private void enableControlsAfterTraining(){
        this.optimizationArea.getIterationSpinner().setDisable(false);
        this.buttonArea.getEvaluateButton().setDisable(false);
    }

    private boolean checkBeforeTraining(){

        if(this.predictionTypeArea.getPredictionType().equals(Constants.REGRESSION)){
            Tools.inform(Message.NOT_IMPLEMENTED, Constants.REGRESSION);
            return false;
        }

        if (!this.checkTrainingOptions()) {
            Tools.inform(Message.ARCHITECTURE_AND_DATASET_DISCORDANCY);
            return false;
        }

        if (!Tools.checkNumericalTextField(this.datasetArea.getTrainingTextField(), 0, 1)){
            Tools.inform(Message.WRONG_PROPORTION, Constants.TRAINING);
            this.datasetArea.getTrainingTextField().requestFocus();
            return false;
        }

        if (!Tools.checkNumericalTextField(this.optimizationArea.getParameterTextField(), 0, 1)){
            Tools.inform(Message.WRONG_PROPORTION, Constants.PARAMETERS);
            this.optimizationArea.getParameterTextField().requestFocus();
            return false;
        }

        return true;
    }

    private void trainButtonClicked() {

        try {
            if (! checkBeforeTraining()){
                return;
            }

            this.initModelIfNecessary();
            this.splitDataIfNecessary();

            new Thread(() -> {
                this.disableControlsDuringTraining();
                int interationCount = this.optimizationArea.getIterationCount();

                try {
                    for (int ii = 1; ii <= interationCount; ii++) {
                        Thread.sleep(Constants.TRAINING_DELAY);
                        data.Evaluation evaluation = this.iaModel.train();
                        this.trainingArea.println(evaluation.toStringWithIteration(this.iaModel.getAchievedInterationCount()));
                    }

                    this.enableControlsAfterTraining();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            Tools.error(Message.TRAINING_ERROR);
        }
    }

    private void evaluateButtonClicked() {
        try {
            Evaluation evaluation = this.iaModel.evaluate();
            this.evaluationArea.clear();
            this.evaluationArea.println(evaluation);
            if(evaluation.getIndicatorValue() < this.iaModel.getAchivedLatestIndicatorValue()){
                Tools.inform(Message.OVERFITTING_MODEL);
            }

        } catch (Exception e) {
            Tools.error(Message.TRAINING_ERROR);
        }
    }
}
