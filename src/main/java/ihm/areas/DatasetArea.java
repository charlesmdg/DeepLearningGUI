package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.DeepComboBox;
import ihm.controls.DeepTextField;
import ihm.controls.DeepVInputArea;
import javafx.scene.layout.Region;

public class DatasetArea extends DeepVInputArea {

    private final DeepTextField csvFileTextField = new DeepTextField();
    private final DeepComboBox targetVariableComboBox  = new DeepComboBox(Constants.EMPTY_STRINGS);
    private final DeepTextField trainingTextField = new DeepTextField();
    private final DeepComboBox pretreatmentComboBox = new DeepComboBox(Constants.PRETREATMENT_OPTIONS);;

    public DatasetArea() {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.DATASET_AREA_HEIGHT, true);

        Tools.setWidth(this.csvFileTextField, Constants.TEXT_TEXTFIELD_WIDTH);
        Tools.setWidth(this.trainingTextField, Constants.NUM_TEXTFIELD_WIDTH);

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
