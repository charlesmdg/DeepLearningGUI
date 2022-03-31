package common;

import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Tools {
    /**
     *
     * @param region : le controle a retailler
     * @param width : la largeur finale
     * @param heigt : la hauteur finale
     */
    public static void setSize(Region region, double width, double heigt) {
        region.setMaxSize(width, heigt);
        region.setMinSize(width, heigt);
    }

    /**
     *
     * @param region : le controle a retailler
     * @param height : la hauteur finale
     */
    public static void setHeight(Region region, double height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
    }

    /**
     *
     * @param region : le controle a retailler
     * @param width : la largeur finale
     */
    public static void setWidth(Region region, double width) {
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }

    /**
     *
     * @param height : la hauteur du spacer
     * @return : un spacer vertical
     */
    public static Region createVerticalSpacer(double height) {
        Region region = new Region();
        Tools.setHeight(region, height);
        return region;
    }

    /**
     *
     * @return : un spacer vertical elastique
     */
    public static Region createVExpandableSpacer() {
        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);
        return region;
    }

    /**
     *
     * @return : un spacer horizontal elastique
     */
    public static Region createHExpandableSpacer() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        return region;
    }

    /**
     *
     * @param region : le controle a encader
     */
    public static void addBorder(Region region) {
        region.setStyle("-fx-border-color: black");
    }

    /**
     *
     * @param message : le message d'erreur a afficher
     */
    public static void error(String message) {
        Tools.alert(Alert.AlertType.ERROR, message);
    }

    /**
     *
     * @param alertType : le type de message
     * @param message : le message a afficher
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
     *
     * @param strings : la liste de chaine de caractere
     * @param string :la chaine de caratere a chercher
     * @return : l'indice de la chaine de caractere recherchee
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
}
