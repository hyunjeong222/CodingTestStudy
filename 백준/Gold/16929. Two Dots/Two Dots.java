import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int startX, startY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                startX = i;
                startY = j;
                char color = map[i][j];
                dfs(i, j, color, 1);
            }
        }
        System.out.println("No");
    }

    private static void dfs(int x, int y, char color, int cnt) {
        if (cnt >= 4 && x == startX && y == startY) {
            System.out.println("Yes");
            System.exit(0);
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || checked[nx][ny] || color != map[nx][ny]) continue;

            checked[nx][ny] = true;
            dfs(nx, ny, color, cnt+1);
            checked[nx][ny] = false;
        }
    }
}