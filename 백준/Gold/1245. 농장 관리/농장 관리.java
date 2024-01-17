import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, -1, 1, 1, -1, 1, -1};
    static boolean flag;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        checked = new boolean[n][m];
        ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!checked[i][j] && map[i][j] != 0) {
                    flag = true;
                    dfs(i, j);
                    if (flag) ans++;
                }
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y) {
        checked[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            if (map[nx][ny] > map[x][y]) flag = false;
            if (!checked[nx][ny] && map[nx][ny] == map[x][y]) {
                dfs(nx, ny);
            }
        }
    }
}