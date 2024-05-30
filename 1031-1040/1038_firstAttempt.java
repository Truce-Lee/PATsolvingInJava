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
		            String longer = s1.length() > s2.length() ? s1 : s2;
		            String shorter = s1.length() > s2.length() ? s2 : s1;

		            char lastChar = longer.charAt(shorter.length() );
		            char firstChar = longer.charAt(0);

		            // 如果较长字符串的最后一个字符在第一个字符之前，则较长字符串排在前面
		            if (lastChar < firstChar) {
		                return s1.length() > s2.length() ? -1 : 1;
		            }
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
