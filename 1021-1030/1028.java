import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	static class Record{
		String id;
		String name;
		int grade;
		int idDecimal;
		public Record(String id,String name,int grade) {
			this.id=id;
			this.name=name;
			this.grade = grade;
			this.idDecimal=Integer.parseInt(id);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int flag = Integer.parseInt(line[1]);
		List<Record> list = new ArrayList<>();
		for(int i =0;i<n;i++) {
			String records[] = br.readLine().split(" ");
			Record record = new Record(records[0],records[1],Integer.parseInt(records[2]));
			list.add(record);
		}
		Collections.sort(list,new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				if(flag == 1) {
					return o1.idDecimal-o2.idDecimal;
				}
				else if (flag ==2) {
					if(o1.name.compareTo(o2.name)==0) {
						return o1.idDecimal-o2.idDecimal;
					}
					return o1.name.compareTo(o2.name);
				}
				else {
					if(o1.grade==o2.grade) {
						return o1.idDecimal-o2.idDecimal;
					}
					return o1.grade-o2.grade;
				}
			}});
//		System.out.println();
		for(Record record: list) {
			System.out.printf("%s %s %d\n",record.id,record.name,record.grade);
		}
		

	}

}
