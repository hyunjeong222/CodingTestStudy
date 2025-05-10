import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        checked = new boolean[n][m];

        List<Pos> empty = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) empty.add(new Pos(i, j));
            }
        }

        int area = 0;
        for (Pos e : empty) {
            if (!checked[e.x][e.y]) {
                bfs(e);
                area++;
            }
        }

        System.out.println(area);

        br.close();
    }

    private static void bfs(Pos start) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(start.x, start.y));
        checked[start.x][start.y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0) nx = n-1;
                if (ny < 0) ny = m-1;
                if (nx >= n) nx = 0;
                if (ny >= m) ny = 0;

                if (map[nx][ny] == 1) continue;
                if (checked[nx][ny]) continue;

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny));
            }
        }
    }
}