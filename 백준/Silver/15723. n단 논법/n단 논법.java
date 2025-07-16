import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 정수
        boolean[][] dist = new boolean[26][26];
        StringTokenizer st;
        int start, end;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 모든 a는 b
            start = st.nextToken().charAt(0) - 'a';
            st.nextToken();
            end = st.nextToken().charAt(0) - 'a';

            dist[start][end] = true;
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] && dist[k][j]) dist[i][j] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            start = st.nextToken().charAt(0) - 'a';
            st.nextToken();
            end = st.nextToken().charAt(0) - 'a';

            if (dist[start][end]) sb.append("T").append("\n");
            else sb.append("F").append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}