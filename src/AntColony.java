
public class AntColony {
    private final int[][] graph;
    private final double[][] pheromones;
    private final int nodeCount;

    public AntColony(int[][] graph) {
        this.graph = graph;
        this.nodeCount = graph.length;
        this.pheromones = new double[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; i++)
            for (int j = 0; j < nodeCount; j++)
                pheromones[i][j] = 0.95;
    }

    public double[][] getPheromones() {
        return pheromones;
    }

    public void updatePheromones(int[] path) {
        for (int i = 0; i < path.length - 1; i++) {
            int from = path[i];
            int to = path[i + 1];
            pheromones[from][to] += 1.0;
            pheromones[to][from] += 1.0;
        }
    }

    public int chooseNextNode(Ant ant) {
        int current = ant.getCurrentNode();
        double[] probs = new double[nodeCount];
        double sum = 0;

        for (int i = 0; i < nodeCount; i++) {
            if (!ant.isVisited(i) && graph[current][i] > 0) {
                probs[i] = pheromones[current][i];
                sum += probs[i];
            }
        }

        if (sum == 0) return -1;

        double r = Math.random() * sum;
        double acc = 0;

        for (int i = 0; i < nodeCount; i++) {
            acc += probs[i];
            if (r <= acc) return i;
        }
        return -1;
    }
}
