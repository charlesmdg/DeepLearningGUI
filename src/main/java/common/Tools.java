package common;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.bytedeco.opencv.presets.opencv_core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    /**
     * @param region le controle a encader
     */
    public static void addBorder(Region region) {
        region.setStyle("-fx-border-color: black");
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

    public static void inform(String message, String text) {
        Tools.alert(Alert.AlertType.INFORMATION, message.replace(Constants.STRING_TO_REPLACE, text));
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
     * @param nodes    les nodes a activer/desactiver
     * @param disabled le statut d'activation final
     */
    public static void setDisableNodes(Node[] nodes, boolean disabled) {
        for (Node node : nodes) {
            node.setDisable(disabled);
        }
    }

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
}
