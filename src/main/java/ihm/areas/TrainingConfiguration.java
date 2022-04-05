package ihm.areas;

import java.io.Serializable;

public class TrainingConfiguration implements Serializable {
    private final String predictionType;
    private final String csvFilePath;
    private final String targetVariableName;
    private final double trainingSplit;
    private final String pretreatement;
    private final int inputCount;
    private final int outputCount;
    private final int hiddenLayerCount;
    private final String activationFunction;
    private final String lossFunction;
    private final String optimizer;
    private final double learningRate;
    private final int iterationCount;

    public TrainingConfiguration(String predictionType, String csvFilePath, String targetVariableName,
                                 double trainingSplit, String pretreatement,
                                 int inputCount, int outputCount, int hiddenLayerCount, String activationFunction,
                                 String lossFunction,
                                 String optimizer, double learningRate, int iterationCount) {
        this.predictionType = predictionType;
        this.csvFilePath = csvFilePath;
        this.targetVariableName = targetVariableName;
        this.trainingSplit = trainingSplit;
        this.pretreatement = pretreatement;
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.hiddenLayerCount = hiddenLayerCount;
        this.activationFunction = activationFunction;
        this.lossFunction = lossFunction;
        this.optimizer = optimizer;
        this.learningRate = learningRate;
        this.iterationCount = iterationCount;
    }

    public String getCsvFilePath() {
        return this.csvFilePath;
    }

    public String getTargetVariableName() {
        return this.targetVariableName;
    }

    public double getTrainingSplit() {
        return this.trainingSplit;
    }

    public String getPretreatement() {
        return this.pretreatement;
    }

    public int getInputCount() {
        return this.inputCount;
    }

    public int getOutputCount() {
        return this.outputCount;
    }

    public int getHiddenLayerCount() {
        return this.hiddenLayerCount;
    }

    public String getActivationFunction() {
        return this.activationFunction;
    }

    public String getLossFunction() {
        return this.lossFunction;
    }

    public String getOptimizer() {
        return this.optimizer;
    }

    public double getLearningRate() {
        return this.learningRate;
    }

    public int getIterationCount() {
        return this.iterationCount;
    }

    public String getPredictionType() {
        return this.predictionType;
    }
}




