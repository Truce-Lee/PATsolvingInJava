import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tk  =new StreamTokenizer(br);
		tk.nextToken();
		int M = (int)tk.nval;
		tk.nextToken();
		int N = (int)tk.nval;
		tk.nextToken();
		int K = (int)tk.nval;
		
		for(int i =0;i<K;i++) {
			boolean flag = true;
			int k = 1;
			Stack<Integer> stack = new Stack<>();
			for(int j =0;j<N;j++) {
				 
				tk.nextToken();
				if(flag) {
					int numberNow = (int)tk.nval;
					while(numberNow>=k) {
						stack.add(k);
						k++;
						if(stack.size()>M) {
							System.out.println("NO");
							flag = false;
							break;
						}
					}
					if(flag && (numberNow != stack.pop())) {
						System.out.println("NO");
						flag = false;
					}
			}
			if(flag) {
				System.out.println("YES");
			}
		}
	}
}
