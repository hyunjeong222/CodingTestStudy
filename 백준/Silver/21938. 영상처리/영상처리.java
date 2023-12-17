import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, t;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 3; k++) {
                    sum += Integer.parseInt(st.nextToken());
                }
                map[i][j] = (int)Math.floor(sum/3);
                sum = 0;
            }
        }
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= t && !checked[i][j]) {
                    dfs(i, j);
                    ans++;
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

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            if (!checked[nx][ny] && map[nx][ny] >= t) {
                checked[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }
}