import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] map;
    static boolean[][][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> que;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m][k+1];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        int ans = bfs(0, 0);
        System.out.println(ans);
    }

    private static int bfs(int x, int y) {
        que = new LinkedList<>();
        int cnt = 1;
        int wall = 0;
        int day = 0;
        que.offer(new int[]{x, y, cnt, wall, day});
        checked[x][y][0] = true;

        int[] now;
        while (!que.isEmpty()) {
            now = que.poll();
            x = now[0];
            y = now[1];
            cnt = now[2];
            wall = now[3];
            day = now[4];

            if (x == n-1 && y == m-1) return cnt;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (map[nx][ny] == 0) {
                    if (checked[nx][ny][wall]) continue;
                    que.offer(new int[]{nx, ny, cnt+1, wall, (day+1)%2});
                    checked[nx][ny][wall] = true;
                } else if (map[nx][ny] == 1 && wall < k){
                    if (day == 0 && !checked[nx][ny][wall+1]) {
                        que.offer(new int[]{nx, ny, cnt+1, wall+1, 1});
                        checked[nx][ny][wall+1] = true;
                    } else if (day == 1) {
                        que.offer(new int[]{x, y, cnt+1, wall, 0});
                    }
                }
            }
        }
        return -1;
    }
}