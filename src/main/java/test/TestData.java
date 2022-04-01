package test;

import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class TestData {

    public static void main(String[] args) {
        g();
    }

    public static void f() {
        INDArray x;
        x = Nd4j.linspace(0, 11, 12).reshape(4, 3);
        println(x);
        println(Nd4j.argMax(x, 1));
    }

    public static void g() {
        INDArray x, y;

        x = Nd4j.argMax(Nd4j.rand(5, 3), 1);
        y = Nd4j.argMax(Nd4j.rand(5, 3), 1);
        Evaluation eval = new Evaluation(3);
        eval.eval(x, y);
        println(x);
        println(y);
    }

    public static void println(Object x) {
        System.out.println(x);
    }
}
