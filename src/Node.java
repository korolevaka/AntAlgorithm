import java.awt.Point;

public class Node {
    public final int index;
    public final Point position;

    public Node(int index, int x, int y) {
        this.index = index;
        this.position = new Point(x, y);
    }
}
