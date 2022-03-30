package ihm.network;

import common.Constants;
import javafx.scene.paint.Color;

public class HiddenNeuron extends Neuron{
    public HiddenNeuron(double x, double y){
        super(x, y, Constants.HIDDEN_NEURON_FILL_COLOR, Constants.HIDDEN_NEURON_STROKE_COLOR);
        this.setFill(Constants.HIDDEN_NEURON_FILL_COLOR);
    }
}
