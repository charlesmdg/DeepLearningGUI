package ihm;

import javafx.scene.layout.HBox;

public class MHBox extends HBox {
    public MHBox(boolean bordered){
        super();
        if(bordered){
            this.setStyle("-fx-border-color: red");
        }
    }
}
