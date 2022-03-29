package ihm.app;

import common.Constants;
import ihm.controls.MComboBox;
import ihm.controls.MTextField;
import ihm.controls.VInputArea;
import javafx.scene.layout.Region;

public class DatasetArea extends VInputArea {

    private final MTextField csvFileTextField = new MTextField();
    private final MComboBox targetVariableComboBox  = new MComboBox();
    private final MTextField trainingTextField = new MTextField();
    private final MComboBox pretreatmentComboBox = new MComboBox(Constants.PRETREATMENT_OPTIONS);;

    public DatasetArea() {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.DATASET_AREA_HEIGHT, true);
        String[] labelTexts = {Constants.CSV_FILE, Constants.TARGET_VARIABLE, Constants.TRAINING, Constants.PRETREATMENT};
        Region[] regions = {this.csvFileTextField, this.targetVariableComboBox, this.trainingTextField, this.pretreatmentComboBox};
        this.fill(labelTexts, regions);
    }

    public String getCSVFilePath(){
        //Todo
        return this.csvFileTextField.getText();
    }

    public String getTargetVariable(){
        //Todo
        return this.targetVariableComboBox.getSelectionModel().getSelectedItem();
    }

    public double getTrainingRate(){
        //Todo
        return Double.parseDouble(this.trainingTextField.getText());
    }

    public String getPretreatment(){
        //Todo
        return this.pretreatmentComboBox.getSelectionModel().getSelectedItem();
    }

}
