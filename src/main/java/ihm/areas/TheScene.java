package ihm.areas;

import common.Constants;
import data.CsvLoader;
import common.Message;
import common.Tools;
import data.IaModel;
import ihm.controls.DeepHBox;
import ihm.controls.DeepVBox;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TheScene extends Scene {

    private final PredictionTypeArea predictionTypeArea = new PredictionTypeArea(this);
    private final DatasetArea datasetArea = new DatasetArea(this);
    private final ArchitectureArea architectureArea = new ArchitectureArea();
    private final OptimizationArea optimizationArea = new OptimizationArea();
    private final VisualisationArea visualisationArea = new VisualisationArea();
    private final TrainingArea trainingArea = new TrainingArea();
    private final EvaluationArea evaluationArea = new EvaluationArea();
    private Stage stage;
    private IaModel iaModel;


    public TheScene(DeepHBox group) {
        super(group, Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT);
        ButtonArea buttonArea = new ButtonArea(this);

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
        parameterBox.getChildren().add(buttonArea.getBox());
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

    private void chooseCSVFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Constants.FILE_CHOOSER_TITLE);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Constants.CSV, Constants.STAR_DOT_CSV),
                new FileChooser.ExtensionFilter(Constants.ALL, Constants.STAR_DOT_STAR));
        File file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            String filePath = file.getPath();
            CsvLoader csvLoader = new CsvLoader(filePath);
            if (csvLoader.check()) {
                this.fillDatasetArea(file, csvLoader);
                this.fillArchitectureArea(csvLoader);
            }
        }
    }

    private void fillDatasetArea(File file, CsvLoader csvLoader) {
        this.datasetArea.setCsvFile(file.getPath());
        this.datasetArea.setTargetVariableComboBoxItemList(csvLoader.getColumnNames());
        String[] columnNames = csvLoader.getColumnNames();
        //A ce stade, apres le csvLoader.check columnNames.length >=2
        this.datasetArea.setTargetVariable(columnNames[columnNames.length - 1]);
        this.datasetArea.setTraining(Constants.DEFAULT_TRAINING);
        this.datasetArea.setPretreatment(Constants.STANDARD_SCALER);
    }

    private void fillArchitectureArea(CsvLoader csvLoader) {
        int inputCount = csvLoader.getDataset().getColumnNames().length - 1;
        int outputCount;
        int hiddenLayerCount = Constants.DEFAULT_HIDDEN_LAYER_COUNT;
        String activationFunction;
        String predictionType = this.predictionTypeArea.getPredictionType();

        switch (predictionType) {
            case Constants.CLASSIFICATION:
                activationFunction = Constants.TANH;

                String targetVariable = this.datasetArea.getTargetVariable();

                outputCount = csvLoader.getDataset().getValueCount(targetVariable);
                if (outputCount > Constants.DIFFERENT_VALUE_MAX_COUNT) {
                    Tools.error(Message.TOO_MANY_DIFFERENT_VALUES);
                }
                break;
            case Constants.REGRESSION:
                activationFunction = Constants.RELU;
                outputCount = 1;
                break;
            default:
                outputCount = Constants.DEFAULT_OUTPUT_COUNT;
                activationFunction = Constants.ACTIVATION_FUNCTION;
        }

        this.architectureArea.setInputCount(inputCount);
        this.architectureArea.setOutputCount(outputCount);
        this.architectureArea.sethiddenLayerCount(hiddenLayerCount);
        this.architectureArea.setActivationFunction(activationFunction);
    }

    private void visualizeButtonClicked() {
        this.visualisationArea.drawNetwork(this.architectureArea.getInputCount(),
                this.architectureArea.getOutputCount(),
                this.architectureArea.getHiddenLayerCount(),
                this.architectureArea.getActivationFunction());
    }

    private void trainButtonClicked() {
        //Todo
        if(iaModel == null){
            this.iaModel = new IaModel(this.predictionTypeArea.getPredictionType(),
                                        this.architectureArea.getInputCount(),
                    this.architectureArea.getOutputCount(),
                    this.architectureArea.getHiddenLayerCount(),
                    this.architectureArea.getActivationFunction(),
                    this.optimizationArea.getLossFunction(),
                    this.optimizationArea.getOptimizer(),
                    this.optimizationArea.getLearningRate());
        }


        try {
            this.iaModel.train(this.optimizationArea.getIterationCount(),
                                this.datasetArea.getCsvFilePath(),
                                this.datasetArea.getT);
            this.trainingArea.println("Start training for " + this.optimizationArea.getIterationCount() + " iterations.");
        } catch (Exception e) {
            Tools.error(Message.TRAINING_ERROR);
        }
    }

    private void evaluateButtonClicked() {
        //Todo
        this.evaluationArea.println("evaluateButtonClicked");
    }
}
