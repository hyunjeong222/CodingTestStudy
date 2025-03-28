import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int startX, startY, p;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // nxm 행렬
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m]; // 물의 양
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        // 로하가 살고있는 땅 위치
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken()); // 파이프를 만들기 위한 재료

        ans = map[startX][startY];

        dfs(startX, startY, -1, p, map[startX][startY]);

        System.out.println(ans);
    }

    private static void dfs(int x, int y, int dir, int cnt, int sum) {
        if (checked[x][y]) return;

        ans = Math.max(ans, sum);
        if (cnt == 0) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (rangeCheck(nx, ny)) continue;

            checked[x][y] = true;
            if (dir != -1 && dir != i) { // 방향이 다르면 구부린
                if (cnt-2 >= 0) dfs(nx, ny, i, cnt-2, sum+map[nx][ny]);
            } else { // 방향이 같으면 일자
                dfs(nx, ny, i, cnt-1, sum+map[nx][ny]);
            }
            checked[x][y] = false;
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}