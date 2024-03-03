import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int startX, startY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = input[j];
            }
        }
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                startX = i;
                startY = j;
                checked[i][j] = true;
                dfs(i, j, 1);
                checked[i][j] = false;
            }
        }
        System.out.println("No");
    }

    private static void dfs(int x, int y, int cnt) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if (nx == startX && ny == startY && cnt >= 4) {
                System.out.println("Yes");
                System.exit(0);
            }
            if(checked[nx][ny] || map[x][y] != map[nx][ny]) continue;

            checked[nx][ny] = true;
            dfs(nx, ny, cnt+1);
            checked[nx][ny] = false;
        }
    }
}