package ihm.areas;

import common.Constants;
import common.Message;
import common.Tools;
import data.CsvLoader;
import data.Evaluation;
import data.IaModel;
import ihm.controls.DeepHBox;
import ihm.controls.DeepVBox;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class TheScene extends Scene {

    private final PredictionTypeArea predictionTypeArea = new PredictionTypeArea(this);
    private final DatasetArea datasetArea = new DatasetArea(this);
    private final ArchitectureArea architectureArea;
    private final OptimizationArea optimizationArea = new OptimizationArea();
    private final VisualisationArea visualisationArea = new VisualisationArea();
    private final EvaluationArea evaluationArea = new EvaluationArea();
    private final TrainingArea trainingArea = new TrainingArea();
    private final ButtonArea buttonArea = new ButtonArea(this);

    private Thread trainingThread;
    private boolean stopTrainingThread = false;
    private Stage stage;
    private IaModel iaModel;

    public TheScene(DeepHBox group) {
        super(group, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        this.architectureArea = new ArchitectureArea(this);

        Tools.setSceneBackground(this, Constants.SCENE_TOP_LEFT_COLOR, Constants.SCENE_BOTTOM_RIGHT_COLOR);

        this.initScene(group);
    }

    /**
     * Construit le contenu de la scene
     *
     * @param group le group principal de la scene
     */
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

    /**
     * ajoute a la scene les deux box
     *
     * @param group            le groupe principal
     * @param parameterBox     le box de gauche
     * @param visualisationBox le box de droite
     */
    private void fillGroup(DeepHBox group, DeepVBox parameterBox, DeepVBox visualisationBox) {
        group.add(Tools.createHExpandableSpacer());
        group.add(parameterBox);
        group.add(Tools.createHExpandableSpacer());
        group.add(visualisationBox);
        group.add(Tools.createHExpandableSpacer());
    }

    /**
     * remplit le box de gauche dedie aux parametres
     *
     * @param parameterBox le box des parametres
     */
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

    /**
     * remplit le cadre Visualisation
     *
     * @param visualisationBox le cadre de visualisation
     */
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

    /**
     * declenchee quand on selectionne un bouton radio
     *
     * @param text le texte du bouton radion selectionne
     */
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

    /**
     * declenchee quand on appuie sur un boutton
     *
     * @param buttonText le texte du bouton appuye
     */
    public void buttonClicked(String buttonText) {
        switch (buttonText) {
            case Constants.START_TRAINING:
                this.startTrainingButtonClicked();
                break;
            case Constants.STOP_TRAINING:
                this.stopTrainingButtonClicked();
                break;
            case Constants.CANCEL_TRAINING:
                this.cancelTraining();
                break;
            case Constants.CHOOSE_AND_DOTS:
                this.chooseCSVFile();
                break;
            default:
        }
    }

    /**
     * declenche quann on change un spinner du cadre architecture
     *
     * @param spinnerText le spinner modifie
     * @param newValue    la nouvelle valeur du spinner
     */
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

    /**
     * declenche l'ouverture du file chooser pour choisir le fichier csv
     */
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

    public void initModelIfNecessary() {
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
            this.iaModel.initModel();
        }
    }

    public void splitDataIfNecessary() throws Exception {
        if (!this.iaModel.dataReady()) {
            this.iaModel.splitData(this.datasetArea.getCsvLoader(),
                    this.datasetArea.getTargetVariableName(),
                    this.datasetArea.getTrainingProportion(),
                    this.datasetArea.getPretreatment());
        }
    }

    /**
     * procede aux verifications avant de lancer l'entrainement du modele
     *
     * @return le statut de la verification
     */
    private boolean checkBeforeTraining() {

        if (this.optimizationArea.getIterationCount() == 0) {
            Tools.inform(Message.ITERATION_COUNT_ZERO);
            return false;
        }

        if (!this.checkTrainingOptions()) {
            Tools.inform(Message.ARCHITECTURE_AND_DATASET_DISCORDANCY);
            return false;
        }

        if (!Tools.checkNumericalTextField(this.datasetArea.getTrainingTextField(), 0, 1)) {
            Tools.inform(Message.WRONG_PROPORTION, Constants.TRAINING);
            this.datasetArea.getTrainingTextField().requestFocus();
            return false;
        }

        if (!Tools.checkNumericalTextField(this.optimizationArea.getParameterTextField(), 0, 1)) {
            Tools.inform(Message.WRONG_PROPORTION, Constants.PARAMETERS);
            this.optimizationArea.getParameterTextField().requestFocus();
            return false;
        }

        return true;
    }

    private void disableControlsDuringTraining() {
        this.buttonArea.getTrainButton().setText(Constants.STOP_TRAINING);
        this.predictionTypeArea.setChildrenDisabled(true);
        this.datasetArea.setChildrenDisabled(true);
        this.architectureArea.setChildrenDisabled(true);
        this.optimizationArea.setChildrenDisabled(true);
        this.buttonArea.getcancelTrainingButton().setDisable(true);
        this.evaluationArea.clear();
    }

    private void enableControlsAfterTraining() {
        this.buttonArea.getTrainButton().setText(Constants.START_TRAINING);
        this.optimizationArea.getIterationSpinner().setDisable(false);
        this.buttonArea.getcancelTrainingButton().setDisable(false);
        this.visualisationArea.stopTrainingAnimation();
    }

    private void startTrainingButtonClicked() {
        this.startTrainingThread();
    }

    private void stopTrainingButtonClicked() {
        this.stopTrainingThread = true;
    }

    /**
     * lance le thread de l'entrainement du modele
     */
    private void startTrainingThread() {
        try {
            if (!checkBeforeTraining()) {
                return;
            }
            //Doit etre en dehors du thread d'entrainement
            this.visualisationArea.startTrainingAnimation();

            this.trainingThread = new Thread(() -> {
                try {
                    Platform.runLater(this::disableControlsDuringTraining);
                    this.initModelIfNecessary();
                    this.splitDataIfNecessary();
                    int interationCount = this.optimizationArea.getIterationCount();

                    for (int ii = 1; ii <= interationCount; ii++) {
                        if (this.stopTrainingThread) break;
                        Thread.sleep(Constants.TRAINING_DELAY);
                        data.Evaluation evaluation = this.iaModel.train();
                        this.optimizationArea.getIterationSpinner().decrement();
                        this.trainingArea.println(evaluation.toStringWithIteration(this.iaModel.getAchievedInterationCount()));
                    }

                    this.evaluateModel();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Platform.runLater(this::enableControlsAfterTraining);
                this.trainingThread = null;
                this.stopTrainingThread = false;
            });

            this.trainingThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            Tools.error(Message.TRAINING_ERROR);
        }
    }

    private void evaluateModel() {
        try {
            Evaluation evaluation = this.iaModel.evaluate();
            this.evaluationArea.clear();
            this.evaluationArea.println(evaluation);
            if (evaluation.getIndicatorValue() < this.iaModel.getAchivedLatestIndicatorValue()) {
                this.evaluationArea.println(Message.OVERFITTING_MODEL);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Tools.error(Message.TRAINING_ERROR);
        }
    }

    private void cancelTraining() {
        Optional<ButtonType> option = Tools.confirm(Message.CANCEL_TRAINING_CONFIRMATION);

        if (option.get() == Constants.DELETE) {
            this.iaModel = null;
            this.trainingArea.clear();
            this.evaluationArea.clear();
            this.predictionTypeArea.setChildrenDisabled(false);
            this.datasetArea.setChildrenDisabled(false);
            this.architectureArea.setChildrenDisabled(false);
            this.optimizationArea.setChildrenDisabled(false);
            this.optimizationArea.setIterationCount(Constants.DEFAULT_ITERATION_COUNT);
            this.buttonArea.getcancelTrainingButton().setDisable(true);
        }
    }
}
