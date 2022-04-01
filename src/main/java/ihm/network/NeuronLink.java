package ihm.network;

import common.Constants;
import javafx.scene.shape.Line;

public class NeuronLink extends Line {
    public NeuronLink(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
        this.setStroke(Constants.NEURON_LINK_COLOR);
    }
}
