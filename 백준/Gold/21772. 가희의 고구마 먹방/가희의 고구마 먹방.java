import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c, t;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Pos[] spot;
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        checked = new boolean[r][c];
        spot = new Pos[1];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'G') spot[0] = new Pos(i, j);
            }
        }
        dfs(spot[0].x, spot[0].y, 0, 0);
        System.out.println(ans);
    }

    private static void dfs(int x, int y, int depth, int cnt) {
        if (depth >= t) {
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if (map[nx][ny] == '#') continue;

            if (map[nx][ny] == 'S') {
                map[nx][ny] = '.';
                dfs(nx, ny, depth+1, cnt+1);
                map[nx][ny] = 'S';
            } else {
                dfs(nx, ny, depth+1, cnt);
            }
        }
    }
}