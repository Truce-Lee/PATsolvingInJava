import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

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
			int numberMin = N;
			int j = 0;
			boolean flag = true;
			while(j<N) {
				 
				tk.nextToken();
				if(flag) {
					int numberNow = (int)tk.nval;
					if(j==0 || numberMin == 1) numberMin =numberNow;
					if(numberNow>M+j) {
						System.out.println("NO");
						flag = false;
					}
					if(numberNow<numberMin) {
						if(numberNow==numberMin-1) {
							numberMin = numberNow;
						}else {
							System.out.println("NO");
							flag = false;
							
						}
					}
				}
				j++;
			}
			if(flag) {
				System.out.println("YES");
			}
		}
	}

}
