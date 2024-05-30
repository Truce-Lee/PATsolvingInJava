import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		List<String> list = new ArrayList<>();
		for(int i = 1;i<line.length;i++) {
			list.add(line[i]);
		}
		list.sort(new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				if (s1.contains(s2) || s2.contains(s1)) {
		            return (s1+s2).compareTo(s2+s1);
		        }
				return s1.compareTo(s2);
			}
		});
		StringBuilder sb = new StringBuilder();
		for(String s : list) {
			sb.append(s);
		}
		System.out.println(sb.toString().replaceFirst("^0+(?!$)", ""));
	}

}
