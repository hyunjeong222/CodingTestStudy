import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][][] checked; // 위치, 방향, 선물
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y; int dir; int cnt; int time;
        public Pos (int x, int y, int dir, int cnt, int time) {
            this.x = x; this.y = y; this.dir = dir; this.cnt = cnt; this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int sx = 0, sy = 0;
        int presentCnt = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = str.charAt(j);
                if (c == 'S') {
                    sx = i; sy = j;
                    map[i][j] = '.';
                } else if (c == 'C') {
                    map[i][j] = presentCnt++;
                } else map[i][j] = c;
            }
        }

        int ans = bfs(sx, sy, presentCnt);
        System.out.println(ans);
    }

    private static int bfs(int sx, int sy, int presentCnt) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(sx, sy, -1, 0, 0));
        int num = (1 << presentCnt)-1;
        checked = new boolean[n][m][4][num+1];

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int nowDir = now.dir;
            int nowCnt = now.cnt;
            int nowTime = now.time;

            if (nowCnt == num) return nowTime;

            for (int i = 0; i < 4; i++) {
                if (i == nowDir) continue; // 같은 방향을 연속으로 갈 수 없음

                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                int nextCnt = nowCnt;

                // 범위 아웃, 민식이가 갈 수 없는 곳, 이미 전달한 물건
                if (rangeCheck(nx, ny) || map[nx][ny] == '#' || checked[nx][ny][i][nowCnt]) continue;
                if (map[nx][ny] <= presentCnt) nextCnt |= (1 << map[nx][ny]);

                que.offer(new Pos(nx, ny, i, nextCnt, nowTime+1));
                checked[nx][ny][i][nextCnt] = true;

            }
        }
        return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}