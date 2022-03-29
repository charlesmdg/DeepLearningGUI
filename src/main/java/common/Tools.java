package common;

import javafx.scene.layout.Region;

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

    public static void println(String text) {
        System.out.println(text);
    }

    public static void println(double x) {
        System.out.println(x);
    }

}
