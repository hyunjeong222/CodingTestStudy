import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int startX, startY, endX, endY;
    static int[][] map;
    static boolean[][][] checked;
    static int[] dx = {0, 1, 0, -1}; // 우하좌상
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<Pos> ghost;
    static public class Pos {
        int x; int y;
        int time; int dir;
        public Pos(int x, int y, int time, int dir) {
            this.x = x; this.y = y;
            this.time = time; this.dir = dir;
        }

        public Pos(int x, int y, int time) {
            this.x = x; this.y = y;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken())-1;
        startY = Integer.parseInt(st.nextToken())-1;
        endX = Integer.parseInt(st.nextToken())-1;
        endY = Integer.parseInt(st.nextToken())-1;

        map = new int[n][m];
        checked = new boolean[4][n][m];
        ghost = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char tmp = str.charAt(j);

                if (tmp == '.') continue;

                if (tmp == '#') map[i][j] = -1; // 벽
                else { // 유령
                    int d = tmp-'0';
                    map[i][j] = -1; // 유령이 있는 곳도 갈 수 없음

                    for (int t = 0; t < 4; t++) {
                        int nd = (d + t) % 4;
                        ghost.add(new Pos(i, j, t, nd)); // 위치, 시간, 방향
                    }
                }
            }
        }

        // 유령 시야 세팅
        setGhost();

        int ans = bfs(startX, startY);

        System.out.println(ans == -1 ? "GG" : ans);

        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y, 0));
        checked[0][x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int t = (now.time + 1) % 4;

            if (now.x == endX && now.y == endY) return now.time;

            // 제자리
            if (!checked[t][now.x][now.y]) {
                checked[t][now.x][now.y] = true;
                que.offer(new Pos(now.x, now.y, now.time+1));
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (rangeCheck(nx, ny) || map[nx][ny] == -1 || checked[t][nx][ny]) continue;

                checked[t][nx][ny] = true;
                que.offer(new Pos(nx, ny, now.time+1));
            }
        }

        return -1;
    }

    private static void setGhost() {
        int x, y, time, dir;
        for (Pos now : ghost) {
            x = now.x; y = now.y;
            time = now.time; dir = now.dir;
             while (true) {
                 x += dx[dir];
                 y += dy[dir];

                 if (rangeCheck(x, y) || map[x][y] == -1) break;

                 checked[time][x][y] = true;
             }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}