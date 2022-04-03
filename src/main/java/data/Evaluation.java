package data;

import org.datavec.api.transform.transform.parse.ParseDoubleTransform;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public abstract class Evaluation {
    public Evaluation(){
    }

    public abstract String toStringWithIteration(int iteration);
    public abstract double getIndicatorValue();
    
    public static double mse(INDArray yTrue, INDArray yPred){
        try {
            return Double.parseDouble(String.valueOf(Nd4j.sum((yTrue.sub(yPred)).mul(yTrue.sub(yPred))).div(yTrue.shape()[0])));
        } catch (Exception e){
            e.printStackTrace();
            return Double.MAX_VALUE;
        }
    }
    public static double mae(INDArray yTrue, INDArray yPred){
        try {
            return Double.parseDouble(String.valueOf(Nd4j.sum(Nd4j.math.abs(yTrue.sub(yPred)).div(yTrue.shape()[0]))));
        } catch (Exception e){
            e.printStackTrace();
            return Double.MAX_VALUE;
        }
    }

    public static double mape(INDArray yTrue, INDArray yPred){
        try {
            return Double.parseDouble(String.valueOf(Nd4j.sum(Nd4j.math.abs((yTrue.sub(yPred)).div(yTrue)).div(yTrue.shape()[0]))));
        } catch (Exception e){
            e.printStackTrace();
            return Double.MAX_VALUE;
        }
    }
}
