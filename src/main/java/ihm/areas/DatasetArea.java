package ihm.areas;

import common.Constants;
import common.Tools;
import data.CsvLoader;
import ihm.controls.*;
import javafx.scene.layout.Region;

public class DatasetArea extends DeepVInputArea {
    private final DeepTextField csvFileTextField = new DeepTextField(false);
    private final DeepTextField targetVariableNameTextField = new DeepTextField(false);
    private final DeepTextField trainingTextField = new DeepNumericalTextField(true);
    private final DeepComboBox pretreatmentComboBox = new DeepComboBox(Constants.PRETREATMENT_OPTIONS);
    private final DeepButton chooseButton;
    private CsvLoader csvLoader;

    public DatasetArea(TheScene scene) {
        super(Constants.PARAMETER_AREA_WIDTH, Constants.DATASET_AREA_HEIGHT, true);
        this.csvFileTextField.editableProperty().setValue(false);
        this.chooseButton = new DeepButton(Constants.CHOOSE_AND_DOTS, scene);

        this.add(new DeepTitleLabel(Constants.DATASET));

        DeepHBox hBox = new DeepHBox(false);
        Tools.setWidth(this.csvFileTextField, Constants.TEXT_TEXTFIELD_WIDTH);
        hBox.add(this.csvFileTextField);
        hBox.add(chooseButton);

        Tools.setWidth(this.trainingTextField, Constants.NUM_TEXTFIELD_WIDTH);

        String[] labelTexts = {Constants.CSV_FILE, Constants.TARGET_VARIABLE, Constants.TRAINING_RATE, Constants.PRETREATMENT};
        Region[] regions = {hBox, this.targetVariableNameTextField, this.trainingTextField, this.pretreatmentComboBox};
        this.fill(labelTexts, regions);
    }

    public boolean checkCsvFile(String filePath) {
        this.csvLoader = new CsvLoader(filePath);
        return this.csvLoader.check();
    }


    public void setChildrenDisabled(boolean disabled) {
        this.csvFileTextField.setDisable(disabled);
        this.chooseButton.setDisable(disabled);
        this.targetVariableNameTextField.setDisable(disabled);
        this.trainingTextField.setDisable(disabled);
        this.pretreatmentComboBox.setDisable(disabled);
    }

    public void setCsvFileName(String csvFile) {
        this.csvFileTextField.setText(csvFile);
        this.csvFileTextField.positionCaret(this.csvFileTextField.getLength());
    }

    public void setTargetVariableName(String targetVariable) {
        this.targetVariableNameTextField.setText(targetVariable);
    }

    public void setTrainingSplit(double training) {
        this.trainingTextField.setText(String.valueOf(training));
    }

    public void setPretreatment(String pretreatment) {
        this.pretreatmentComboBox.setValue(pretreatment);
    }

    public String getTargetVariableName() {
        return this.targetVariableNameTextField.getText();
    }

    public double getTrainingProportion(){
        return Double.parseDouble(this.trainingTextField.getText());
    }

    public String getPretreatment() {
        return this.pretreatmentComboBox.getSelectionModel().getSelectedItem();
    }

    public CsvLoader getCsvLoader() {
        return this.csvLoader;
    }

    public DeepTextField getTrainingTextField() {
        return this.trainingTextField;
    }

    public DeepTextField getCsvFileTextField() {
        return this.csvFileTextField;
    }
}
