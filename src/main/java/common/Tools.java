package common;

import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Tools {
    public static void setSize(Region region, double width, double heigt) {
        region.setMaxSize(width, heigt);
        region.setMinSize(width, heigt);
    }

    public static void setHeight(Region region, double height) {
        region.setMaxHeight(height);
        region.setMinHeight(height);
    }

    public static void setWidth(Region region, double width) {
        region.setMaxWidth(width);
        region.setMinWidth(width);
    }

    public static Region createVerticalSpacer(double height) {
        Region region = new Region();
        Tools.setHeight(region, height);
        return region;
    }

    public static Region createHorizontalSpacer(double width) {
        Region region = new Region();
        Tools.setWidth(region, width);
        return region;
    }

    public static Region createVExpandableSpacer(){
        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);
        return region;
    }

    public static Region createHExpandableSpacer(){
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        return region;
    }

    public static void addBorder(Region region){
        region.setStyle("-fx-border-color: black");
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static void println(double x) {
        System.out.println(x);
    }

    public static void inform(String message){
        Tools.alert(Alert.AlertType.INFORMATION, message);
    }

    public static void error(String message){
        Tools.alert(Alert.AlertType.ERROR, message);
    }

    private static void alert(Alert.AlertType alertType, String message){
        Alert alert = new Alert(alertType);

        String title = "";
        if(alertType == Alert.AlertType.INFORMATION)
            title = Constants.INFORMATION;
        else if(alertType == Alert.AlertType.ERROR)
            title = Constants.ERROR;

        alert.setTitle(title);

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


}
