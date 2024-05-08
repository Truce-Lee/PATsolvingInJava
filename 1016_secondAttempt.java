import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static class Record {
        Time startTime;
        Time finishTime;
        String name;

        public Record(String name, Time startTime, Time finishTime) {
            this.name = name;
            this.startTime = startTime;
            this.finishTime = finishTime;
        }

        public int getDuration() {
            int day = finishTime.day - startTime.day;
            int hour = finishTime.hour - startTime.hour;
            int minute = finishTime.minute - startTime.minute;
            return day * 24 * 60 + hour * 60 + minute;
        }
    }

    static class Time implements Comparable<Time> {
        int month, day, hour, minute;
        String status;

        public Time(String month, String day, String hour, String minute, String status) {
            this.month = Integer.parseInt(month);
            this.day = Integer.parseInt(day);
            this.hour = Integer.parseInt(hour);
            this.minute = Integer.parseInt(minute);
            this.status = status;
        }

        @Override
        public int compareTo(Time other) {
            if (this.month != other.month) return this.month - other.month;
            if (this.day != other.day) return this.day - other.day;
            if (this.hour != other.hour) return this.hour - other.hour;
            return this.minute - other.minute;
        }
    }

    static int[] chargeTable = new int[24];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < 24; i++) chargeTable[i] = Integer.parseInt(line[i]);

        int num = Integer.parseInt(br.readLine());
        Map<String, List<Time>> recordMap = new TreeMap<>();
        for (int i = 0; i < num; i++) {
            String[] parts = br.readLine().split(" ");
            String name = parts[0];
            String[] timeString = parts[1].split(":");
            String status = parts[2];

            Time time = new Time(timeString[0], timeString[1], timeString[2], timeString[3], status);
            recordMap.computeIfAbsent(name, k -> new ArrayList<>()).add(time);
        }

        Map<String, List<Record>> result = new TreeMap<>();
        for (String name : recordMap.keySet()) {
            List<Time> list = recordMap.get(name);
            Collections.sort(list);

            List<Record> records = new ArrayList<>();
            for (int i = 0; i < list.size() - 1; i++) {
                Time start = list.get(i);
                Time end = list.get(i + 1);

                if ("on-line".equals(start.status) && "off-line".equals(end.status)) {
                    records.add(new Record(name, start, end));
                    i++; // skip the next record as it is already paired
                }
            }

            if (!records.isEmpty()) result.put(name, records);
        }

        for (String name : result.keySet()) {
            List<Record> records = result.get(name);
            int month = records.get(0).startTime.month;
            System.out.println(String.format("%s %02d", name, month));
            double total = 0;

            for (Record record : records) {
                double cost = calculateCost(record);
                total += cost;
                System.out.println(String.format("%02d:%02d:%02d %02d:%02d:%02d %d $%.2f",
                        record.startTime.day, record.startTime.hour, record.startTime.minute,
                        record.finishTime.day, record.finishTime.hour, record.finishTime.minute,
                        record.getDuration(), cost));
            }

            System.out.println(String.format("Total amount: $%.2f", total));
        }
    }

    private static double calculateCost(Record record) {
        int startDay = record.startTime.day, endDay = record.finishTime.day;
        int startHour = record.startTime.hour, endHour = record.finishTime.hour;
        int startMinute = record.startTime.minute, endMinute = record.finishTime.minute;

        double cost = 0;

        while (startDay < endDay || (startDay == endDay && startHour < endHour)) {
            int nextHour = (startHour + 1) % 24;
            int duration = 60 - startMinute;

            if (startHour == endHour && startDay == endDay) {
                duration = endMinute - startMinute;
            }

            cost += (duration * chargeTable[startHour]) / 100.0;
            startHour = nextHour;
            startMinute = 0;

            if (startHour == 0) startDay++;
        }

        cost += (endMinute * chargeTable[endHour]) / 100.0;
        return cost;
    }
}
