package aPulsBPoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Emergency {
	static int shortLength = Integer.MAX_VALUE;
	static int maxTeamNum = 0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int cities = s.nextInt();
		int roads = s.nextInt();
		int start = s.nextInt();
		int end = s.nextInt();
		int[] rescueTeam =new int[cities];
		for(int i=0;i<cities;i++) {
			rescueTeam[i]=s.nextInt();
		}
		List<List<Pair<Integer,Integer>>> graph = new ArrayList<>();
		
		
		for(int i =0; i<cities;i++) {
			graph.add(new ArrayList<>());
		}
		for(int i =0; i<roads;i++) {
			int u = s.nextInt();
			int t = s.nextInt();
			int len = s.nextInt();
			graph.get(u).add(new Pair<>(t,len));
			graph.get(t).add(new Pair<>(u,len));
		}
		s.close();
		
		int length = 0;
		int teamNum = rescueTeam[start];
		boolean[] visitedCity = new boolean[cities];
        if (start == end) {
            System.out.println(1 + " " + rescueTeam[start]); // There's exactly one shortest path, its length is 0, and the rescue teams are from the starting city.
            return; // End the program early.
        }
		findRoads(start,end,graph,length,teamNum,visitedCity,rescueTeam);
		
		System.out.println(shortLength+" "+maxTeamNum);
		
	}

	private static void findRoads(int currentCity, int endCity, List<List<Pair<Integer, Integer>>> graph,int currentLength,int currentTeamNum,boolean[] visitedCity,int[] rescueTeam) {
		if(currentCity==endCity){
			if (currentLength<shortLength) {
				shortLength = currentLength;
				maxTeamNum = currentTeamNum;
			}else if(currentLength==shortLength) {
				maxTeamNum=Math.max(currentTeamNum, maxTeamNum);
			}
			return;
		}
		visitedCity[currentCity] = true;
		for (Pair<Integer,Integer> pair :graph.get(currentCity)) {
			int nextCity = pair.first;
			if(!visitedCity[nextCity]) {
				int newLength = currentLength + pair.second;
		        int newTeamNum = currentTeamNum + rescueTeam[nextCity];
				findRoads(nextCity,endCity,graph,newLength,newTeamNum,visitedCity,rescueTeam);
			}
			
		}
		visitedCity[currentCity] = false;
	}
	static class Pair<T, U>{
		public T first;
		public U second;
		public Pair(T first,U second){
			this.first = first;
			this.second = second;
		}
	}
}	
