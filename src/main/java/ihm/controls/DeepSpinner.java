package ihm.controls;

import common.Constants;
import common.Tools;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DeepSpinner extends Spinner<Integer> {
    private final int minValue;
    private final int maxValue;

    public DeepSpinner(int minValue, int maxValue) {
        super();

        this.minValue = minValue;
        this.maxValue = maxValue;

        Tools.setWidth(this, Constants.SPINNER_WIDTH);

        this.editorProperty().get().setAlignment(Pos.CENTER);
    }

    private void setValueFactory() {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(this.minValue, this.maxValue);
        this.setValueFactory(valueFactory);

    }

    public void setValue(int value) {
        if(this.getValueFactory()==null)
            this.setValueFactory();

        this.getValueFactory().setValue(value);
    }
}
