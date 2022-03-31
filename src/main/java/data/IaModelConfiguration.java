package data;

import common.Constants;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class IaModelConfiguration {
    private final MultiLayerConfiguration configuration;

    public IaModelConfiguration(String predictionType,
                                int inputCount, int outputCount, int hiddenLayerCount,
                                String activationFunction, String lossFunction, String optimizer,
                                double learningRate) {

        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();

        Activation activation = this.createActivation(activationFunction);

        this.configuration = builder.activation(activation)
                .updater(this.createOptimiser(optimizer, learningRate))
                .list()
                .layer(new DenseLayer.Builder().nIn(inputCount).nOut(inputCount)
                        .build())
                .layer(new DenseLayer.Builder().nIn(inputCount).nOut(inputCount)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(inputCount).nOut(outputCount).build())
                .build();
    }


    private Activation createActivation(String activationFunction) {
        Activation activation;

        switch (activationFunction) {
            case Constants.RELU:
                activation = Activation.RELU;
                break;
            case Constants.SIGMOID:
                activation = Activation.SIGMOID;
                break;
            case Constants.TANH:
                activation = Activation.TANH;
                break;
            case Constants.SOFTMAX:
                activation = Activation.SOFTMAX;
                break;
            case Constants.SOFTPLUS:
                activation = Activation.SOFTPLUS;
                break;
            case Constants.IDENTITY:
                activation = Activation.IDENTITY;
                break;
            default:
                activation = null;
        }
        return activation;
    }

    private Sgd createOptimiser(String optimizer, double learningRate) {
        return new Sgd(learningRate);
    }

}
