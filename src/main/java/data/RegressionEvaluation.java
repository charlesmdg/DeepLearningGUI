package data;

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
        return "Iteration # " + iteration + " : " + this.mape;
    }

}
