package ihm.controls;

import common.Constants;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class MSpinner extends Spinner<Integer>{
    public MSpinner(int min_value, int max_value){
        super();
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(min_value, max_value);
        this.setValueFactory(valueFactory);
    }
}
