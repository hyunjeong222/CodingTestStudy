import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<pos> que;
    static public class pos {
        int x; int y;
        public pos (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // mxn
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        checked = new boolean[m][n];
        que = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (i == 0) que.offer(new pos(i, j));
            }
        }

        bfs();

        System.out.println(flag ? "YES" : "NO");
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            pos pq = que.poll();
            checked[pq.x][pq.y] = true;

            if (pq.x == m-1) {
                flag = true;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = pq.x + dx[i];
                int ny = pq.y + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (!checked[nx][ny] && map[nx][ny] == 0) {
                        checked[nx][ny] = true;
                        que.offer(new pos(nx, ny));
                    }
                }
            }
        }
    }
}