import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int startX, startY, endX, enxY;
    static int[][] map;
    static boolean[][][] checked;
    static ArrayList<Pos> ghost;
    static int[] dx = {0, 1, 0, -1}; // 우하좌상
    static int[] dy = {1, 0, -1, 0};
    static public class Pos {
        int x; int y;
        int time; int dir;
        public Pos (int x, int y, int time, int dir) {
            this.x = x; this.y = y;
            this.time = time; this.dir = dir;
        }
        public Pos (int x, int y, int time) {
            this.x = x; this.y = y;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열

        map = new int[n][m]; // 유령의 집
        checked = new boolean[4][n][m]; // 시간, 위치

        st = new StringTokenizer(br.readLine());
        // 입구
        startX = Integer.parseInt(st.nextToken())-1;
        startY = Integer.parseInt(st.nextToken())-1;
        // 출구
        endX = Integer.parseInt(st.nextToken())-1;
        enxY = Integer.parseInt(st.nextToken())-1;

        ghost = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char tmp = str.charAt(j);

                if (tmp == '.') continue; // 움직일 수 있는 공간

                if (tmp == '#') map[i][j] = -1; // 벽, 움직일 수 없는 공간
                else { // 유령
                    int d = tmp - '0';
                    map[i][j] = -1; // 유령이 있는 곳도 갈 수 없음

                    for (int t = 0; t < 4; t++) {
                        int nd = (d + t) % 4;
                        ghost.add(new Pos(i, j, t, nd));
                    }
                }
            }
        }

        setGhost();

        int ans = bfs();

        System.out.println(ans != -1 ? ans : "GG");
    }

    private static int bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(startX, startY, 0));
        checked[0][startX][startY] = true;

        int x, y, t;
        while (!que.isEmpty()) {
            Pos now = que.poll();

            x = now.x; y = now.y;
            t = (now.time + 1)%4;

            if (x == endX && y == enxY) return now.time;

            // 제자리
            if (!checked[t][x][y]) {
                checked[t][x][y]= true;
                que.offer(new Pos(x, y, now.time+1));
            }

            // 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (rangeCheck(nx, ny) || map[nx][ny] == -1 || checked[t][nx][ny]) continue;

                checked[t][nx][ny] = true;
                que.offer(new Pos(nx ,ny, now.time+1));
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