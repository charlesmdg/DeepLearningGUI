package ihm.network;

import java.util.ArrayList;

public class Layer extends ArrayList<Neuron> {

    public Layer() {
        super();
    }

    public static Layer createInputOutputLayer(int neuronCount) {
        Layer layer = new Layer();

        for (int ii = 0; ii < neuronCount; ii++)
            layer.add(new InputOuputNeuron());

        return layer;
    }

    public static Layer createHiddenLayer(int neuronCount) {
        Layer layer = new Layer();

        for (int ii = 0; ii < neuronCount; ii++)
            layer.add(new HiddenNeuron());

        return layer;
    }

}
