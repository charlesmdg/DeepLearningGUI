package ihm.controls;

import common.Constants;
import common.Tools;
import ihm.areas.DatasetArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DeepNumericalTextField extends DeepTextField{
    public DeepNumericalTextField(boolean editable){
        super(editable);
        Tools.turnsTextFieldNumericalOnly(this);
        Tools.addTextLimiter(this, Constants.NUMERICAL_FIELD_MAX_LENGTH);
    }
}
