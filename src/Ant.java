
public class Ant {
    private int currentNode;
    private boolean[] visited;
    private int[] path;
    private int pathIndex;

    public Ant(int nodeCount, int startNode) {
        this.currentNode = startNode;
        this.visited = new boolean[nodeCount];
        this.path = new int[nodeCount + 1];
        this.pathIndex = 0;
        visitNode(startNode);
    }

    public void visitNode(int node) {
        visited[node] = true;
        path[pathIndex++] = node;
        currentNode = node;
    }

    public boolean isVisited(int node) {
        return visited[node];
    }

    public int getCurrentNode() {
        return currentNode;
    }

    public int[] getPath() {
        return path;
    }

    public int getPathLength() {
        return pathIndex;
    }
}
