import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String N1 = s.next();
		String N2 =s.next();
		int tag = s.nextInt();
		int radix =s.nextInt();
		s.close();
		long result=0;
		if(tag==1) {
			long N_decimal=calculateDecimal(N1,radix);
			result = findRadix(N_decimal,N2);
		}else {
			long N_decimal=calculateDecimal(N2,radix);
			result = findRadix(N_decimal,N1);
		}
		if(result==0) {
			System.out.println("Impossible");
            System.exit(0);
		}
		System.out.println(result);
	}

	

	private static long calculateDecimal(String n, long radix) {
		long result=0;
		int[] digits=getNum(n);
		for(int i=0;i<digits.length;i++) {
		 	result+=digits[digits.length-i-1]*(Math.pow(radix, i));
		}
		return result;
	}
	private static int[] getNum(String text) {
		int [] digits = new int[text.length()];
		for(int i=0;i<text.length();i++) {
			char character = text.charAt(i);
			if((int)character<=57 &&(int)character>=48) {
				digits[i] =(int)character-48; 
			}else {
				digits[i] =(int)character-97+10; 
			}
		}
		return digits;
	}
	private static long findRadix(long decimal, String n) {
		long minRadix = 2;
		int[] digits=getNum(n);
		for(int i=0;i<digits.length;i++) {
			minRadix=Math.max(minRadix, digits[i]+1);
		}
		long maxRadix = Math.max(decimal+1, minRadix) ;
		while(minRadix<=maxRadix) {
			long radix = minRadix +(maxRadix - minRadix) / 2;
			long cal = calculateDecimal(n,radix);
			if(cal==decimal) {
				return radix;
			}else if(cal>decimal && cal>0) {
				// maxRadix = (radix+maxRadix)/2;
                maxRadix=radix-1;
			}else {
				// minRadix = (radix+minRadix)/2;
                minRadix=radix+1;
			}
		}
		return 0;
	}
}
