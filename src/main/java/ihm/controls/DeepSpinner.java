package ihm.controls;

import common.Constants;
import common.Tools;
import ihm.areas.TheScene;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class DeepSpinner extends Spinner<Integer> {
    private final int minValue;
    private final int maxValue;
    private final String text;

    public DeepSpinner(String text, int minValue, int maxValue, TheScene scene) {
        super();
        this.text = text;
        this.minValue = minValue;
        this.maxValue = maxValue;
        Tools.setWidth(this, Constants.SPINNER_WIDTH);

        this.editorProperty().get().setAlignment(Pos.CENTER);

        this.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if(scene != null)
                        scene.spinnerValueChanged(DeepSpinner.this.text, newValue);
                });
    }

    public int getValue_(){
        if(this.getValueFactory() == null)
            return 1;
        else
            return this.getValue();
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
