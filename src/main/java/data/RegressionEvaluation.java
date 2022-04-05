package data;

import common.Constants;
import common.Tools;

public class RegressionEvaluation extends Evaluation {
    private final double mse;
    private final double mae;
    private final double mape;

    public RegressionEvaluation(double mse, double mae, double mape) {
        this.mse = mse;
        this.mae = mae;
        this.mape = mape;
    }

    @Override
    public String toString() {
        return Constants.MEAN_SQUARED_ERROR_ + Constants.SPACED_COLON +
                Tools.stringFormatAbsoluteIndicator(this.mse) + Constants.CR +
                Constants.MEAN_ABSOLUTE_ERROR + Constants.SPACED_COLON +
                Tools.stringFormatAbsoluteIndicator(this.mae) + Constants.CR +
                Constants.MEAN_ABSOLUTE_PRECENTAGE_ERROR + Constants.SPACED_COLON +
                Tools.stringFormatRelativeIndicator(this.mape) + Constants.CR;
    }

    public String toStringWithIteration(int iteration) {
        return Constants.REGRESSION_TRAINING_DISPLAY_INTRODUCTION + iteration + Constants.SPACED_COLON +
                                Tools.stringFormatRelativeIndicator(this.mape);
    }

    @Override
    public double getIndicatorValue() {
        return this.mape;
    }
}
