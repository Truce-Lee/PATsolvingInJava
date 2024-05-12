import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		BigInteger num = new BigInteger(s1);
		BigInteger doubleNum = num.add(num);
		String s2 = doubleNum.toString();
		boolean digits[] = new boolean[10];
		boolean digits2[] = new boolean[10];

		for(int i=0;i<s1.length();i++) {
			digits[Character.valueOf(s1.charAt(i))-48]=true;
			digits2[Character.valueOf(s1.charAt(i))-48]=true;
		}
		for(int i=0;i<s2.length();i++) {
			if(digits[Character.valueOf(s2.charAt(i))-48]) {
				digits2[Character.valueOf(s2.charAt(i))-48]=false;
			}else {
				System.out.println("No");
				System.out.println(doubleNum);
				System.exit(0);
			}
		}
		for(boolean flag:digits2) {
			if(flag) {
				System.out.println("No");
				System.out.println(doubleNum);
				System.exit(0);
			}
		}
		System.out.println("Yes");
		System.out.println(doubleNum);

		
	}

}
