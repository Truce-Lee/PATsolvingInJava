import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	static class Record{
		String name;
		String id;
		String gender;
		int grade;
		public Record(String name,String id, String gender,int grade) {
			this.name = name;
			this.id = id;
			this.gender = gender;
			this.grade = grade;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Record> listM = new ArrayList<>();
		List<Record> listF = new ArrayList<>();
		for(int i =0;i<n;i++) {
			String line[] = br.readLine().split(" ");
			Record record = new Record(line[0],line[2],line[1],Integer.parseInt(line[3]));
			if(line[1].equals("M")) {
				listM.add(record);
			}else {
				listF.add(record);
			}
		}
		listM.sort(new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				return o2.grade-o1.grade;
			}
		});
		listF.sort(new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				return o1.grade-o2.grade;
			}
		});

		if(listF.isEmpty()) {
			System.out.println("Absent");
		}else {
			System.out.println(String.format("%s %s", listF.get(listF.size()-1).name,listF.get(listF.size()-1).id));
		}
		if(listM.isEmpty()) {
			System.out.println("Absent");
		}else {
			System.out.println(String.format("%s %s", listM.get(listM.size()-1).name,listM.get(listM.size()-1).id));
		}
		if(listM.isEmpty() || listF.isEmpty()) {
			System.out.println("NA");
		}else {
			System.out.println(listF.get(listF.size()-1).grade-listM.get(listM.size()-1).grade);
		}
	}

}



