import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine()); // 반의 수
        StringTokenizer st;
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 각 반의 학생수
            Integer[] grades = new Integer[n];
            for (int j = 0; j < n; j++) {
                grades[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(grades, Collections.reverseOrder());

            sb.append("Class ").append(i).append("\n");
            sb.append("Max ").append(grades[0]).append(", ");
            sb.append("Min ").append(grades[grades.length-1]).append(", ");

            int gap = Integer.MIN_VALUE;
            for (int l = 0; l < grades.length-1; l++) {
                gap = Math.max(gap, grades[l]-grades[l+1]);
            }
            sb.append("Largest gap ").append(gap).append("\n");
        }

        System.out.println(sb.toString());


        br.close();
    }
}