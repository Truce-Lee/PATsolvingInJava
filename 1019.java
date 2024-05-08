import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		int num = Integer.parseInt(line[0]);
		int base = Integer.parseInt(line[1]);
		if(num==0) {
			System.out.println("Yes");
			System.out.println("0");
			System.exit(0);
		}
		int digit = 0;
		List<Integer> list= new ArrayList<>();
		while(num!=0) {
			digit = num%base;
			num=num/base;
			list.add(digit);
		}

		
		for(int i =0;i<list.size()/2+1;i++) {
			int a = list.get(i);
			int b = list.get(list.size()-i-1);
			if (!(a==b)) {
				System.out.println("No");
				pringList(list);
				System.exit(0);
			}
		}
		System.out.println("Yes");
		pringList(list);
	}

	private static void pringList(List<Integer> list) {
		for(int i =list.size()-1;i>=0;i--) {
			System.out.printf("%d%s",list.get(i), i==0? "\n" :" ");
		}
		
	}



}
