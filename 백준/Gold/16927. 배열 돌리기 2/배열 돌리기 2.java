import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, r;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken()); // 회전의 수
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int N = n;
        int M = m;

        int size = Math.min(n, m)/2; // 공간 크기
        int nN = n;
        int mM = m;
        for (int i = 0; i < size; i++) {
            rotate(i, (2*nN)+(2*mM)-4);
            n -= 1;
            m -= 1;
            nN -= 2;
            mM -= 2;
        }
        // System.out.println(Arrays.deepToString(map));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void rotate(int start, int len) {
        int repeat = r % len; // 회전 횟수
        for (int i = 0; i < repeat; i++) {
            int tmp = map[start][start];
            for (int j = start; j < m-1; j++) { // ←
                map[start][j] = map[start][j+1];
            }
            for (int j = start; j < n-1; j++) { // ↑
                map[j][m-1] = map[j+1][m-1];
            }
            for (int j = m-1; j > start; j--) { // →
                map[n-1][j] = map[n-1][j-1];
            }
            for (int j = n-1; j > start; j--) { // ↓
                map[j][start] = map[j-1][start];
            }
            map[start+1][start] = tmp;
        }
    }
}