package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepPane;
import ihm.controls.DeepTitleLabel;
import ihm.controls.DeepVBox;
import ihm.network.*;
import javafx.geometry.Insets;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VisualisationArea extends DeepPane {
    private final DeepPane drawPane = new DeepPane(true);

    private int inputCount;
    private int outputCount;
    private int hiddenLayerCount;

    //todo
    private Timer blinkingTimer;

    private boolean isNetworkDrawn = false;

    public Network network;

    public VisualisationArea() {
        super(true);
        Tools.setSize(this, Constants.DISPLAY_AREA_WIDTH, Constants.VISUALIZATION_AREA_HEIGHT);

        DeepVBox box = new DeepVBox(false);
        box.setPadding(new Insets(Constants.AREA_PADDING));
        this.getChildren().add(box);

        box.getChildren().add(new DeepTitleLabel(Constants.VISUALIZATION));

        box.getChildren().add(this.drawPane);

        Tools.setSize(this.drawPane, Constants.DISPLAY_AREA_WIDTH - 2 * Constants.AREA_PADDING,
                Constants.VISUALIZATION_AREA_HEIGHT - 2 * Constants.AREA_PADDING - Constants.DISPLAY_AREA_TOP_MARGIN);

        Tools.addClearBorder(this.drawPane);

        //todo
        this.blinkingTimer = new Timer();
    }

    public void draw(Shape shape) {
        this.drawPane.draw(shape);
    }

    public void drawNetwork(int inputCount, int outputCount, int hiddenLayerCount) {
        this.clear();

        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.hiddenLayerCount = hiddenLayerCount;

        this.initizeNetwork(inputCount, outputCount, hiddenLayerCount);

        for (Layer layer : this.network) {
            this.drawLayer(layer);
        }

        this.drawLayerLinks(this.network);

        this.isNetworkDrawn = true;
    }

    private void initizeNetwork(int inputCount, int outputCount, int hiddenLayerCount) {
        //Tous les neurones des couches cachees ont
        //le meme neme nombre de neurones que la couche d'entree
        ArrayList<Integer> hiddenLayerNeuronCounts = new ArrayList<>();

        for (int ii = 0; ii < hiddenLayerCount; ii++) {
            hiddenLayerNeuronCounts.add(inputCount);
        }

        this.network = Network.createNetwork(inputCount, outputCount, hiddenLayerCount, hiddenLayerNeuronCounts);
        this.network.pack(this.drawPane.getWidth(), this.drawPane.getHeight());
    }

    private void drawLayer(Layer layer) {
        for (Neuron neuron : layer) {
            this.draw(neuron);
        }
    }

    private void drawLayerLinks(Network network) {
        for (int ii = 0; ii < network.size() - 1; ii++) {
            this.drawLayerLinks(network.get(ii), network.get(ii + 1));
        }
    }

    private void drawLayerLinks(Layer downstreamLayer, Layer upstreamLayer) {

        for (Neuron downstreamNeuron : downstreamLayer) {
            for (Neuron uptreamNeuron : upstreamLayer) {
                this.draw(new NeuronLink(
                        downstreamNeuron.getCenterX() + downstreamNeuron.getRadius(),
                        downstreamNeuron.getCenterY(),
                        uptreamNeuron.getCenterX() - downstreamNeuron.getRadius(),
                        uptreamNeuron.getCenterY()));
            }
        }
    }


    public void clear() {
        //decalage de 1 pixel pour ne pas faire disparaitre le cadre gris
        //de la zone de dessin
        this.draw(new NetworkBackgroung(1, 1,
                this.drawPane.getWidth() - 2, this.drawPane.getHeight() - 2));
        this.isNetworkDrawn = false;

    }

    //todo
    private void commute() {
        if (this.isNetworkDrawn) {
            this.clear();
        } else {
            this.drawNetwork(this.inputCount,
                            this.outputCount,
                            this.hiddenLayerCount);
        }
    }

    //todo
    public void startBlinking() {
        this.blinkingTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                VisualisationArea.this.commute();
            }
        }, 0, 1000);
    }

    //todo
    public void stopBlinking() {
        this.blinkingTimer.cancel();

        this.drawNetwork(this.inputCount,
                this.outputCount,
                this.hiddenLayerCount);
    }

}

