import java.io.*;
import java.util.*;

public class StudentScoreProcessor {
    
    static class Student {
        String name;
        int[] scores = new int[3];
        int subTotal;
        double average;
        String level;
        int rank;
    }

    public static void main(String[] args) {
        String inputFile = "Student_Scores_with_Empty_Columns.csv";
        String outputFile = "Student_Scores3.txt";

        List<String[]> data = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        // 讀取 CSV 檔案
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // 資料處理
        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (row.length < 4) continue;

            Student student = new Student();
            student.name = row[0].trim();
            int validCount = 0;
            for (int j = 1; j <= 3; j++) {
                if (j < row.length && !row[j].trim().isEmpty()) {
                    student.scores[j - 1] = Integer.parseInt(row[j].trim());
                    student.subTotal += student.scores[j - 1];
                    validCount++;
                } else {
                    student.scores[j - 1] = -1;
                }
            }
            student.average = validCount > 0 ? (double) student.subTotal / validCount : 0.0;

            if (student.average >= 90) student.level = "A";
            else if (student.average >= 80) student.level = "B";
            else if (student.average >= 70) student.level = "C";
            else if (student.average >= 60) student.level = "D";
            else student.level = "F";

            students.add(student);
        }

        // 排名處理（依 Sub_Total 由高到低排序）
        students.sort((a, b) -> Integer.compare(b.subTotal, a.subTotal));
        int currentRank = 1;
        for (int i = 0; i < students.size(); i++) {
            if (i > 0 && students.get(i).subTotal == students.get(i - 1).subTotal) {
                students.get(i).rank = students.get(i - 1).rank;
            } else {
                students.get(i).rank = currentRank;
            }
            currentRank++;
        }

        // 輸出含 Rank 的結果到 txt 檔
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String header = "Student Name    Score 1  Score 2  Score 3  Sub_Total  Average  Level  Rank";
            String divider = "---------------------------------------------------------------------------";

            writer.write(header + "\n");
            writer.write(divider + "\n");
            System.out.println(header);
            System.out.println(divider);

            for (Student s : students) {
                StringBuilder line = new StringBuilder();
                line.append(String.format("%-15s", s.name));
                for (int score : s.scores) {
                    line.append(String.format("%-8s", (score == -1) ? " " : score));
                }
                line.append(String.format("%-10d %-8.2f %-6s %-4d", s.subTotal, s.average, s.level, s.rank));

                writer.write(line.toString() + "\n");
                System.out.println(line.toString());
            }

            System.out.println("✅ 處理完成！輸出檔案為：" + outputFile);

        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}
