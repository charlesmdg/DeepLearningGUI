package data;

public class ClassificationEvaluation {
    private double accuracy;
    private double precision;
    private double recall;
    private double f1Score;

    public ClassificationEvaluation(double accuracy, double precision, double recall, double f1Score){
        this.accuracy =accuracy;
        this.precision = precision;
        this.recall = recall;
        this.f1Score = f1Score;
    }

    public double getPrecision() {
        return this.precision;
    }

    public double getRecall() {
        return this.recall;
    }

    public double getF1Score() {
        return this.f1Score;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    @Override
    public String toString() {
        return  "Accuracy : " + accuracy + "\n" +
                "Precision : " + precision + "\n" +
                "Recall : " + recall + "\n" +
                "F1 Score : " + f1Score;
    }

    public String toStringWithIteration(int iteration) {
        return  "Iteration # " + iteration + " : " + accuracy;
    }

}
