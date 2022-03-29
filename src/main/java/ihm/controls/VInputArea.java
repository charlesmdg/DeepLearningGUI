package ihm.controls;

public class VInputArea extends InputArea {

    public VInputArea(double width, double height, boolean bordered) {
        super(new MVBox(bordered), width, height);
    }
}
