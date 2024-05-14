import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		long num = Long.parseLong(line[0]);
		BigInteger bigNum = BigInteger.valueOf(num);
		int step = Integer.parseInt(line[1]);
		for(int i =0;i<step;i++) {
			if(judgePal(bigNum)) {
				System.out.println(bigNum);
				System.out.println(i);
				System.exit(0);
			}else {
				bigNum = bigNum.add(getInverse(bigNum));
			}
		}
		System.out.println(bigNum);
		System.out.println(step);
		

	}

	private static BigInteger getInverse(BigInteger bigNum) {
		BigInteger digit = BigInteger.valueOf(0);
		BigInteger  result = BigInteger.valueOf(0);
		BigInteger ten = BigInteger.valueOf(10);
		
		while(!bigNum.equals(BigInteger.ZERO)) {
			digit = bigNum.mod(ten);
			bigNum = bigNum.divide(ten);
			result = result.multiply(ten).add(digit);
		}
		return result;
	}

	private static boolean judgePal(BigInteger bigNum) {
		String s = bigNum.toString() ;
		for(int i = 0;i<s.length()/2;i++) {
			if(s.charAt(i)!=s.charAt(s.length()-i-1) ) {
				return false;
			}
		}
		return true;
	}

}
