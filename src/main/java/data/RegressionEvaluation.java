package data;

import common.Constants;

public class RegressionEvaluation extends Evaluation{
    private double mse;
    private double mape;

    public RegressionEvaluation(){
    }

    public double getMse() {
        return mse;
    }

    public double getMape() {
        return mape;
    }
    public String toStringWithIteration(int iteration) {
        return Constants.TRAINING_DISPLAY_INTRODUCTION + iteration + Constants.SPACED_COLON + this.mape;
    }

    @Override
    public double getIndicatorValue() {
        return 0;
    }

}
