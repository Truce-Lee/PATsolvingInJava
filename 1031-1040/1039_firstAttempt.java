import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tk = new StreamTokenizer(br);
		tk.nextToken();
		int studentNum = (int)tk.nval;
		tk.nextToken();
		int courseNum = (int)tk.nval;
		Map<String, List<Integer>> map = new HashMap<>();
		for(int i =0;i<courseNum;i++) {
			tk.nextToken();
			int courseId = (int)tk.nval;
			tk.nextToken();
			int chosed = (int)tk.nval;
			for(int j =0;j<chosed;j++) {
				tk.nextToken();
				String name = tk.sval;
                // map.computeIfAbsent(name, k -> new ArrayList<>()).add(courseId);
				List<Integer> list = map.getOrDefault(name, new ArrayList<Integer>());
				list.add(courseId);
				map.put(name, list);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<studentNum;i++) {
			tk.nextToken();
			String name = tk.sval;
			List<Integer> list = map.get(name);
			if(list==null) {
				sb.append(name).append(" 0\n");
			}else{
                Collections.sort(list);
    			sb.append(name).append(" ").append(list.size());
    			for(Integer courseId : list) {
    				sb.append(" ").append(courseId);
    			}
    			sb.append("\n");
            }
		}
		System.out.print(sb.toString());
	}

}
