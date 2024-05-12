import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();
		int b = s.nextInt();
		s.close();
		
		int sum =a+b;
		String formatsum=String.format("%,d", sum);
		
		System.out.print(formatsum);
	}

}
