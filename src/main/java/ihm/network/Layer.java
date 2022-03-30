package ihm.network;

import java.util.ArrayList;

public class Layer {
    private ArrayList<Neuron> neurons;

    public Layer(){
    }

    public void addNeuron(Neuron neuron){
        this.neurons.add(neuron);
    }
}
