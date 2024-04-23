package aPulsBPoly;

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
		int [][] road = new int[roads][3];
		for(int i =0; i<roads;i++) {
			road[i][0]=s.nextInt();
			road[i][1]=s.nextInt();
			road[i][2]=s.nextInt();
		}
		s.close();
		
		int length = 0;
		int teamNum = rescueTeam[start];
		boolean[] visitedCity = new boolean[cities];
		findRoads(start,end,road,rescueTeam,length,teamNum,visitedCity);
		System.out.println(shortLength+" "+maxTeamNum);
		
	}

	private static void findRoads(int currentCity, int endCity,int[][] roads, int[] rescueTeam,int length,int teamNum,boolean[] visitedCity) {
		if (currentCity==endCity) {
			if (length < shortLength) {
                shortLength = length;
                maxTeamNum = teamNum;
            }
			return;
		}
		visitedCity[currentCity]=true;
		for(int[]road:roads) {
			if(road[0]==currentCity&& !visitedCity[road[1]]||road[1]==currentCity&& !visitedCity[road[0]]) {
				int nextCity = road[0]==currentCity?road[1]:road[0];
				length=length+road[2];
				teamNum = teamNum+rescueTeam[nextCity];
				findRoads(nextCity,endCity,roads,rescueTeam,length,teamNum,visitedCity);
			}
		}
		
		visitedCity[currentCity]=false;
		
	}

}
