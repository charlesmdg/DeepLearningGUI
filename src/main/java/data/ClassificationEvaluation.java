package data;

import common.Constants;
import common.Tools;

public class ClassificationEvaluation extends Evaluation {
    private final double accuracy;
    private final double precision;
    private final double recall;
    private final double f1Score;

    public ClassificationEvaluation(double accuracy, double precision, double recall, double f1Score) {
        super();
        this.accuracy = accuracy;
        this.precision = precision;
        this.recall = recall;
        this.f1Score = f1Score;
    }

    @Override
    public String toString() {
        return Constants.ACCURACY + Constants.SPACED_COLON + Tools.stringFormatIndicator(this.accuracy) + Constants.CR +
                Constants.PRECISION + Constants.SPACED_COLON + Tools.stringFormatIndicator(this.precision) + Constants.CR +
                Constants.RECALL + Constants.SPACED_COLON + Tools.stringFormatIndicator(this.recall) + Constants.CR +
                Constants.F1SCORE + Constants.SPACED_COLON + Tools.stringFormatIndicator(this.f1Score);
    }

    public String toStringWithIteration(int iteration) {
        return Constants.TRAINING_DISPLAY_INTRODUCTION + iteration
                + Constants.SPACED_COLON + Tools.stringFormatIndicator(this.accuracy);
    }

    @Override
    public double getIndicatorValue() {
        return this.accuracy;
    }
}
