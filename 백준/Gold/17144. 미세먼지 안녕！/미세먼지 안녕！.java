import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, t;
    static int[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static Queue<Pos> que;
    static public class Pos {
        int x; int y; int d;
        public Pos(int x, int y, int d) {
            this.x = x; this.y = y; this.d = d;
        }
    }
    static int airCleaner = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1 && airCleaner == -1) airCleaner = i;
            }
        }

        while (t --> 0) {
            checkDusty();
            spreadDusty();
            operateAir();
        }

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) continue;
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    private static void checkDusty() {
        que = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) continue;
                que.offer(new Pos(i, j, map[i][j]));
            }
        }
    }

    private static void spreadDusty() {
        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.d < 5) continue;
            int amount = now.d / 5;
            int cnt = 0;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] == -1) continue;

                map[nx][ny] += amount;
                cnt++;
            }

            map[now.x][now.y] -= (amount*cnt);
        }
    }

    private static void operateAir() {
        int top = airCleaner;
        int down = airCleaner+1;

        for (int i = top-1; i > 0; i--) {
            map[i][0] = map[i-1][0];
        }
        for (int i = 0; i < c-1; i++) {
            map[0][i] = map[0][i+1];
        }
        for (int i = 0; i < top; i++) {
            map[i][c-1] = map[i+1][c-1];
        }
        for (int i = c-1; i > 1; i--) {
            map[top][i] = map[top][i-1];
        }
        map[top][1] = 0;
        for (int i = down+1; i < r-1; i++) {
            map[i][0] = map[i+1][0];
        }
        for (int i = 0; i < c-1; i++) {
            map[r-1][i] = map[r-1][i+1];
        }
        for (int i = r-1; i > down; i--) {
            map[i][c-1] = map[i-1][c-1];
        }
        for (int i = c-1; i > 1; i--) {
            map[down][i] = map[down][i-1];
        }
        map[down][1] = 0;
    }
}