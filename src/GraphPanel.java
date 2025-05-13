import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphPanel extends JPanel {
    private final List<Node> nodes;
    private final List<Edge> edges;

    private int[] path;
    private int iteration = 0;
    private int currentPathLength = 0;
    private int bestPathLength = Integer.MAX_VALUE;

    public GraphPanel(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void setPath(int[] path, int currentLength, int iteration) {
        this.path = path;
        this.currentPathLength = currentLength;
        this.iteration = iteration;

        if (currentLength < bestPathLength) {
            bestPathLength = currentLength;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw edges
        for (Edge edge : edges) {
            Node from = nodes.get(edge.from);
            Node to = nodes.get(edge.to);
            g.setColor(Color.LIGHT_GRAY);
            g.drawLine(from.position.x, from.position.y, to.position.x, to.position.y);
        }

        // Draw ant path
        if (path != null) {
            g.setColor(Color.RED);
            for (int i = 0; i < path.length - 1; i++) {
                Node from = nodes.get(path[i]);
                Node to = nodes.get(path[i + 1]);
                g.drawLine(from.position.x, from.position.y, to.position.x, to.position.y);
            }
        }

        // Draw nodes
        for (Node node : nodes) {
            g.setColor(Color.BLUE);
            g.fillOval(node.position.x - 10, node.position.y - 10, 20, 20);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(node.index), node.position.x - 5, node.position.y + 5);
        }

        // Draw stats
        g.setColor(Color.BLACK);
        g.drawString("Iteration: " + iteration, 20, 20);
        g.drawString("Current Path Length: " + currentPathLength, 20, 40);
        g.drawString("Best Path Length: " + bestPathLength, 20, 60);
    }
}
