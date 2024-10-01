import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] checked;
    static public class Pos {
        int x; int y; int dir; int cnt;
        public Pos (int x, int y, int dir, int cnt) {
            this.x = x; this.y = y; this.dir = dir; this.cnt = cnt;
        }
    }
    static int sx, sy, sd;
    static int ex, ey, ed;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        // 시작 위치
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        sd = Integer.parseInt(st.nextToken())-1; // 바라보는 방향

        st = new StringTokenizer(br.readLine());
        // 종료
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;
        ed = Integer.parseInt(st.nextToken())-1; // 바라보는 방향

        int ans = bfs(); // 도착지점까지 필요한 최소 명령 횟수
        System.out.println(ans);
    }

    private static int bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(sx, sy, sd, 0));
        checked[sx][sy][sd] = true;

        // 동 0 서 1 남 2 북 3
        // 0 움직일 수 있음, 1 없음
        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == ex && now.y == ey && now.dir == ed) return now.cnt;

            // go : 1, 2 또는 3칸 이동
            for (int k = 1; k <= 3; k++) {
                int nx = now.x + dx[now.dir] * k;
                int ny = now.y + dy[now.dir] * k;

                // 범위, 방문체크
                if (rangeCheck(nx, ny)) continue;
                if (map[nx][ny] == 1) break; // 벽인 경우 완전히 끝내기

                if (!checked[nx][ny][now.dir]) {
                    checked[nx][ny][now.dir] = true;
                    que.offer(new Pos(nx, ny, now.dir, now.cnt+1));
                }
            }

            // turn dir : 각각 왼쪽, 오른쪽으로 90도 회전
            // 왼쪽 : 동 0 -> 북 3 -> 서 1 -> 남 2
            // 오른쪽 : 동 0 -> 남 2 -> 서 1 -> 븍 3
            int left = 0; int right = 0;
            switch (now.dir) {
                case 0 : right = 2; left = 3; break;
                case 1 : right = 3; left = 2; break;
                case 2 : right = 1; left = 0; break;
                case 3 : right = 0; left = 1; break;
            }

            if (!checked[now.x][now.y][left]) {
                checked[now.x][now.y][left] = true;
                que.offer(new Pos(now.x, now.y, left, now.cnt+1));
            }
            if (!checked[now.x][now.y][right]) {
                checked[now.x][now.y][right] = true;
                que.offer(new Pos(now.x, now.y, right, now.cnt+1));
            }
        }

        return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}