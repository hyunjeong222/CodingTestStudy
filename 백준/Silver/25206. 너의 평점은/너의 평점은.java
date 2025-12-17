import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static public String[] gradeList = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F"};
    static public double[] scoreList = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        double scoreSum = 0;//  전공과목별 (학점 × 과목평점)의 합
        double totalSum = 0; // 학점의 총합
        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            String subject = st.nextToken();
            double grade = Double.parseDouble(st.nextToken());
            String subjectAvg = st.nextToken();

            if (subjectAvg.equals("P")) continue;

            for (int j = 0; j < 9; j++) {
                if (subjectAvg.equals(gradeList[j])) {
                    scoreSum += grade*scoreList[j];
                    totalSum += grade;
                }
            }
        }

        // 전공 평점 - 전공과목별 (학점 × 과목평점)의 합을 학점의 총합으로 나눈 값
        double average = scoreSum / totalSum;

        System.out.printf("%.6f\n", average);

        br.close();
    }
}