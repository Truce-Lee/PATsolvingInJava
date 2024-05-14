import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	static class Record{
		String id;
		int location;
		int mark;
		public Record(String id,int location,int mark) {
			this.id=id;
			this.location=location;
			this.mark=mark;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int locationNum = Integer.parseInt(br.readLine()); 
		List<Record> list = new ArrayList<>();
		for(int i =0;i<locationNum;i++) {
			int recordNum = Integer.parseInt(br.readLine());
			for(int j=0;j<recordNum;j++) {
				String line[] = br.readLine().split(" ");
				Record record = new Record(line[0],i,Integer.parseInt(line[1]));
				list.add(record);
			}
		}
		Collections.sort(list,new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				if(o1.mark>o2.mark || (o1.mark==o2.mark&&Long.parseLong(o1.id) <Long.parseLong(o2.id))) {
					return -1;
				}else if(o1.mark<o2.mark) {
					return 1;
				}
				return 0;
			}
		
		});
		
		System.out.println(list.size());
		int rank = 0;
		int localRank[] = new int[locationNum];
		int lastMark = 101;
		int lastLocalMark[] = new int[locationNum];
		int rankNow=0;
		int localrankNow[] = new int[locationNum];
		Arrays.fill(lastLocalMark, 101);
		for(int i =0;i<list.size();i++) {
			Record recordNow = list.get(i);

			if(recordNow.mark!=lastMark) {
				lastMark=recordNow.mark;
				rankNow = rank;
				
			}
			if(recordNow.mark!=lastLocalMark[recordNow.location]) {
				lastLocalMark[list.get(i).location]=recordNow.mark;
				localrankNow[recordNow.location]=localRank[recordNow.location];
			}
			System.out.println(recordNow.id+" "+(rankNow+1)+" "+(recordNow.location+1)+" "+(localrankNow[recordNow.location]+1));
			rank++;
			localRank[recordNow.location]++;
		}
	}

}
