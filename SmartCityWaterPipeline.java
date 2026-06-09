public class SmartCityWaterPipeline {

    static final int V = 5;

    int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    void printMST(int parent[], int graph[][]) {
        int totalCost = 0;

        System.out.println("Selected Pipelines:");
        System.out.println("Area1 - Area2   Cost");

        for (int i = 1; i < V; i++) {
            System.out.println(
                    "Area " + parent[i] + " - Area " + i + "      " + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }

        System.out.println("\nTotal Pipeline Construction Cost = " + totalCost);
    }

    void primMST(int graph[][]) {

        int parent[] = new int[V];
        int key[] = new int[V];
        boolean mstSet[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {

                if (graph[u][v] != 0 &&
                        !mstSet[v] &&
                        graph[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {

        SmartCityWaterPipeline obj = new SmartCityWaterPipeline();

        int graph[][] = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        System.out.println("Smart City Water Pipeline Optimization");
        System.out.println("--------------------------------------");

        obj.primMST(graph);
    }
}