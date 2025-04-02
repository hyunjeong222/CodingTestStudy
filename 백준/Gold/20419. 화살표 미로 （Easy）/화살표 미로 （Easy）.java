import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static int[][] map;
    static boolean[][][][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] tl = {2, 3, 1, 0};
    static int[] tr = {3, 2, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        checked = new boolean[r][c][k+1][k+1]; // 행열좌우

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                char tmp = str.charAt(j);
                if (tmp == 'U') map[i][j] = 0;
                else if (tmp == 'R') map[i][j] = 3;
                else if (tmp == 'D') map[i][j] = 1;
                else map[i][j] = 2; // L
            }
        }

        boolean flag = bfs();

        System.out.println(flag ? "Yes" : "No");
    }

    public static boolean bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{0, 0, k, k});
        checked[0][0][k][k] = true;

        while (!que.isEmpty()) {
            int[] now = que.poll();
            int x = now[0], y = now[1],
                left = now[2], right = now[3];

            if (x == r - 1 && y == c - 1) return true;

            int d = map[x][y]; // 현재 방향
            // 기존 방향
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (rangeCheck(nx, ny) && !checked[nx][ny][left][right]) {
                checked[nx][ny][left][right] = true;
                que.offer(new int[]{nx, ny, left, right});
            }

            // 좌회전
            if (left > 0) {
                int nd = tl[d];
                nx = x + dx[nd];
                ny = y + dy[nd];
                if (rangeCheck(nx, ny) && !checked[nx][ny][0][right]) {
                    checked[nx][ny][0][right] = true;
                    que.offer(new int[]{nx, ny, 0, right});
                }
            }

            // 우회전
            if (right > 0) {
                int nd = tr[d];
                nx = x + dx[nd];
                ny = y + dy[nd];
                if (rangeCheck(nx, ny) && !checked[nx][ny][left][0]) {
                    checked[nx][ny][left][0] = true;
                    que.offer(new int[]{nx, ny, left, 0});
                }
            }
        }

        return false;
    }

    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}