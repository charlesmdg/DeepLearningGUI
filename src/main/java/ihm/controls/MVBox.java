package ihm.controls;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MVBox extends VBox {
    public MVBox(boolean bordered){
        super();
        if(bordered){
            this.setBorder(new Border(new BorderStroke(Color.BLACK,
                                                        BorderStrokeStyle.DASHED,
                                                        CornerRadii.EMPTY,
                                                        BorderWidths.DEFAULT)));
        }
    }
}
