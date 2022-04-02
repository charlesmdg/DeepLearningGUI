package ihm.network;

import common.Constants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Neuron extends Circle {
    public Neuron(double x, double y, Color fillColor){
        super(x, y, Constants.NEURON_RADIUS);
        this.setFill(fillColor);
    }
}
