import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 학생 수

        int[] prefix = new int[1000002];
        int s, e;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 시작시간
            e = Integer.parseInt(st.nextToken()); // 종료시간

            prefix[s]++;
            // 각 좌석은 사용이 종료되는 시각에 곧바로 선택될 수 없다.
            prefix[e+1]--;
        }

        for (int i = 1; i < 1000001; i++) {
            prefix[i] += prefix[i-1];
        }

        int Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int q;
        StringBuilder sb = new StringBuilder();
        while (Q --> 0) {
            q = Integer.parseInt(st.nextToken());
            sb.append(prefix[q]).append("\n");
        }

        System.out.println(sb.toString());
    }
}