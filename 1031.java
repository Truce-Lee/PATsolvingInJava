import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = line.length();
		int n2 = (N+2)/3;
		if(n2<3) n2 = 3;
        if(N+2-n2*3>0) n2++;
		if((N+2-n2)%2!=0) n2++;
		int n1=(N+2-n2)/2;
		char left[] = new char[n1];
		char right[] = new char[n1];
		char middle[] = new char[n2-2];
		for(int i = 0;i<n1;i++) {
			left[i] = line.charAt(i);
		}
		for(int i =n1;i<n1+n2-2;i++) {
			middle[i-n1] = line.charAt(i);
		}
		for(int i = n1+n2-2;i<N;i++) {
			right[i-n1-n2+2] = line.charAt(i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<n1;i++) {
			sb.append(left[i]);
			
			if(i==n1-1) {
				for(int j =0;j<n2-2;j++) {
					sb.append(middle[j]);
				}
			}else {
				for(int j =0;j<n2-2;j++) {
					sb.append(" ");
				}
			}
			sb.append(right[right.length-1-i]+"\n");
		}
		System.out.print(sb.toString());

	}

}
