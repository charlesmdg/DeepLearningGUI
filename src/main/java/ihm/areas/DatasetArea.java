package ihm.areas;

import common.Constants;
import common.Tools;
import ihm.controls.*;
import javafx.scene.layout.Region;

import static common.Constants.CHOOSE_AND_DOTS;

public class DatasetArea extends DeepVInputArea {

    private final DeepTextField csvFileTextField = new DeepTextField(false);
    private final DeepComboBox targetVariableComboBox  = new DeepComboBox(Constants.EMPTY_STRINGS);
    private final DeepTextField trainingTextField = new DeepTextField(true);
    private final DeepComboBox pretreatmentComboBox = new DeepComboBox(Constants.PRETREATMENT_OPTIONS);;

    public DatasetArea(TheScene scene) {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.DATASET_AREA_HEIGHT, true);

        DeepHBox hBox = new DeepHBox(false);
        Tools.setWidth(this.csvFileTextField, Constants.TEXT_TEXTFIELD_WIDTH);
        hBox.getChildren().add(this.csvFileTextField);
        DeepButton chooseButton = new DeepButton(Constants.CHOOSE_AND_DOTS, scene);
        hBox.getChildren().add(chooseButton);
        Tools.setWidth(this.csvFileTextField, Constants.NUM_TEXTFIELD_WIDTH);

        String[] labelTexts = {Constants.CSV_FILE, Constants.TARGET_VARIABLE, Constants.TRAINING, Constants.PRETREATMENT};
        Region[] regions = {hBox, this.targetVariableComboBox, this.trainingTextField, this.pretreatmentComboBox};
        this.fill(labelTexts, regions);
    }

    public String getCSVFile(){
        return this.csvFileTextField.getText();
    }

    public String getTargetVariable(){
        return this.targetVariableComboBox.getSelectionModel().getSelectedItem();
    }

    public double getTrainingRate(){
        return Double.parseDouble(this.trainingTextField.getText());
    }

    public String getPretreatment(){
        return this.pretreatmentComboBox.getSelectionModel().getSelectedItem();
    }

    public void setCsvFile(String csvFile){
        this.csvFileTextField.setText(csvFile);
    }

    public void setTargetVariable(String targetVariable){
        this.targetVariableComboBox.setValue(targetVariable);
    }

    public void setTraining(double training){
        this.trainingTextField.setText(String.valueOf(training));
    }
    public void setPretreatment(String pretreatment){
        this.pretreatmentComboBox.setValue(pretreatment);
    }
}
