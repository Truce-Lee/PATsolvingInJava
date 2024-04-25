import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		String[] numInEn = new String[10];
		numInEn[1] = "one";
		numInEn[2] = "two";
		numInEn[3] = "three";
		numInEn[4] = "four";
		numInEn[5] = "five";
		numInEn[6] = "six";
		numInEn[7] = "seven";
		numInEn[8] = "eight";
		numInEn[9] = "nine";
		numInEn[0] = "zero";
		int sum = 0;
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		int[] num = new int[line.length()];
		String part[]=line.split("");
		for (int i =0;i<line.length();i++) {
			num[i] = Integer.parseInt(part[i]);
			sum = sum +num[i];
		}
		s.close();
		StringBuilder sb = new StringBuilder();
		sb.append(sum);
		String string = sb.toString();
		StringBuilder result = new StringBuilder();
		for(int i=0;i<string.length();i++) {
			int index =Character.getNumericValue(string.charAt(i));
			if(i!=string.length()-1) {
				result.append(numInEn[index]+" ");
			}else {
				result.append(numInEn[index]);
			}
		}
		System.out.println(result.toString());
	}
	
}
