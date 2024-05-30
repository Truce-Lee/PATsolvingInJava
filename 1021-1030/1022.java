import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<String, List<String>> titleMap = new HashMap<>();
		Map<String, List<String>> authorMap = new HashMap<>();
		Map<String, List<String>> keyMap = new HashMap<>();
		Map<String, List<String>> publisherMap = new HashMap<>();
		Map<Integer, List<String>> yearMap = new HashMap<>();
		for(int i = 0;i<n;i++) {
			String id = br.readLine();
			String title = br.readLine();
			String author = br.readLine();
			String info[] = br.readLine().split(" ");
			List<String> keywords= new ArrayList<>();
			for(int j = 0;j<info.length;j++) {
				keywords.add(info[j]);
				List<String> keylist = keyMap.getOrDefault(info[j], new ArrayList<>());
				keylist.add(id);
				keyMap.put(info[j], keylist);
			}
			String publisher = br.readLine();
			int year = Integer.parseInt(br.readLine());
			List<String> list = titleMap.getOrDefault(title, new ArrayList<>());
			list.add(id);
			titleMap.put(title, list);
			list = authorMap.getOrDefault(author, new ArrayList<>());
			list.add(id);
			authorMap.put(author, list);
			list = publisherMap.getOrDefault(publisher, new ArrayList<>());
			list.add(id);
			publisherMap.put(publisher, list);
			list = yearMap.getOrDefault(year, new ArrayList<>());
			list.add(id);
			yearMap.put(year, list);
			
		}
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for(int i =0;i<m;i++) {
			String checkInfo[] = br.readLine().split(": ");
			int checkFlag = Integer.parseInt(checkInfo[0]);
			String checkQ = checkInfo[1];
			sb.append(checkFlag+": "+checkQ+"\n");
			if(checkFlag == 1) {
				if(titleMap.get(checkQ)==null) {
					sb.append("Not Found\n");
				}else {
					Collections.sort(titleMap.get(checkQ));
					for(String id : titleMap.get(checkQ)) {
						sb.append(id+"\n");
					}
				}
			}
			if(checkFlag == 2) {
				if(authorMap.get(checkQ)==null) {
					sb.append("Not Found\n");
				}else {
					Collections.sort(authorMap.get(checkQ));
					for(String id : authorMap.get(checkQ)) {
						sb.append(id+"\n");
					}
				}
			}
			if(checkFlag == 3) {
				if(keyMap.get(checkQ)==null) {
					sb.append("Not Found\n");
				}else {
					Collections.sort(keyMap.get(checkQ));
					for(String id : keyMap.get(checkQ)) {
						sb.append(id+"\n");
					}
				}
			}	
			if(checkFlag == 4) {
				if(publisherMap.get(checkQ)==null) {
					sb.append("Not Found\n");
				}else {
					Collections.sort(publisherMap.get(checkQ));
					for(String id : publisherMap.get(checkQ)) {
						sb.append(id+"\n");
					}
				}
			}
			if(checkFlag == 5) {
				if(yearMap.get(Integer.parseInt(checkQ))==null) {
					sb.append("Not Found\n");
				}else {
					Collections.sort(yearMap.get(Integer.parseInt(checkQ)));
					for(String id : yearMap.get(Integer.parseInt(checkQ))) {
						sb.append(id+"\n");
					}
				}
			}
		}
		System.out.print(sb.toString());

	}

}
