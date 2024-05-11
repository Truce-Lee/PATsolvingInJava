import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	static private List<List<Integer>> adjList= new ArrayList<>(); 
	static private boolean visited[];
	static private int deep= 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine()); 
		visited =new boolean[num];
		for (int i =0;i<num;i++) {
			adjList.add(i, new ArrayList<Integer>());
		}
		for(int i = 0;i<num-1;i++) {
			String line[] = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			adjList.get(a-1).add(b-1);
			adjList.get(b-1).add(a-1);
		}
		int component = 0;
		Arrays.fill(visited, false);
		for(int i =0;i<num;i++) {
			if(visited[i]==false) {
				component++;
				bfs(i);
			}
		}
		if(component>1) {
			System.out.println("Error: "+component +" components");
			System.exit(0);
		}
		int maxDeep = 0;
		List<Integer> result = new ArrayList<>();
		for(int i =0;i<num;i++) {
			deep = 0;
			Arrays.fill(visited, false);
			dfs(i,0);
//			System.out.println("deep of "+(i+1)+" : "+deep);
			if(deep>maxDeep) {
				maxDeep = deep;
				result.removeAll(result);
				result.add(i+1);
			}else if(deep==maxDeep) {
				result.add(i+1);
			}
		}
		for(int i : result) {
			System.out.println(i);
		}

	}
	private static void dfs(int i,int currentDeep) {
		visited[i]=true;
		currentDeep++;
		deep = Math.max(deep, currentDeep);
		for(int j : adjList.get(i)) {
			if(visited[j]==false) {
//				deep++;
				dfs(j,currentDeep);
			}
		}
		
	}
	private static void bfs(int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for( int j: adjList.get(now)) {
				if(visited[j]==false) {
					visited[j]=true;
					queue.add(j);
				}
			}
		}
	}

}
