package ihm.network;

import java.util.ArrayList;

public class Network extends ArrayList<Layer> {

    public Network() {
        super();
    }

    public static Network createNetwork(int inputCount, int outputCount,
                                        int hiddenLayerCount, ArrayList<Integer> hiddenLayerNeuronCounts) {
        Network network = new Network();

        network.add(Layer.createInputOutputLayer(inputCount));

        for (int ii = 0; ii < hiddenLayerCount; ii++) {
            network.add(Layer.createHiddenLayer(hiddenLayerNeuronCounts.get(ii)));
        }
        network.add(Layer.createInputOutputLayer(outputCount));

        return network;
    }


    public void pack(double width, double height) {
        // ecart horizontal entre les couches
        double hspace = width / (this.size() + 1);

        //Nombre de couches
        int layerCount = this.size();

        for (int ii = 0; ii < layerCount; ii++) {
            Layer layer = this.get(ii);

            //Nombre de neurone dans la couche
            int layerNeuronCount = layer.size();

            // ecart vertical entre les neurones
            Double vspace = height / (layerNeuronCount + 1);

            for (int jj = 0; jj < layerNeuronCount; jj++) {
                Neuron neuron = layer.get(jj);
                neuron.setCenterX((ii + 1) * hspace);
                neuron.setCenterY((jj + 1) * vspace);
            }
        }
    }
}
