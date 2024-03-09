import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {0, 1}; static int[] dy = {1, 0};
    static int max = Integer.MIN_VALUE; static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
        checked = new boolean[n][n];
        dfs(0, 0, map[0][0]-'0');
        System.out.println(max + " " + min);
    }

    private static void dfs(int x, int y, int num) {
        if (x == n-1 && y == n-1) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }
        checked[x][y] = true;
        for (int i = 0; i < 2; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (checked[nx][ny]) continue;

            if (map[x][y] == '+') dfs(nx, ny, num+(map[nx][ny]-'0'));
            else if (map[x][y] == '-') dfs(nx, ny, num-(map[nx][ny]-'0'));
            else if (map[x][y] == '*') dfs(nx, ny, num*(map[nx][ny]-'0'));
            else dfs(nx, ny, num);
        }
        checked[x][y] = false;
    }
}