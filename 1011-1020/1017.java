import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static class Time{
		int hour;
		int minute;
		int second;
		public Time(int h,int m,int s) {
			this.hour=h;
			this.minute=m;
			this.second=s;
		}
		public int getInt() {
			int second = this.hour*3600+this.minute*60+this.second;
			return second;
		}
		public void addTime(Time time) {
			int plus = (this.second+time.second)/60;
			this.second = (this.second+time.second)%60;
			this.minute = this.minute+time.minute+plus;
			plus = this.minute/60;
			this.minute = this.minute%60;
			this.hour=this.hour+time.hour+plus;
		}
	}
	public static class Record{
		Time arriveTime;
		int processTime;
		Time finishTime = new Time(0,0,0);
		public Record(Time arriveTime,int processTime) {
			
			this.arriveTime=arriveTime;
			
			this.processTime=processTime;
		}
		
	}
	public static class Window{
//		boolean occupy;
		Time freeTime = new Time(0,0,0);
		public Window() {
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[] = br.readLine().split(" ");
		int customerNum = Integer.parseInt(line[0]);
		int windowNum = Integer.parseInt(line[1]);
//		boolean occupied[] = new boolean[windowNum];
		List<Record> list = new ArrayList<>();
		for(int i =0;i<customerNum;i++) {
			String string[] = br.readLine().split(" ");
			String timeString[] = string[0].split(":");
			Time arriveTime = new Time(Integer.parseInt(timeString[0]),Integer.parseInt(timeString[1]),Integer.parseInt(timeString[2]));
			Record record = new Record(arriveTime,Integer.parseInt(string[1]));
			if(arriveTime.hour<17) list.add(record);
		}
		list.sort(new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				return o1.arriveTime.getInt()-o2.arriveTime.getInt();
			}
		});
		Window windows[]= new Window[windowNum];
		for(int i =0;i<windowNum;i++) {
			windows[i] = new Window(); 
		}
		double sum = 0;
		for(Record record:list) {
			int early = Integer.MAX_VALUE;
			int index=0;
			for(int i = 0;i<windowNum;i++) {
				if(windows[i].freeTime.getInt()<early) {
					early =windows[i].freeTime.getInt();
					index = i;
				}
			}
			if(record.arriveTime.hour<8) {
				sum = sum + 8*3600-record.arriveTime.getInt();
				record.arriveTime = new Time(8,0,0);
			}
			if(record.arriveTime.getInt()>early) {
				record.finishTime.addTime(new Time(0,record.processTime,0));
				record.finishTime.addTime(record.arriveTime);
				windows[index].freeTime=record.finishTime;
			}else {
				sum = sum + early-record.arriveTime.getInt();
				record.finishTime.addTime(new Time(0,record.processTime,0));
				record.finishTime.addTime(windows[index].freeTime);
				windows[index].freeTime=record.finishTime;
				
			}
//			System.out.printf("%.1f\n",sum);
			
		}
		sum = (sum/60)/list.size();
		System.out.printf("%.1f\n",sum);
	}

}
