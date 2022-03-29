package ihm;

import javafx.scene.layout.VBox;

public class MVBox extends VBox {
    public MVBox(boolean bordered){
        super();
        if(bordered){
            this.setStyle("-fx-border-color: green");
        }
    }
}
