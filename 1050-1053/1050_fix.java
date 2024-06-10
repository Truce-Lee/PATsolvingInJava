import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line1 = br.readLine();
		String line2 = br.readLine();
		boolean check[] = new boolean[256];
		Arrays.fill(check, true);
		for(int i =0;i<line2.length();i++) {
			check[(int)line2.charAt(i)]=false;
		}
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<line1.length();i++) {
			if(check[(int)line1.charAt(i)]) {
				sb.append(line1.charAt(i));
			}
		}
		System.out.println(sb.toString());
	}

}
