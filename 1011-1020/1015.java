import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int flag = s.nextInt();
		while(flag>=0) {
			if(flag==0 ||flag==1) {
				s.nextInt();
				System.out.println("No");
                flag = s.nextInt();
                continue;
			}
			int prime = flag;
			int radix = s.nextInt();
			String numInRadix = getNumRadix(prime,radix);
			int inverse = getInverse(numInRadix,radix);
			boolean judge = judgePrime(inverse) &&judgePrime(prime) ;
	
			if(judge) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
			flag = s.nextInt();
		}
		s.close();
	}

	private static String getNumRadix(int prime, int radix) {
		String s = new String();
		while(prime>0) {
			int mod = prime % radix;
			s = mod+s;
			prime = prime / radix;
		}
		return s;
	}

	private static boolean judgePrime(int prime) {
		for(int i = 2;i<=prime/2;i++) {
			if(prime%i==0) return false;
		}
		return true;
	}

	private static int getInverse(String numInRadix,int radix) {
		String s = numInRadix;
		int result = 0;
		for(int i=s.length()-1;i>=0;i--) {
			int digit = Character.getNumericValue(s.charAt(i));
			result = result*radix + digit;
		}
		
		return result;
	}



}