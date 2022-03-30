package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepPane;
import ihm.network.HiddenNeuron;
import ihm.network.InputOuputNeuron;
import ihm.network.NetworkBackgroung;
import javafx.scene.shape.Shape;

public class VisualisationArea extends DeepPane {

    public VisualisationArea() {
        super(true);
        Tools.setSize(this, Constants.DISPLAY_AREA_WIDTH, Constants.VISUALISATION_AREA_HEIGHT);
    }

    public void drawNetwork(int inputCount, int outputCount, int hiddenLayerCount, String activationFunction) {
        //Todo
        this.clear();
        this.drawNeurons(inputCount,outputCount, hiddenLayerCount, activationFunction);
    }

    private void drawNeurons(int inputCount, int outputCount, int hiddenLayerCount, String activationFunction){
        double width = this.getWidth();
        double height = this.getHeight();

        double hspace = width / (hiddenLayerCount + 3);
        double vspace = height / (inputCount + 1);

        double xOffset = hspace;
        double yOffset = vspace;

        for (int hh = 1; hh <= inputCount; hh++){
            this.draw(new InputOuputNeuron(xOffset, yOffset));
            yOffset += vspace;
        }

        for (int ii = 1; ii <= hiddenLayerCount; ii++) {
            vspace = height / (inputCount + 1);
            yOffset = vspace;
            xOffset += hspace;
            for (int hh = 1; hh <= inputCount; hh++){
                this.draw(new HiddenNeuron(xOffset, yOffset));
                yOffset += vspace;
            }
        }

        vspace = height / (outputCount + 1);
        xOffset += hspace;
        yOffset = vspace;

        for (int hh = 1; hh <= outputCount; hh++){
            this.draw(new InputOuputNeuron(xOffset, yOffset));
            yOffset += vspace;
        }
    }
    private void draw(Shape shape) {
        this.getChildren().add(shape);
    }

    public void clear() {
        double padding = Constants.AREA_PADDING;
        this.draw(new NetworkBackgroung(padding, padding, this.getWidth() - 2 * padding, this.getHeight() - 2 * padding));
    }
}

