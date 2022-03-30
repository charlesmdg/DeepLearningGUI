package ihm.network;

import common.Constants;
import javafx.scene.shape.Rectangle;

public class NetworkBackgroung extends Rectangle {
    public NetworkBackgroung(double x, double y, double width, double height){
        super(x, y, width, height);
        this.setFill(Constants.NETWORK_BACKGROUND_COLOR);
    }
}
