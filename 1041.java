import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		tk.nextToken();
		int n = (int)tk.nval;
//		String line[] = br.readLine().split(" ");
//		int n = Integer.parseInt(line[0]);
		int number[] = new int[n];
		int check[] = new int[100000];
		for(int i =0;i<n;i++) {
			tk.nextToken();
			int index=(int)tk.nval;
//			int index = Integer.parseInt(line[i+1]);
			number[i] = index;
			check[index]++;
		}
		for(int i =0;i<n;i++) {
			if(check[number[i]]==1) {
				System.out.println(number[i]);
				System.exit(0);
			}
		}
		System.out.println("None");
	}

}
