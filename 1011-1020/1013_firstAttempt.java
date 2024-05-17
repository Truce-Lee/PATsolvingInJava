import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static List<List<Integer>> adjList = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int cityNum = Integer.parseInt(line[0]);
        int highwayNum = Integer.parseInt(line[1]);
        int cityCheck = Integer.parseInt(line[2]);

        // Initialize adjacency list
        for (int i = 0; i < cityNum; i++) {
            adjList.add(new ArrayList<>());
        }
        
        // Initialize visited array
        visited = new boolean[cityNum];
        
        // Read highways
        for (int i = 0; i < highwayNum; i++) {
            String[] highway = br.readLine().split(" ");
            int a = Integer.parseInt(highway[0]) - 1;
            int b = Integer.parseInt(highway[1]) - 1;
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        // Read lost cities
        String[] cityLost = br.readLine().split(" ");
        for (String s : cityLost) {
            int city = Integer.parseInt(s) - 1;
            List<List<Integer>> newList = getNewList(city);
            int component = 0;
            Arrays.fill(visited, false);
            for (int j = 0; j < cityNum; j++) {
                if (j != city && !visited[j]) {
                    dfs(j, newList);
                    component++;
                }
            }
            System.out.println(component - 1);
        }
    }

    // Create a new adjacency list excluding the specified city
    private static List<List<Integer>> getNewList(int city) {
        List<List<Integer>> newList = new ArrayList<>();
        for (int i = 0; i < adjList.size(); i++) {
            newList.add(new ArrayList<>());
            if (i != city) {
                for (int neighbor : adjList.get(i)) {
                    if (neighbor != city) {
                        newList.get(i).add(neighbor);
                    }
                }
            }
        }
        return newList;
    }

    // Depth First Search
    private static void dfs(int node, List<List<Integer>> list) {
        visited[node] = true;
        for (int neighbor : list.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, list);
            }
        }
    }
}
