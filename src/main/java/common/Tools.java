package common;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * C'es la boîte a outils de l'application
 */
public class Tools {
    /**
     * @param region le controle a retailler
     * @param width  la largeur finale
     * @param heigt  la hauteur finale
     */
    public static void setSize(Region region, double width, double heigt) {
        region.setMaxSize(width, heigt);
        region.setMinSize(width, heigt);
    }

    /**
     * @param region le controle a retailler
     * @param height la hauteur finale
     */
    public static void setHeight(Region region, double height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
    }

    /**
     * @param region le controle a retailler
     * @param width  la largeur finale
     */
    public static void setWidth(Region region, double width) {
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }

    /**
     * @param height la hauteur du spacer
     * @return un spacer vertical
     */
    public static Region createVerticalSpacer(double height) {
        Region region = new Region();
        Tools.setHeight(region, height);
        return region;
    }

    /**
     * @return un spacer vertical elastique
     */
    public static Region createVExpandableSpacer() {
        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);
        return region;
    }

    /**
     * @return un spacer horizontal elastique
     */
    public static Region createHExpandableSpacer() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        return region;
    }


    public static void addClearBorder(Region region) {
        region.setStyle("-fx-border-color: lightgray; -fx-background-color: white;");
    }

    /**
     * @param message le message d'erreur a afficher
     */
    public static void error(String message) {
        Tools.alert(Alert.AlertType.ERROR, message);
    }

    public static void error(String message, int number) {
        Tools.alert(Alert.AlertType.ERROR, message.replace(Constants.STRING_TO_REPLACE, String.valueOf(number)));
    }

    public static void inform(String message) {
        Tools.alert(Alert.AlertType.INFORMATION, message);
    }

    public static void inform(String message, String text) {
        Tools.alert(Alert.AlertType.INFORMATION, message.replace(Constants.STRING_TO_REPLACE, text));
    }

    public static Optional<ButtonType> confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Constants.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(Constants.DELETE, Constants.DO_NOT_DELETE);

        alert.setHeaderText(null);
        alert.setContentText(message);
        Tools.setStyle(alert.getDialogPane());
        Tools.setDefaultButton(alert, Constants.DO_NOT_DELETE);
        return alert.showAndWait();
    }

    private static void setDefaultButton(Alert alert, ButtonType defaultButtonType) {
        DialogPane pane = alert.getDialogPane();
        for (ButtonType buttonType : alert.getButtonTypes())
            ((Button) pane.lookupButton(buttonType)).setDefaultButton(buttonType == defaultButtonType);
    }

    /**
     * @param alertType le type de message
     * @param message   le message a afficher
     */
    private static void alert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);

        String title = "";
        if (alertType == Alert.AlertType.INFORMATION)
            title = Constants.INFORMATION;
        else if (alertType == Alert.AlertType.ERROR)
            title = Constants.ERROR;

        alert.setTitle(title);

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(message);

        Tools.setStyle(alert.getDialogPane());
        alert.showAndWait();
    }

    /**
     * @param strings la liste de chaine de caractere
     * @param string  :la chaine de caratere a chercher
     * @return l'indice de la chaine de caractere recherchee
     */
    public static int indexOf(String[] strings, String string) {
        if (strings == null)
            return Constants.IMPOSSIBLE_INDEX;

        for (int ii = 0; ii < strings.length; ii++) {
            if (strings[ii].equals(string))
                return ii;
        }

        return Constants.IMPOSSIBLE_INDEX;
    }

    /**
     * compte le nombre del lignes d'un fichier
     *
     * @param path le chemin absolu du fichier
     * @return le nombre de ligne du fichier
     */
    public static int filelineCount(String path) {
        int count = -1;
        try {
            // make a connection to the file
            Path file = Paths.get(path);

            // read all lines of the file
            count = (int) Files.lines(file).count();

        } catch (Exception e) {
            Tools.error(Message.CSV_READ_ERROR);
        }
        return count;
    }

    /**
     * modifie l'arriere plan de la scene en creant un degrade diagonal
     *
     * @param scene              la scene dont on veut modifier le fond
     * @param top_left_color     la couleur du coin haut gauche
     * @param bottom_right_color la couleur du coin bas droit
     */
    public static void setSceneBackground(Scene scene, Color top_left_color, Color bottom_right_color) {
        scene.setFill(new LinearGradient(
                0, 0, 1, 1, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, top_left_color),
                new Stop(1, bottom_right_color))
        );
    }

    public static String stringFormatRelativeIndicator(double indicator) {
        return String.format("%.2f", 100 * indicator) + Constants.PERCENT;
    }

    public static String stringFormatAbsoluteIndicator(double indicator) {
        return String.format("%.2f", indicator);
    }

    public static void turnsTextFieldNumericalOnly(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d.]*")) {
                textField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });
    }

    /**
     * impose une longueur limite a un textfield
     *
     * @param textField le textfield a limiter
     * @param maxLength la longueur maximale
     */
    public static void addTextLimiter(TextField textField, int maxLength) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (textField.getText().length() > maxLength) {
                String s = textField.getText().substring(0, maxLength);
                textField.setText(s);
            }
        });

    }

    public static boolean checkNumericalTextField(TextField textField, double minValue, double maxValue) {
        boolean answer = false;

        String text = textField.getText().trim();

        if (text.matches(Constants.DATA_REGEX)) {
            double value = Double.parseDouble(text);
            answer = (value > minValue) && (value < maxValue);
        }

        return answer;
    }

    public static File chooseFile(Stage stage, String title, String[][] extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        if (extensions != null) {
            for (String[] extension : extensions) {
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter(extension[0], extension[1]));
            }
        }

        return fileChooser.showOpenDialog(stage);
    }

    public static File saveFile(Stage stage, String title, String[][] extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        if (extensions != null) {
            for (String[] extension : extensions) {
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter(extension[0], extension[1]));
            }
        }

        return fileChooser.showSaveDialog(stage);
    }

    public static void serialize(Object object, String filePath) throws Exception {
        File fichier = new File(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fichier));
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();

    }

    public static Object deSerialize(String filePath) throws Exception {
        File fichier = new File(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fichier));
        return objectInputStream.readObject();
    }

    /**
     * @param region le controle a encader
     */
    public static void addBorder(Region region, boolean bordered) {
        if (bordered)
            region.setStyle("-fx-border-color: #021a57; -fx-border-width: 1px; -fx-background-color: transparent;");
        else
            region.setStyle("-fx-background-color: transparent;");
    }

    public static void setStyle(Control control) {
        control.setStyle("-fx-text-inner-color: #021a57; -fx-font: 14px Tahoma; -fx-text-alignment: center");
    }

    public static void setStyle(Pane pane) {
        pane.setStyle("-fx-text-inner-color: #021a57; -fx-font: 14px Tahoma");
    }
}