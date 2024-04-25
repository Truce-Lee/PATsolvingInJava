import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        if (num == 0) {
            System.exit(0);
        }
        String[][] records = new String[num][3];
        for (int i = 0; i < num; i++) {
            records[i][0] = s.next(); // ID_number
            records[i][1] = s.next(); // Sign_in_time
            records[i][2] = s.next(); // Sign_out_time
        }
        s.close();

        int earlyIndex = 0;
        int lateIndex = 0;
        for (int i = 1; i < num; i++) {
            if (compareTime(records[i][1], records[earlyIndex][1])) { // Earliest sign-in time
                earlyIndex = i;
            }
            if (!compareTime(records[i][2], records[lateIndex][2])) { // Latest sign-out time
                lateIndex = i;
            }
        }
        System.out.println(records[earlyIndex][0] + " " + records[lateIndex][0]);
    }

    private static boolean compareTime(String time1, String time2) {
        String part1[] = time1.split(":");
        String part2[] = time2.split(":");
        int hour1 = Integer.parseInt(part1[0]);
        int hour2 = Integer.parseInt(part2[0]);
        int minute1 = Integer.parseInt(part1[1]);
        int minute2 = Integer.parseInt(part2[1]);
        int second1 = Integer.parseInt(part1[2]);
        int second2 = Integer.parseInt(part2[2]);
        return (hour1 < hour2) || (hour1 == hour2 && minute1 < minute2) || (hour1 == hour2 && minute1 == minute2 && second1 < second2);
    }
}
