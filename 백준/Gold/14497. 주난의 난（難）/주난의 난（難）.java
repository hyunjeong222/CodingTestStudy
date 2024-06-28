import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int jx, jy, cx, cy;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y; int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x; this.y = y; this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m];
        st = new StringTokenizer(br.readLine());
        // 주난의 위치
        jy = Integer.parseInt(st.nextToken())-1;
        jx = Integer.parseInt(st.nextToken())-1;
        // 범인의 위치
        cy = Integer.parseInt(st.nextToken())-1;
        cx = Integer.parseInt(st.nextToken())-1;
        for (int j = 0; j < n; j++) {
            String str = br.readLine();
            for (int i = 0; i < m; i++) {
                char c = str.charAt(i);
                if (c == '#' || c == '*') continue;
                map[j][i] = c - '0';
            }
        }
        map[cy][cx] = 1;
        int ans = bfs();
        // System.out.println(Arrays.deepToString(map));
        System.out.println(ans);
        br.close();
    }

    private static int bfs() {
        ArrayDeque<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(jx, jy, 0));
        checked[jy][jx] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == cx && now.y == cy) return now.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (checked[ny][nx]) continue;

                checked[ny][nx] = true;
                if (map[ny][nx] == 0) que.offerFirst(new Pos(nx, ny, now.cnt));
                else que.offerLast(new Pos(nx, ny, now.cnt+1));
            }
        }
        return -1;
    }
}