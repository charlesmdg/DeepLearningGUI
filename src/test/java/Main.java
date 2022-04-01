import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.LossFunction;
import org.nd4j.linalg.api.ops.impl.loss.MeanSquaredErrorLoss;
import org.nd4j.linalg.factory.Nd4j;

public class Main {
    public static void main(String[] args) {
//        String[] strings1 = {"56.", ".6876", "78.676"};
//        checkStrings(strings1);
//
//        String[] strings2 = {"5.6.", ".68s76", ".78676."};
//        checkStrings(strings2);
//
//        String[] strings3 = {"-56", "-.6876", "-78.676"};
//        checkStrings(strings3);
//
//        String[] strings4 = {"-5-6", ".6876-", "786-76"};
//        checkStrings(strings4);
        INDArray x =  Nd4j.linspace(1, 4, 4);
        INDArray y =  Nd4j.linspace(-2, 1, 4);
        System.out.println(x);
        System.out.println(y);
        System.out.println(x.add(y));
        System.out.println(x.sub(y));
        System.out.println(x.mmul(y));
        System.out.println(x.mul(y));
        System.out.println(x.sum());
        MeanSquaredErrorLoss m = new MeanSquaredErrorLoss();
    }

    public static void checkStrings(String[] strings){
        String regex = "(^-?[0-9]+$|^-?\\.[0-9]+$|^-?[0-9]+\\.[0-9]*$)";

        for (String string: strings) {
            println(string + " : "+ string.matches(regex));
        }
    }

    public static void println(String text){
        System.out.println(text);
    }
}
