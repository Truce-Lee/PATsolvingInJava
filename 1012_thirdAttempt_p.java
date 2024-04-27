import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
public class Main{
	static class Course{
		enum Name{
			C(2),M(3),E(4),A(1);
			final int priority;
			private Name(int priority) {
				this.priority=priority;
			}
		}
		int score;
		static Name[] names = Name.values();
		String name;
		int priority;
        int index;
		
		public Course(int index,int score) {
			this.score = score;
            this.index = index;
			this.name = names[index].toString();
			this.priority =names[index].priority;
		}
	}
	
	static class Student{
		String id;
		Course[] courses;
		public Student(String id,Course[] courses) {
			this.id = id;
			this.courses = courses;
		}
	}
	
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		
//		bf.readLine()
		String[] parts = bf.readLine().split(" ");
		int N = Integer.parseInt(parts[0]);
		int M = Integer.parseInt(parts[1]);
		int[][]scoreTable = new int[4][101];
		int[][]rankTable = new int[4][101];
		
		Map<String,Student> students = new HashMap<>(1024,1);
		for(int i=0;i<N;i++) {
			String[] line = bf.readLine().split(" ");
			String id = line[0];
			Course[] courses = new Course[4];
			int index = 0;
			int sum = 0;
			for(int j=1;j<line.length;j++) {
				int score = Integer.parseInt(line[j]);
				courses[index] = new Course(index,score);
				sum = sum+score;
				scoreTable[index][score]++;
				index++;
			}
			int avg = sum/index;
			scoreTable[index][avg]++;
			courses[index] = new Course(index,avg);
			Student student = new Student(id,courses);
			students.put(id, student);
		}

		for(int i =0; i<4;i++) {
			int[] scores = scoreTable[i];
			int rankNow = 1;
			for(int j =100;j>=0;j--) {
				if(scores[j]>0) {
					rankTable[i][j] = rankNow;
					rankNow=rankNow+scores[j];
				}
			}
		}
		
		
		for(int i =0;i<M;i++) {
			String quaryId = bf.readLine();
			Student student = students.get(quaryId);
			if(student==null) {
				System.out.println("N/A");
			}else {
			    Course[] courses = student.courses;
				int rank = Integer.MAX_VALUE;
				int priority = 4;
				String name = new String();
				for(int j =0 ;j<4;j++) {
					Course item = courses[j];
					if(rankTable[j][item.score]<rank ||(rankTable[j][item.score]==rank && item.priority<priority)) {
						rank = rankTable[j][item.score];
//						System.out.println(j+" "+item.score+" "+rank);
						name = item.name;
						priority = item.priority;
					}
				}
                System.out.println(rank+" "+name);
			}
		}
		
	}
}