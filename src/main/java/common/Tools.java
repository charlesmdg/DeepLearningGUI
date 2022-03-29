package common;

import javafx.scene.layout.Region;

public class Tools {
    public static void setSize(Region region, double width, double heigt){
        region.setMaxSize(width, heigt);
        region.setMinSize(width, heigt);
    }

    public static void setHeight(Region region, double heigt){
        region.setMaxHeight(heigt);
        region.setMinHeight(heigt);
    }

    public static Region createVerticalSpacer(int heigt){
        Region region = new Region();
        Tools.setSize(region, 0, heigt);
        return  region;
    }

    public static Region createHorizontalSpacer(int width){
        Region region = new Region();
        Tools.setSize(region, width, 0);
        return  region;
    }

    public static void println(String text){
        System.out.println(text);
    }

    public static void println(double x){
        System.out.println(x);
    }

}
