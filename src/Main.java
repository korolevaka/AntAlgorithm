import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Матрица смежности графа: расстояния между вершинами
        int[][] graph = {
                {0, 5, 0, 2, 0},
                {5, 0, 1, 3, 0},
                {0, 1, 0, 0, 4},
                {2, 3, 0, 0, 1},
                {0, 0, 4, 1, 0}
        };

        // Визуальные координаты вершин графа
        List<Node> nodes = List.of(
                new Node(0, 100, 100),
                new Node(1, 300, 100),
                new Node(2, 500, 100),
                new Node(3, 200, 300),
                new Node(4, 400, 300)
        );

        // Построение списка рёбер на основе матрицы смежности
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph.length; j++) {
                if (graph[i][j] > 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }

        // Панель отрисовки графа
        GraphPanel panel = new GraphPanel(nodes, edges);
        JFrame frame = new JFrame("Ant Colony Optimization - Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.add(panel);
        frame.setVisible(true);

        // Инициализация колонии
        AntColony colony = new AntColony(graph);
        Random random = new Random();
        final int[] iteration = {0};

        // Таймер запускает нового муравья каждую секунду
        new Timer(1000, e -> {
            int startNode = random.nextInt(graph.length);
            Ant ant = new Ant(graph.length, startNode);

            while (ant.getPathLength() < graph.length) {
                int next = colony.chooseNextNode(ant);
                if (next == -1) break;
                ant.visitNode(next);
            }

            colony.updatePheromones(ant.getPath());

            int[] path = Arrays.copyOf(ant.getPath(), ant.getPathLength());
            int length = 0;
            for (int i = 0; i < path.length - 1; i++) {
                length += graph[path[i]][path[i + 1]];
            }

            iteration[0]++;
            panel.setPath(path, length, iteration[0]);
        }).start();
    }
}
