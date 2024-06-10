import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	static int[] weights;
	static List<List<Integer>> result;
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tk = new StreamTokenizer(br);
		tk.nextToken();
		int nodeNum = (int)tk.nval;
		tk.nextToken();
		int noleafNum = (int)tk.nval;
		tk.nextToken();
		int weightNum = (int)tk.nval;
		weights = new int[nodeNum];
		for(int i =0;i<nodeNum;i++) {
			tk.nextToken();
			weights[i] = (int)tk.nval;
		}
		List<List<Integer>> adjList = new ArrayList<List<Integer>>();
		for(int i =0;i<nodeNum;i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for(int i =0;i<noleafNum;i++) {
			tk.nextToken();
			int root = (int)tk.nval;
			tk.nextToken();
			int n = (int)tk.nval;
			List<Integer> list= adjList.get(root);
			
			for(int j = 0;j<n;j++) {
				tk.nextToken();
				int leaf = (int)tk.nval;	
				list.add(leaf);
			}
			adjList.set(root, list);
		}
		List<Integer> road = new ArrayList<>();
		result = new ArrayList<List<Integer>>();
		dfs(0,adjList,0,weightNum,road);
		result.sort(new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				int i =0;int j = 0;
				while (i<o1.size() && j<o2.size()) {
					if(weights[o1.get(i)]>weights[o2.get(j)]) return -1;
					else if (weights[o1.get(i)]<weights[o2.get(j)]) return 1;
					else {
						i++;
						j++;
					}
				}
				return 0;
			}
		});
		StringBuilder sb = new StringBuilder();
		for(List<Integer> list:result) {
			for(int i=0;i<list.size();i++) {
				sb.append(weights[list.get(i)]);
				if(i!=list.size()-1) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void dfs(int i, List<List<Integer>> adjList, int sum, int weightNum, List<Integer> road) {
		road.add(i);
		sum = sum + weights[i];
		if(adjList.get(i).isEmpty()) {
			if(sum == weightNum) {
				result.add(new ArrayList<>(road));
			}
			return;
		}
		List<Integer> list= adjList.get(i);
		for(Integer j: list) {
			dfs(j,adjList,sum,weightNum,road);
			road.remove(road.size()-1);
		}
		
	}

}
