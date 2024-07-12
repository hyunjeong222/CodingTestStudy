import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] checked; // 168421
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sx, sy; // 시작 위치
    static int objectCnt; // 물건 개수, 최대 5개
    static Queue<Pos> que;
    static public class Pos {
        int x; int y; int cnt; int time;
        public Pos (int x, int y, int cnt, int time) {
            this.x = x; this.y = y; this.cnt = cnt; this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        objectCnt = 0;
        for (int i = 0; i < m; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (tmp[j] == 'X') { // 물건
                    map[i][j] = objectCnt++;
                } else if (tmp[j] == 'S') { // 시작위치
                    map[i][j] = '.';
                    sx = i; sy = j;
                } else map[i][j] = tmp[j];
            }
        }
        // System.out.println(Arrays.deepToString(map));
        // System.out.println(objectCnt);
        int ans = bfs();
        System.out.println(ans);
    }

    private static int bfs() {
        int x = (1 << (objectCnt)) - 1;
        que = new LinkedList<>();
        checked = new boolean[m][n][x+1]; // 168421
        que.offer(new Pos(sx, sy, 0, 0));

       while (!que.isEmpty()) {
            Pos now = que.poll();
            int cnt = now.cnt;
            int time = now.time;

            if (map[now.x][now.y] == 'E') { // 출구
                if (cnt == x) return time; // 물건 다 챙김
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (rangeCheck(nx, ny) || map[nx][ny] == '#' || checked[nx][ny][cnt]) continue; // 벽이거나 이미 갖고있는 물건이라면 패쓰

                int nCnt = cnt;
                if (map[nx][ny] <= objectCnt) nCnt = nCnt | 1 << map[nx][ny];

                checked[nx][ny][nCnt] = true;
                que.offer(new Pos(nx, ny, nCnt, time+1));

            }
        }
       return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}