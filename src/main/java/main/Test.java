package main;

import common.Tools;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.common.util.ArrayUtil;
import org.nd4j.common.util.ND4JFileUtils;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

public class Test {
    public static void main(String[] args) {
        try {
//            h();
            MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork("C:\\Users\\charles\\OneDrive\\Workspace_IntelliJ\\DeepLearningGUI\\src\\main\\java\\main\\model.dat");
//            println(model);
            DataSet dataset = (DataSet)Tools.deSerialize("C:\\Users\\charles\\OneDrive\\Workspace_IntelliJ\\DeepLearningGUI\\src\\main\\java\\main\\dataset.dat");
//            println(dataset);
            INDArray x = dataset.getFeatures();
            INDArray y_true = dataset.getLabels();
            INDArray y_pred = model.output(x);
//            println(y_true);
//            println(y_pred);
//            println(y_true.distance1(y_pred));
            println(Nd4j.sum(Nd4j.math.abs((y_true.sub(y_pred)).div(y_true)).div(y_pred.shape()[0])));
            println(Nd4j.sum((y_true.sub(y_pred)).mul(y_true.sub(y_pred))).div(y_pred.shape()[0]));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public static void f() {
//        INDArray x;
//        x = Nd4j.linspace(0, 11, 12).reshape(4, 3);
//        println(x);
//        println(Nd4j.argMax(x, 1));
//    }
//
//    public static void g() {
//        INDArray x, y;
//
//        x = Nd4j.argMax(Nd4j.rand(5, 3), 1);
//        y = Nd4j.argMax(Nd4j.rand(5, 3), 1);
//        println(x);
//        println(y);
////        println(x.add(y));
////        println(x.sub(y));
////        println(x.mul(y));
//        println(x.distance2(y));
//    }

    public static void h() {
        INDArray x, y;

        x = Nd4j.rand(1, 5);
        y = Nd4j.rand(1, 5);
        println(x);
        println(y);
//        println(x.add(y));
//        println(x.sub(y));
        println(x.mul(y));
//        println(x.distance2(y));
//            println(x.div(y));
    }

    public static void println(Object x) {
        System.out.println(x);
    }
}
