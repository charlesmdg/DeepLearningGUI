package ihm.network;

import common.Constants;

public class InputOuputNeuron extends Neuron{
    public InputOuputNeuron(){
        super(0, 0, Constants.INPUT_OUPUT_NEURON_FILL_COLOR, Constants.INPUT_OUPUT_NEURON_STROKE_COLOR);
        this.setFill(Constants.INPUT_OUPUT_NEURON_FILL_COLOR);
    }

    public InputOuputNeuron(double x, double y){
        super(x, y, Constants.INPUT_OUPUT_NEURON_FILL_COLOR, Constants.INPUT_OUPUT_NEURON_STROKE_COLOR);
        this.setFill(Constants.INPUT_OUPUT_NEURON_FILL_COLOR);
    }
}
