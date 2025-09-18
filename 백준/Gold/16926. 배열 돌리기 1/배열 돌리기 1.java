import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, r;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; // ← ↑ → ↓
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken()); // 회전횟수

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int size = Math.min(n, m)/2;
        int nN = n;
        int mM = m;
        for (int i = 0; i < size; i++) {
            rotate(i, (nN*2)+(mM*2)-4);
            nN -= 2;
            mM -= 2;
        }
        // System.out.println(Arrays.deepToString(map));

        // print
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());

        br.close();
    }

    private static void rotate(int start, int len) {
        int repeat = r % len; // 반복횟수

        for (int i = 0; i < repeat; i++) {
            int tmp = map[start][start];

            int idx = 0;
            int x = start; int y = start;
            while (idx < 4) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                if (nx >= start && ny >= start && nx < n-start && ny < m-start) {
                    map[x][y] = map[nx][ny];
                    x = nx; y = ny;
                } else idx++;
            }
            map[start+1][start] = tmp;
        }
    }
}