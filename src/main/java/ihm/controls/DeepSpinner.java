package ihm.controls;

import common.Constants;
import common.Tools;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DeepSpinner extends Spinner<Integer>{
    public DeepSpinner(int min_value, int max_value){
        super();
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(min_value, max_value);
        this.setValueFactory(valueFactory);
        Tools.setWidth(this, Constants.SPINNER_WIDTH);
        this.editorProperty().get().setAlignment(Pos.CENTER);
    }

    public void setValue(int value){
        this.getValueFactory().setValue(value);
    }
}
