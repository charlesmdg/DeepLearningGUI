package ihm.network;

import common.Constants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Neuron extends Circle {
    public Neuron(double x, double y, Color fillColor, Color strokeColor){
        super(x, y, Constants.NEURON_RADIUS);
        this.setFill(fillColor);
        this.setStroke(strokeColor);
        this.setStrokeWidth(Constants.NEURON_STROKE_WIDTH);
    }
}
