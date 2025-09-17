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
        r = Integer.parseInt(st.nextToken()); // 회전의 수
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int size = Math.min(n, m)/2; // 공간 크기
        int nN = n;
        int mM = m;
        for (int i = 0; i < size; i++) {
            rotate(i, (2*nN)+(2*mM)-4);
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
        int repeat = r % len; // 회전 횟수
        for (int i = 0; i < repeat; i++) {
            int tmp = map[start][start]; // 마지막에 넣을 값

            int idx = 0; // 다음 값 탐색할 인덱스

            int x = start;
            int y = start;

            while (idx < 4) { // 네 방향(0~3)
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                if (nx >= start && ny >= start && nx < n-start && ny < m-start) {
                    // 경계 안 → 같은 방향으로 계속 이동
                    map[x][y] = map[nx][ny];
                    x = nx; y = ny;
                } else idx++; // 경계 밖 → 방향 바꾸기
            }
            map[start+1][start] = tmp;
        }
    }
}