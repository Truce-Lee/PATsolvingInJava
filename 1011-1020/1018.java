import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int[][] adjTable;
	static boolean[]check;
	static int[] U;
	static boolean[] find;
	static List<List<Integer>> prev= new ArrayList<>();
	static int[] stations;
	static int minTakeTo  =Integer.MAX_VALUE;
	static int minTakeback  =Integer.MAX_VALUE;
	static List<Integer> result = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		int capacity = Integer.parseInt(line[0]);
		int stationNum = Integer.parseInt(line[1]);
		int problemStation = Integer.parseInt(line[2]);
		int roadNum = Integer.parseInt(line[3]);
		adjTable = new int[stationNum+1][stationNum+1];
		check = new boolean[stationNum+1];
		U = new int[stationNum+1];
		find = new boolean[stationNum+1];
		String line2[] = br.readLine().split(" ");
		stations = new int[stationNum];
		for(int i =0;i< stationNum;i++) {
			stations[i]= Integer.parseInt(line2[i]);
		}
		for(int i = 0;i<roadNum;i++) {
			String line3[] = br.readLine().split(" ");
			adjTable[Integer.parseInt(line3[0])][Integer.parseInt(line3[1])]=Integer.parseInt(line3[2]);
			adjTable[Integer.parseInt(line3[1])][Integer.parseInt(line3[0])]=Integer.parseInt(line3[2]);
		}
		for(int i =0;i<stationNum+1;i++) {
			for(int j =0;j<stationNum+1;j++) {
				if(adjTable[i][j]==0) {
					adjTable[i][j]=1000000;
				}
			}
		}
		dijkstra(0);
		
		int takeBack = 0;
		int takeTo = 0;
		int perfect = capacity/2;
		List<Integer> list = new ArrayList<>();
		dfs(problemStation,takeBack,takeTo,perfect,list);
		
		StringBuilder sb = new StringBuilder();
		sb.append(minTakeTo+" ");
		for(int i= result.size()-1;i>=0;i--) {
			sb.append(result.get(i));
			if(i!=0) sb.append("->");
		}
		sb.append(" "+minTakeback);
		System.out.println(sb.toString());
	}
	private static void dijkstra(int start) {
		int U[] = new int[adjTable.length];

		boolean check[] = new boolean[adjTable.length];
		
		for(int i =0;i<adjTable.length;i++) {
			prev.add(new ArrayList<>());
		}
		for(int i =0;i<adjTable.length;i++) {
			U[i] = adjTable[start][i];
			if(U[i]!=1000000) {
				prev.get(i).add(start);
			}
		}
		U[start] = 0;

		check[0] = true;
		for(int i =1;i<adjTable.length;i++) {
			int min = 1000000;
			int index = 0;
			for(int j=0;j<U.length;j++) {
				if(!check[j] && U[j]<min) {
					min = U[j];
					index = j;
				}
			}
			check[index] = true;
			for(int j =0;j<adjTable.length;j++) {
				if(!check[j] && adjTable[index][j]+min<U[j]) {
					U[j] = adjTable[index][j]+min;
					prev.get(j).removeAll(prev.get(j));
					prev.get(j).add(index);
					
				}else if(adjTable[index][j]+min==U[j]) {
					prev.get(j).add(index);
				}
			}	
		}
	}
	private static void dfs(int current, int takeBack, int takeTo, int perfect, List<Integer> list) {
		list.add(current);
		check[current] = true;
		takeBack = takeTo<0? takeBack-takeTo:takeBack;
		takeTo = takeTo<0? 0:takeTo;
		if(prev.get(current).isEmpty()) {
			if((takeTo<minTakeTo) || (takeTo == minTakeTo && takeBack<minTakeback)) {
				minTakeTo=takeTo;
				minTakeback = takeBack;
				result.removeAll(result);
				result.addAll(list);
			}
			return;
		}
		takeTo = takeTo + perfect-stations[current-1];
		for(int j : prev.get(current)) {
			if(!check[j]) {
				dfs(j,takeBack,takeTo,perfect,list);
				check[j] = false;
				list.remove(list.size()-1);
			}
		}
	}

}
