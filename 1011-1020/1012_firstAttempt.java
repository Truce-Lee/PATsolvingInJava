import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
	static class Grade{
		String id;
		int C;
		int M;
		int E;
		int A;
		int rank = Integer.MAX_VALUE-1;
		String acc ="";
		public Grade(String id,int C,int M,int E) {
			this.id = id;
			this.C= C;
			this.M=M;
			this.E=E;
			this.A=(C+M+E)/3;
		}
		int get(String s) {
			if(s.equals("A")) {
				return this.A;
			}else if(s.equals("C")) {
				return this.C;
			}else if(s.equals("M")) {
				return this.M;
			}else if(s.equals("E")) {
				return this.E;
			}
			return 0;
		}
		
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		List<Grade> gradeList = new ArrayList<>();
		int studentsNum = s.nextInt();
		int studentsCheck = s.nextInt();
		for(int i =0;i<studentsNum;i++) {
			Grade grade = new Grade(s.next(),s.nextInt(),s.nextInt(),s.nextInt());
			gradeList.add(grade);
		}
		String studentsId[] = new String[studentsCheck];
		for(int i=0;i<studentsCheck;i++) {
			studentsId[i]=s.next();
		}		
		s.close();
		
		rankReset(gradeList,"A");
		rankReset(gradeList,"C");
		rankReset(gradeList,"M");
		rankReset(gradeList,"E");
		
		for(int i=0;i<studentsCheck;i++) {
			int flag = 0;
			for(Grade grade:gradeList) {
				if(studentsId[i].equals(grade.id)) {
					System.out.println(grade.rank+" "+grade.acc);
				}else {
					flag++;
				}
			}
			if (flag==gradeList.size()) {
				System.out.println("N/A");
			}
		}	
		

	}

	private static void rankReset(List<Grade> gradeList, String s) {
		Collections.sort(gradeList,new Comparator<Grade>(){
			@Override
			public int compare(Grade o1, Grade o2) {
				return Integer.compare(o2.get(s), o1.get(s));
			}
		});
		for(Grade grade:gradeList) {
			if(grade.rank>gradeList.indexOf(grade)+1) {
				grade.rank=gradeList.indexOf(grade)+1;
				grade.acc=s;
			}
		}
		
	}

//	private static void printId(List<Grade> gradeList) {
//		for(Grade grade:gradeList) {
//			System.out.println(grade.id);
//		}
//	}
//	private static void printRank(List<Grade> gradeList) {
//		for(Grade grade:gradeList) {
//			System.out.println(grade.rank);
//		}
//	}
}
