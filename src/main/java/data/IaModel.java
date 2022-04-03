package data;

import common.Constants;
import common.Tools;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerMinMaxScaler;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.*;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.io.Serializable;

public class IaModel implements Serializable {
    private MultiLayerNetwork model;
    private DataSet trainingData;
    private DataSet evaluationData;
    private final String predictionType;
    private final int inputCount;
    private final int outputCount;
    private final int hiddenLayerCount;
    private final String activationFunction;
    private final String lossFunction;
    private final String optimizer;
    private final double learningRate;
    private int achievedInterationCount = 0;
    private double achivedLatestIndicatorValue = Constants.IMPOSSIBLE_INDEX;

    public IaModel(String predictionType,
                   int inputCount, int outputCount, int hiddenLayerCount,
                   String activationFunction, String lossFunction, String optimizer,
                   double learningRate) {

        this.predictionType = predictionType;
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.hiddenLayerCount = hiddenLayerCount;
        this.activationFunction = activationFunction;
        this.lossFunction = lossFunction;
        this.learningRate = learningRate;
        this.optimizer = optimizer;
        this.initModel();
    }

    /**
     * initialise le modele d'ia de la librairie Deeplearning4J
     */
    public void initModel() {
        Activation hiddenLayerActivation = this.createHiddenLayerActivation(this.activationFunction);
        Activation outputLayerActivation = this.createOutputLayerActivation(this.predictionType);
        LossFunctions.LossFunction loss = this.createLossFunction(this.lossFunction);

        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder().activation(hiddenLayerActivation);

        builder = this.addOptimizer(builder, this.learningRate, this.optimizer);

        NeuralNetConfiguration.ListBuilder listBuilder = builder.list();

        for (int ii = 0; ii < this.hiddenLayerCount; ii++) {
            listBuilder = listBuilder.layer(new DenseLayer.Builder().nIn(this.inputCount).nOut(this.inputCount).build());
        }

        MultiLayerConfiguration conf = listBuilder
                .layer(new OutputLayer.Builder(loss)
                        .activation(outputLayerActivation)
                        .nIn(this.inputCount).nOut(this.outputCount).build()).build();

        this.model = new MultiLayerNetwork(conf);
        this.model.setListeners(new ScoreIterationListener(1));
        this.model.init();
        this.achievedInterationCount = 0;
    }

    /**
     * construit l'objet de configuration du modele d'ia de la librairie Deeplearning4J
     * @param builder la structure de l'objet de configuraition
     * @param learningRate le taux d'apprentissage de l'algorithme d'optimisation
     * @param optimizer l'algorithme d'optimisation choisi
     * @return l'objet d'optimisation pour le modele
     */
    private NeuralNetConfiguration.Builder addOptimizer(NeuralNetConfiguration.Builder builder,
                                                        double learningRate,
                                                        String optimizer) {
        switch (optimizer) {
            case Constants.STOCHASTIC_GRADIENT:
                builder = builder.updater(new Sgd(learningRate));
                break;
            case Constants.ADAM:
                builder = builder.updater(new Adam(learningRate));
                break;
            case Constants.NADAM:
                builder = builder.updater(new Nadam(learningRate));
                break;
            case Constants.NESTEROV:
                builder = builder.updater(new Nesterovs(learningRate));
                break;
            default:
                builder = builder.updater(new AdaMax(learningRate));
        }
        return builder;
    }

    /**
     * cree les couches cachees du modele
     * @param activationFunction la fonction d'activation du modele
     * @return la fonction d'activation
     */
    private Activation createHiddenLayerActivation(String activationFunction) {
        Activation activation;

        switch (activationFunction) {
            case Constants.RELU:
                activation = Activation.RELU;
                break;
            case Constants.SIGMOID:
                activation = Activation.SIGMOID;
                break;
            case Constants.SOFTMAX:
                activation = Activation.SOFTMAX;
                break;
            case Constants.SOFTPLUS:
                activation = Activation.SOFTPLUS;
                break;
            case Constants.TANH:
                activation = Activation.TANH;
                break;
            default:
                activation = Activation.IDENTITY;
        }

        return activation;
    }

    private Activation createOutputLayerActivation(String predictionType) {
        Activation activation;

        if (predictionType.equals(Constants.CLASSIFICATION)) {
            activation = Activation.SOFTMAX;
        } else {
            activation = Activation.IDENTITY;
        }

        return activation;
    }

