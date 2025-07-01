import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void back(int depth, int sum) {
        if (depth == k) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!checked[i][j]) { // 선택하지 않은 값
                    if (check(i, j)) { // 위의 다음 칸이 인접한 칸인지 아닌지 체크
                        checked[i][j] = true;
                        back(depth+1, sum+map[i][j]);
                        checked[i][j] = false;
                    }
                }
            }
        }
    }

    private static boolean check(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue; // 범위 체크
            if (checked[nx][ny]) return false; // 인접한 칸
        }

        return true;
    }
}