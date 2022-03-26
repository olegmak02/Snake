import javax.swing.*;
import java.awt.*;

public class Food extends JComponent {
    private int x;
    private int y;

    public void setCoords(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
