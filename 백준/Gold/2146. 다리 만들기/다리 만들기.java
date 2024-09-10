import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] checked;
    static Queue<Pos> que;
    static public class Pos {
        int x; int y; int cnt;
        public Pos (int x, int y, int cnt) {
            this.x = x; this.y = y; this.cnt = cnt;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        checked = new boolean[n][n];
        int num = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    // 섬 그룹핑
                    dfs(i, j, num++);
                }
            }
        }

        // System.out.println(Arrays.deepToString(map));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 1) {
                    bfs(i, j, map[i][j]);
                }
            }
        }

        System.out.println(min);
    }

    private static void bfs(int x, int y, int num) {
        checked = new boolean[n][n];
        checked[x][y] = true;
        que = new LinkedList<>();
        que.offer(new Pos(x, y, 0));

        while (!que.isEmpty()) {
            Pos now = que.poll();

            // 현재 지점이 바다가 아니고, 탐색을 시작한 섬의 번호와 현재 번호가 다르면
            // 다리를 생성할 수 있으므로 최솟값 갱신
            if (map[now.x][now.y] != 0 && map[now.x][now.y] != num) {
                min = Math.min(min, now.cnt-1);
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (rangeCheck(nx, ny) || checked[nx][ny]) continue;
                // 섬의 테두리만 탐색
                // 다음 지점이 바다와 연결되어 있는 테두리만 탐색 가능
                if (map[nx][ny] == num) continue;

                que.offer(new Pos(nx, ny, now.cnt+1));
                checked[nx][ny] = true;
            }
        }
    }

    private static void dfs(int x, int y, int num) {
        checked[x][y] = true;
        map[x][y] = num;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (rangeCheck(nx, ny) || checked[nx][ny]) continue;
            if (map[nx][ny] != 1) continue;

            dfs(nx, ny, num);
        }

    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}