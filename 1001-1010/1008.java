import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		int [] floors= new int[num];
		int time = 0;
		for (int i=0;i<num;i++) {
			floors[i]=s.nextInt();
			if(i==0) {
				time = time+5+floors[i]*6;
			}else {
				if(floors[i]>floors[i-1]) {
					time = time+5+(floors[i]-floors[i-1])*6;
				}else {
					time = time+5+(floors[i-1]-floors[i])*4;
				}
			}
		}
		s.close();
		System.out.println(time);
		

	}

}
