import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		double [][] odds = new double[3][3];
		StringBuilder sb = new StringBuilder();
		double result=1;
		for(int i=0;i<3;i++) {
            double maxOdd = 0;
		    String record = new String();
			for(int j=0;j<3;j++) {
				odds[i][j]=s.nextDouble();
				if(odds[i][j]>maxOdd) {
					maxOdd=odds[i][j];
					if(j==0) {
						record = "W";
					}else if(j==1) {
						record = "T";
					}else {
						record = "L";
					}
				}
				
			}
			result=result*maxOdd;
			sb.append(record+" ");
		}
		s.close();
		
		result=(result*0.65-1)*2;
		sb.append(String.format("%,.2f", result));
		System.out.println(sb.toString());
		
	}

}
