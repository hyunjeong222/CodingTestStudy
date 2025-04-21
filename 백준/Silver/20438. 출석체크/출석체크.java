import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생의 수
        int k = Integer.parseInt(st.nextToken()); // 졸고 있는 학생 수
        int q = Integer.parseInt(st.nextToken()); // 지환이가 출석 코드를 보낼 학생 수
        int m = Integer.parseInt(st.nextToken()); // 구간의 수

        int[] students = new int[n+3];
        int idx;
        st = new StringTokenizer(br.readLine()); // 졸고 있는 학생 입장 번호 정보
        for (int i = 0; i < k; i++) {
            idx = Integer.parseInt(st.nextToken());
            students[idx] = -1; // 졸고있는 학생
        }

        st = new StringTokenizer(br.readLine()); // 출석 코드를 받을 학생의 입장 번호 정보
        for (int i = 0; i < q; i++) {
            idx = Integer.parseInt(st.nextToken());
            if (students[idx] != -1) {
                for (int idx2 = idx; idx2 < n+3; idx2 += idx) {
                    if (students[idx2] != -1) {
                        students[idx2] = 1; // 출석체크 한 학생
                    }
                }
            }
        }

        int[] sum = new int[n+3];
        for (int i = 3; i < sum.length; i++) {
            sum[i] = sum[i-1] + (students[i] != 1 ? 1 : 0);
        }

        StringBuilder sb = new StringBuilder();
        int s, e;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            sb.append(sum[e]-sum[s-1]).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}