    private LossFunctions.LossFunction createLossFunction(String lossFunction) {
        LossFunctions.LossFunction loss;

        switch (lossFunction) {
            case Constants.MEAN_SQUARED_ERROR:
                loss = LossFunctions.LossFunction.SQUARED_LOSS;
                break;
            case Constants.NEGATIVE_LOG_LIKELIHOOD:
                loss = LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD;
                break;
            case Constants.HINGE_ERROR:
                loss = LossFunctions.LossFunction.HINGE;
                break;
            case Constants.SQUARED_HINGE_ERROR:
                loss = LossFunctions.LossFunction.SQUARED_HINGE;
                break;
            default:
                loss = null;
        }

        return loss;
    }

    /**
     * repartit le jeu de donnees en jeu d'entrainement et jeu d'evaluation
     * @param csvLoader le loader de fichier csn
     * @param targetVariableName le nom de la variable cible
     * @param trainingProportion la proportion du jeu d'entrainement a consacrer a l'entrainemnent
     * @param pretreatment l'algorithme de pretraitement des donnees
     * @throws Exception declenchee quand la lecture du fichier csv pose probleme
     */
    public void splitData(CsvLoader csvLoader, String targetVariableName, double trainingProportion,
                          String pretreatment) throws Exception {
        int numLinesToSkip = 1;
        char delimiter = ',';

        int labelIndex = csvLoader.getDataset().getColumnIndex(targetVariableName);

        RecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
        String filePath = csvLoader.getFilePath();
        recordReader.initialize(new FileSplit(new File(filePath)));
        int batchSize = Tools.filelineCount(filePath);

        //Melange et eclatement du jeu de donnees en jeux d'entrainement et de validation
        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, this.outputCount);
        DataSet allData = iterator.next();
        allData.shuffle();
        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(trainingProportion);

        this.trainingData = testAndTrain.getTrain();
        this.evaluationData = testAndTrain.getTest();

        if (pretreatment.equals(Constants.STANDARD_SCALER)) {
            DataNormalization normalizer = new NormalizerStandardize();
            normalizer.fit(trainingData);
            normalizer.transform(trainingData);
            normalizer.transform(this.evaluationData);
        } else if (pretreatment.equals(Constants.MIN_MAX_SCALER)) {
            DataNormalization normalizer = new NormalizerMinMaxScaler();
            normalizer.fit(trainingData);
            normalizer.transform(trainingData);
            normalizer.transform(this.evaluationData);
        }
    }

    public double getAchivedLatestIndicatorValue() {
        return achivedLatestIndicatorValue;
    }

    public boolean dataReady() {
        return this.trainingData != null && this.evaluationData != null;
    }

    public int getAchievedInterationCount() {
        return this.achievedInterationCount;
    }

    public boolean modelReady() {
        return this.model != null;
    }


    /**
     * effectue une iteration de l'entrainement du modele sur tout le jeu d'entrainement
     * @return l'objet qui regroupe les indicateurs de qualite des predictions
     */
    public data.Evaluation train() {
        this.model.fit(trainingData);
        this.achievedInterationCount++;
        data.Evaluation evaluation = this.evaluate(this.trainingData);
        this.achivedLatestIndicatorValue = evaluation.getIndicatorValue();
        return evaluation;
    }

    /**
     * lance l'evaluation du modele sur le jeu d'evaluation
     * @return l'objet qui regroupe les indicateurs de qualite des predictions
     */
    public data.Evaluation evaluate() {
        return this.evaluate(this.evaluationData);
    }

    private data.Evaluation evaluate(DataSet dataset) {
        Evaluation eval = new Evaluation(this.outputCount);
        INDArray output = this.model.output(dataset.getFeatures());

        if (this.predictionType.equals(Constants.CLASSIFICATION)) {
            eval.eval(dataset.getLabels(), output);
            return new data.ClassificationEvaluation(eval.accuracy(), eval.precision(),
                    eval.recall(), eval.f1());
        } else {
            double mse = data.Evaluation.mse(dataset.getLabels(), output);
            double mae = data.Evaluation.mae(dataset.getLabels(), output);
            double mape = data.Evaluation.mape(dataset.getLabels(), output);
            return new data.RegressionEvaluation(mae, mse, mape);
        }
    }

}
