package aPulsBPoly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class TheBestRank {
    static class Grade {
        String id;
        int C, M, E, A;
        int rank = Integer.MAX_VALUE;
        String bestSubject = "";
        int priority = 0;

        public Grade(String id, int C, int M, int E) {
            this.id = id;
            this.C = C;
            this.M = M;
            this.E = E;
            this.A = (C + M + E) / 3;
        }

        void setRank(int rank, String sub) {
            if (rank < this.rank || (rank == this.rank && getPriority(sub) > this.priority)) {
                this.rank = rank;
                bestSubject = sub;
                priority = getPriority(sub);
            }
        }

        private int getPriority(String sub) {
            switch (sub) {
                case "A": return 4;
                case "C": return 3;
                case "M": return 2;
                case "E": return 1;
                default: return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int studentsNum = Integer.parseInt(firstLine[0]);
        int studentsCheck = Integer.parseInt(firstLine[1]);

        TreeMap<Integer, ArrayList<String>> scoreTableC = new TreeMap<>((a, b) -> b - a);
        TreeMap<Integer, ArrayList<String>> scoreTableM = new TreeMap<>((a, b) -> b - a);
        TreeMap<Integer, ArrayList<String>> scoreTableE = new TreeMap<>((a, b) -> b - a);
        TreeMap<Integer, ArrayList<String>> scoreTableA = new TreeMap<>((a, b) -> b - a);

        HashMap<String, Grade> grades = new HashMap<>();
        for (int i = 0; i < studentsNum; i++) {
            String[] studentData = reader.readLine().split(" ");
            String id = studentData[0];
            int C = Integer.parseInt(studentData[1]);
            int M = Integer.parseInt(studentData[2]);
            int E = Integer.parseInt(studentData[3]);
            Grade grade = new Grade(id, C, M, E);
            grades.put(id, grade);
            scoreTableC.computeIfAbsent(C, k -> new ArrayList<>()).add(id);
            scoreTableM.computeIfAbsent(M, k -> new ArrayList<>()).add(id);
            scoreTableE.computeIfAbsent(E, k -> new ArrayList<>()).add(id);
            scoreTableA.computeIfAbsent(grade.A, k -> new ArrayList<>()).add(id);
        }

        processScores(scoreTableC, grades, "C");
        processScores(scoreTableM, grades, "M");
        processScores(scoreTableE, grades, "E");
        processScores(scoreTableA, grades, "A");

        // Handling queries for student ranks
        for (int i = 0; i < studentsCheck; i++) {
            String queryId = reader.readLine();
            Grade grade = grades.get(queryId);
            if (grade != null) {
                System.out.println(grade.rank + " " + grade.bestSubject);
            } else {
                System.out.println("N/A");
            }
        }
    }

    private static void processScores(TreeMap<Integer, ArrayList<String>> scoreTable, HashMap<String, Grade> grades, String subject) {
        int rank = 1;
        for (var entry : scoreTable.entrySet()) {
            ArrayList<String> ids = entry.getValue();
            for (String id : ids) {
                grades.get(id).setRank(rank, subject);
            }
            rank += ids.size();
        }
    }
}
