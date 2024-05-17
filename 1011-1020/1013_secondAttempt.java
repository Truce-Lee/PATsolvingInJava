import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
    static boolean[] visited;
    static int[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        
        tokenizer.nextToken();
        int cityNum = (int) tokenizer.nval;
        tokenizer.nextToken();
        int highwayNum = (int) tokenizer.nval;
        tokenizer.nextToken();
        int testCases = (int) tokenizer.nval;

        // Initialize adjacency matrix
        adjMatrix = new int[cityNum][cityNum];
        visited = new boolean[cityNum];

        // Read highways
        for (int i = 0; i < highwayNum; i++) {
            tokenizer.nextToken();
            int a = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            int b = (int) tokenizer.nval - 1;
            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        StringBuilder result = new StringBuilder();

        // Read lost cities
        for (int i = 0; i < testCases; i++) {
            tokenizer.nextToken();
            int lostCity = (int) tokenizer.nval - 1;
            int componentCount = getComponentCount(lostCity, cityNum);
            result.append(componentCount - 1).append("\n");
        }

        System.out.print(result.toString());
    }

    // Calculate the number of components excluding the specified city
    private static int getComponentCount(int lostCity, int cityNum) {
        Arrays.fill(visited, false);
        visited[lostCity] = true; // Mark the lost city as visited
        int componentCount = 0;

        for (int i = 0; i < cityNum; i++) {
            if (!visited[i]) {
                dfs(i, cityNum);
                componentCount++;
            }
        }

        return componentCount;
    }

    // Depth First Search
    private static void dfs(int node, int cityNum) {
        visited[node] = true;
        for (int i = 0; i < cityNum; i++) {
            if (adjMatrix[node][i] == 1 && !visited[i]) {
                dfs(i, cityNum);
            }
        }
    }
}
