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
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;

public class IaModel {
    private MultiLayerNetwork model;
    private int inputCount;
    private int outptCount;
    private String predictionType;

    private DataSet trainingData;
    private DataSet evaluationData;

    private Thread trainingThead;

    public IaModel(String predictionType,
                   int inputCount, int outputCount, int hiddenLayerCount,
                   String activationFunction, String lossFunction, String optimizer,
                   double learningRate) {

        this.predictionType = predictionType;
        this.inputCount = inputCount;
        this.outptCount = outputCount;

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .activation(Activation.TANH)
                .weightInit(WeightInit.XAVIER)
                .updater(new Sgd(learningRate))
                .l2(1e-4)
                .list()
                .layer(new DenseLayer.Builder().nIn(inputCount).nOut(inputCount)
                        .build())
                .layer(new DenseLayer.Builder().nIn(inputCount).nOut(inputCount)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX) //Override the global TANH activation with softmax for this layer
                        .nIn(inputCount).nOut(outputCount).build())
                .build();

        this.model = new MultiLayerNetwork(conf);
        this.model.init();

        this.trainingThead = new Thread(() -> {System.out.println("U");});
    }

    //todo
    public void splitData(CsvLoader csvLoader, String targetVariableName, double trainingProportion,
                          String pretreatment) throws Exception {
        int numLinesToSkip = 1;
        char delimiter = ',';

        int numClasses = this.outptCount;
        int labelIndex = csvLoader.getDataset().getColumnIndex(targetVariableName);

        RecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
        String filePath = csvLoader.getFilePath();
        recordReader.initialize(new FileSplit(new File(filePath)));
        int batchSize = Tools.filelineCount(filePath);

        //Melange et eclatement du jeu de donnees en jeux d'entrainement et de validation
        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
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
        }
    }

    public boolean dataReady() {
        return this.trainingData != null && this.evaluationData != null;
    }

    public void train(int iterationCount) throws Exception {
        this.model.setListeners(new ScoreIterationListener(1));

        for (int i = 0; i < iterationCount; i++) {
            Thread.sleep(10);
            this.model.fit(trainingData);
        }
    }

    public ClassificationEvaluation train1() throws Exception {
        this.train(1);

        return this.evaluate(this.trainingData);
    }

    public ClassificationEvaluation evaluate() {
        return this.evaluate(this.trainingData);
    }

    private ClassificationEvaluation evaluate(DataSet dataset) {
        Evaluation eval = new Evaluation(this.outptCount);
        INDArray output = this.model.output(dataset.getFeatures());
        eval.eval(dataset.getLabels(), output);
        return new ClassificationEvaluation(eval.accuracy(), eval.precision(),
                eval.recall(), eval.f1());
    }

}
