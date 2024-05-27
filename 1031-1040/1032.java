import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.HashSet;


public class Main {
	static HashSet<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tk = new StreamTokenizer(br);
//		tk.wordChars('0', '9');
		tk.nextToken();
		
//		String line[] = br.readLine().split(" ");
		int s1 = (int)tk.nval;
		tk.nextToken();
		int s2 = (int)tk.nval;
		tk.nextToken();
//		int n = Integer.parseInt(line[2]);
		int n = (int)tk.nval;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i =0;i<n;i++) {
//			String wordInfo[] = br.readLine().split(" ");
			tk.nextToken();
			int index = (int)tk.nval;
			tk.nextToken();
			tk.nextToken();
			int next = (int)tk.nval;
			map.put(index, next);
		}

		if(s1==s2) {
			System.out.printf("%05d\n",s1);
			System.exit(0);
		}
		getNext(map,s1);
		getNext(map,s2);
		System.out.println(-1);
	}

	private static void getNext(HashMap<Integer,Integer> map, int s1) {
		
		int s = map.get(s1)==null? -1:map.get(s1);
		if(s==-1) return;
		if(set.contains(s)) {
			System.out.printf("%05d\n",s);
			System.exit(0);
		}
		set.add(s);
		getNext(map, s);
		
	}

}
