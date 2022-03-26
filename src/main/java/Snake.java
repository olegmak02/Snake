import java.util.ArrayList;
import java.util.List;

class Snake {
    private ArrayList<Integer> xs = new ArrayList<>();
    private ArrayList<Integer> ys = new ArrayList<>();
    private int direction;                               // up - 0;  right - 1; down - 2; left - 3
    private int length;

    Snake() {
        this.length = 2;
        this.xs.add(5);
        this.xs.add(4);
        this.ys.add(6);
        this.ys.add(6);
        this.direction = 1;
    }

    public List<Integer> getXs() {
        return xs;
    }

    public List<Integer> getYs() {
        return ys;
    }

    public int getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public void increaseLength() {
        this.length++;
    }
}
