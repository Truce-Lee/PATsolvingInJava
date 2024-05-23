import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

	public static void main(String[] args) throws IOException {
		StreamTokenizer tk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		List<Long> list=new ArrayList<>(); 
		tk.nextToken();
		int n =  (int)tk.nval;
		for(int i =0;i<n;i++) {
			tk.nextToken();
			list.add((long)tk.nval);
		}
		tk.nextToken();
		int m = (int)tk.nval;
		for(int i =0;i<m;i++) {
			tk.nextToken();
			list.add((long)tk.nval);
		}
		Collections.sort(list);
		System.out.println(list.get((list.size()+1)/2-1));
	}

}
