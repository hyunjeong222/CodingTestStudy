import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        checked = new boolean[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = bfs(0, 0);

        System.out.println(flag ? "HaruHaru" : "Hing");

        br.close();
    }

    private static boolean bfs(int x, int y) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (map[now.x][now.y] == -1) return true;

            int moveCnt = map[now.x][now.y];

            for (int i = 0; i < 2; i++) {
                int nx = now.x + dx[i] * moveCnt;
                int ny = now.y + dy[i] * moveCnt;


                if (!rangeCheck(nx, ny) && !checked[nx][ny]) {
                    checked[nx][ny] = true;
                    que.offer(new Pos(nx, ny));
                }
            }
        }

        return false;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}