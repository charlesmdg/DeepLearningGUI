package ihm.network;

import common.Constants;

public class HiddenNeuron extends Neuron{
    public HiddenNeuron(){
        super(0, 0, Constants.HIDDEN_NEURON_FILL_COLOR);
        this.setFill(Constants.HIDDEN_NEURON_FILL_COLOR);
    }

    public HiddenNeuron(double x, double y){
        super(x, y, Constants.HIDDEN_NEURON_FILL_COLOR);
        this.setFill(Constants.HIDDEN_NEURON_FILL_COLOR);
    }

}
