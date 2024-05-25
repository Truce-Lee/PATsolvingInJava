import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static class Record{
		String id;
		String psw;
		boolean flag = false;
		public Record(String id, String psw) {
			this.id=id;
			this.psw=psw;
		}
	}
	static int count = 0;
	static boolean flag = false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Record> list = new ArrayList<>();
		for(int i=0;i<n;i++) {
			String line[] = br.readLine().split(" ");
			String psw = line[1];
			flag = false;
			psw = checkPsw(psw);
			Record record = new Record(line[0],psw);
			if(flag) {
				count++;
				record.flag=true;
			}
			
			list.add(record);
		}
		if(count==0) {
			if(n==1) {
				System.out.println("There is "+n+" account and no account is modified");
			}else {
				System.out.println("There are "+n+" accounts and no account is modified");
			}
			
		}else {
			System.out.println(count);
			for(Record r:list) {
				if(r.flag) {
					System.out.printf("%s %s\n",r.id,r.psw);
				}
			}
		}

	}
	private static String checkPsw(String psw) {
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<psw.length();i++) {
			if(psw.charAt(i)=='1') {
				flag = true;
				sb.append('@');
			}else if(psw.charAt(i)=='0') {
				flag = true;
				sb.append('%');
			}else if(psw.charAt(i)=='l') {
				flag = true;
				sb.append('L');
			}else if(psw.charAt(i)=='O') {
				flag = true;
				sb.append('o');
			}else {
				
				sb.append(psw.charAt(i));
			}
		}
		return sb.toString();
	}

}
