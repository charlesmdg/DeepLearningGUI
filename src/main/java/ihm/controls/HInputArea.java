package ihm.controls;

public class HInputArea extends InputArea {

    public HInputArea(double width, double height, boolean bordered) {
        super(new MHBox(bordered), width, height);
        this.box = new MHBox(bordered);
    }
}
