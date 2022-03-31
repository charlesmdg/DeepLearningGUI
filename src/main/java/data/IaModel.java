package data;

import common.Constants;
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
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;

public class IaModel {
    MultiLayerNetwork model;
    int inputCount;
    int outptCount;
    String predictionType;
    CsvLoader csvLoader;

    public IaModel(String predictionType,
                   int inputCount, int outputCount, int hiddenLayerCount,
                   String activationFunction, String lossFunction, String optimizer,
                   double learningRate){

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

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
    }

    //todo
    public void train(int iterationCount, String csvFilePath, double trainingProportion) throws Exception{
        int numLinesToSkip = 1;
        char delimiter = ',';
        RecordReader recordReader = new CSVRecordReader(numLinesToSkip,delimiter);
        recordReader.initialize(new FileSplit(new File(csvFilePath)));

        int numClasses = this.outptCount;
        int labelIndex = this.inputCount;
        int batchSize = 150;
        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader,batchSize,labelIndex,numClasses);
        DataSet allData = iterator.next();
        allData.shuffle();
        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);  //Use 65% of data for training

        DataSet trainingData = testAndTrain.getTrain();
        DataSet testData = testAndTrain.getTest();

        //We need to normalize our data. We'll use NormalizeStandardize (which gives us mean 0, unit variance):
        DataNormalization normalizer = new NormalizerStandardize();
        normalizer.fit(trainingData);           //Collect the statistics (mean/stdev) from the training data. This does not modify the input data
        normalizer.transform(trainingData);     //Apply normalization to the training data
        normalizer.transform(testData);         //Apply normalization to the test data. This is using statistics calculated from the *training* set

//
//        final int numInputs = 4;
//        int outputNum = 3;
//        long seed = 6;
//
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .seed(seed)
//                .activation(Activation.TANH)
//                .weightInit(WeightInit.XAVIER)
//                .updater(new Sgd(0.1))
//                .l2(1e-4)
//                .list()
//                .layer(new DenseLayer.Builder().nIn(numInputs).nOut(3)
//                        .build())
//                .layer(new DenseLayer.Builder().nIn(3).nOut(3)
//                        .build())
//                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
//                        .activation(Activation.SOFTMAX) //Override the global TANH activation with softmax for this layer
//                        .nIn(3).nOut(outputNum).build())
//                .build();
//
//        //run the model
//        MultiLayerNetwork model = new MultiLayerNetwork(conf);
//        model.init();
//        //record score once every 100 iterations
//        model.setListeners(new ScoreIterationListener(10));
//
//        for(int i=0; i<1000; i++ ) {
//            model.fit(trainingData);
//        }
//
    }
}
