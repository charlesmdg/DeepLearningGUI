package data;

import common.Constants;

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
        return Constants.ACCURACY + " : " + this.accuracy + "\n" +
                Constants.PRECISION + " : " + this.precision + "\n" +
                Constants.RECALL + " : " + this.recall + "\n" +
                Constants.F1SCORE + " : " + this.f1Score;
    }

    public String toStringWithIteration(int iteration) {
        return "Iteration # " + iteration + " : " + this.accuracy;
    }
}
