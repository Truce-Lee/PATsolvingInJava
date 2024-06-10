package pat50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringSubtraction {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line1 = br.readLine();
		String line2[] = br.readLine().split();
		for(int i =0;iline2.length;i++) {
			line1 = line1.replaceAll(line2[i], );
		}
		System.out.println(line1);
	}

}
