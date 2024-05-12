import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static class Grade {
        String id;
        int C, M, E, A;
        int rankC = Integer.MAX_VALUE, rankM = Integer.MAX_VALUE, rankE = Integer.MAX_VALUE, rankA = Integer.MAX_VALUE;
        String bestSubject = "";
        int bestRank = Integer.MAX_VALUE;

        public Grade(String id, int C, int M, int E) {
            this.id = id;
            this.C = C;
            this.M = M;
            this.E = E;
            this.A = (C + M + E) / 3;
        }

        void updateBestRank() {
            if (rankA < bestRank) {
                bestRank = rankA;
                bestSubject = "A";
            }
            if (rankC < bestRank) {
                bestRank = rankC;
                bestSubject = "C";
            }
            if (rankM < bestRank) {
                bestRank = rankM;
                bestSubject = "M";
            }
            if (rankE < bestRank) {
                bestRank = rankE;
                bestSubject = "E";
            }
        }
        int getScore(String subject) {
            switch (subject) {
                case "A": return this.A;
                case "C": return this.C;
                case "M": return this.M;
                case "E": return this.E;
                default: return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int studentsNum = Integer.parseInt(firstLine[0]);
        int studentsCheck = Integer.parseInt(firstLine[1]);

        List<Grade> grades = new ArrayList<Grade>();
        Map<String, Grade> gradeMap = new HashMap<String, Grade>();

        for (int i = 0; i < studentsNum; i++) {
            String[] studentData = reader.readLine().split(" ");
            String id = studentData[0];
            int C = Integer.parseInt(studentData[1]);
            int M = Integer.parseInt(studentData[2]);
            int E = Integer.parseInt(studentData[3]);
            Grade grade = new Grade(id, C, M, E);
            grades.add(grade);
            gradeMap.put(id, grade);
        }

        // Sort and rank for each subject and average
        rankStudents(grades, "C");
        rankStudents(grades, "M");
        rankStudents(grades, "E");
        rankStudents(grades, "A");

        for (Grade grade : grades) {
            grade.updateBestRank();
        }

        // Responding to queries about student ranks
        for (int i = 0; i < studentsCheck; i++) {
            String queryId = reader.readLine();
            Grade queriedGrade = gradeMap.get(queryId);
            if (queriedGrade != null) {
                System.out.println(queriedGrade.bestRank + " " + queriedGrade.bestSubject);
            } else {
                System.out.println("N/A");
            }
        }
    }

    private static void rankStudents(List<Grade> grades, String subject) {
        grades.sort(new Comparator<Grade>() {
            @Override
            public int compare(Grade g1, Grade g2) {
                return Integer.compare(g2.getScore(subject), g1.getScore(subject)); // Descending order
            }
        });
        int rank = 1;
        int lastScore = -1;
        for (int i = 0; i < grades.size(); i++) {
            Grade grade = grades.get(i);
            if (i == 0 || grade.getScore(subject) != lastScore) {
                lastScore = grade.getScore(subject);
                rank = i + 1;
            }
            switch (subject) {
                case "C": grade.rankC = rank; break;
                case "M": grade.rankM = rank; break;
                case "E": grade.rankE = rank; break;
                case "A": grade.rankA = rank; break;
            }
        }
    }
}

