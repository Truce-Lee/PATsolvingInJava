import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class Main {
	static class Record{
		Time startTime;
		Time finishTime;
		String name;
		int duration;
		public Record(String name) {
			this.name =name;
//			this.startTime=startTime;
//			this.finishTime=finishTime;
		}
		public void setTime(Time time,String status) {
			if(status.equals("on-line")) {
				this.startTime=time;
			}else if(status.equals("off-line")) {
				this.finishTime=time;
			}
		}
		public int getDuration() {
			int day = finishTime.day-startTime.day;
			int hour = finishTime.hour-startTime.hour;
			int minute =finishTime.minute-startTime.minute;
			this.duration = day*24*60+hour*60+minute;
			return this.duration;
		}
	}
	static class Time{
		int month;
		int day;
		int hour;
		int minute;
		String status;
		public Time(String month,String day,String hour,String minute,String status) {
			this.month = Integer.parseInt(month);
			this.day = Integer.parseInt(day);
			this.hour = Integer.parseInt(hour);
			this.minute = Integer.parseInt(minute);
			this.status = status;
		}
		public int comparoTo(Time other) {
			if(this.month!=other.month) {
				return this.month-other.month;
			}else if(this.day!=other.day) {
				return this.day-other.day;
			}else if(this.hour!= other.hour) {
				return this.hour-other.hour;
			}else if(this.minute!= other.minute) {
				return this.minute-other.minute;
			}else {
				return 0;
			}
		}
	}
	static int chargeTable[] = new int[24];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		for(int i = 0;i<24;i++) {
			chargeTable[i] = Integer.parseInt(line[i]);
		}
		int num = Integer.parseInt(br.readLine());
		Map<String,List<Time>> recordMap = new HashMap<>();
		for(int i =0;i<num;i++) {
			String parts[] = br.readLine().split(" ");
			String name = parts[0];
			String timeString[] = parts[1].split(":");
			
			String status = parts[2];
			Time time = new Time(timeString[0],timeString[1],timeString[2],timeString[3],status);
			List<Time> list =  recordMap.getOrDefault(name, new ArrayList<>());    						
			list.add(time);
			recordMap.put(name, list);
		}
		for(List<Time> list : recordMap.values()) {
			Collections.sort(list,(a,b)->{
				return a.comparoTo(b);
			});
		}
		Map<String,List<Record>> result = new TreeMap<>((a,b)->{
			for(int i =0;i<Math.min(a.length(), b.length());i++) {
				if((int)a.charAt(i)>(int)b.charAt(i)) {
					return 1;
				}else if((int)a.charAt(i)<(int)b.charAt(i)) {
					return -1;
				}
			}
			return 0;
		});
		for(String name:recordMap.keySet()) {
			List<Time> list = recordMap.get(name);
			Time start = new Time("0", "0", "0", "0", null);
			boolean flag = true;
			List<Record> records = new ArrayList<>();
			
			for(Time time : list) {
				if(time.status.equals("on-line")) {
					start = time;
					flag = false;
				}else if(time.status.equals("off-line")) {
					Record record = new Record(name);
					flag = true;
					record.setTime(start, "on-line");
					record.setTime(time, "off-line");
					records.add(record);
				}
			}
			result.put(name, records);
		}
		for(String name : result.keySet()) {
			List<Record> list = result.get(name);
			
			int month = list.get(0).startTime.month;
			System.out.println(String.format("%s %02d", name,month));
			double total=0;
			for(Record record:list) {
				if(month!=record.startTime.month) {
					month=record.startTime.month;
					System.out.println(name+" "+month);
				}
				
				double cost = getCost(record);
				total = total+cost;
				System.out.println(String.format("%02d:%02d:%02d %02d:%02d:%02d %d $%.2f", record.startTime.day,
						record.startTime.hour,record.startTime.minute,record.finishTime.day,record.finishTime.hour,record.finishTime.minute,record.getDuration(),cost));
			}
			System.out.println(String.format("Total amount: $%.2f", total));
		}

	}
	private static double getCost(Record record) {
//		double duration = record.duration/60;
		int startDay = record.startTime.day;
		int endDay = record.finishTime.day;
		int startHour = record.startTime.hour;
		int endHour = record.finishTime.hour;
	
		double cost = 0;
		if(startDay==endDay) {
			if(startHour==endHour) {
				cost=record.getDuration()/60*chargeTable[startHour];
			}else {
				for(int i =startHour;i<endHour;i++) {
					if(i==startHour) {
						cost = cost + (double)(60-record.startTime.minute)*(double)chargeTable[startHour]/100;
					}else {
						cost = cost +(double)chargeTable[i]*60/100;
					}
				}
				cost=cost + (double)record.finishTime.minute*(double)chargeTable[endHour]/100;
			}
		}else {
			for(int i = startDay;i<endDay;i++) {
				if(i==startDay) {
					for(int j =startHour;j<24;j++) {
						if(j==startHour) {
							cost = cost + (double)(60-record.startTime.minute)*(double)chargeTable[startHour]/100;
						}else {
							cost = cost +(double)chargeTable[j]*60/100;
						}
					}
				}else {
					for(int j =0;j<24;j++) {
						cost = cost +(double)chargeTable[j]*60/100;
					}
				}
			}
			for(int i = 0;i<endHour;i++) {
				cost = cost +(double)chargeTable[i]*60/100;
			}
			cost=cost + (double)record.finishTime.minute*(double)chargeTable[endHour]/100;
		}
		
		return cost;
	}

}
