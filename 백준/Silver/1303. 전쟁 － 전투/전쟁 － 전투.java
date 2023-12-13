import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<pos> que;
    static public class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int white = 0;
    static int blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!checked[i][j]) {
                    if (map[i][j] == 'W') {
                        int cnt = bfs(i, j, 'W');
                        white += Math.pow(cnt, 2);
                    } else {
                        int cnt = bfs(i, j, 'B');
                        blue += Math.pow(cnt, 2);
                    }
                }
            }
        }

        System.out.println(white + " " + blue);
    }

    private static int bfs(int x, int y, char c) {
        que = new LinkedList<>();
        que.offer(new pos(x, y));
        checked[x][y] = true;

        int cnt = 1;
        while (!que.isEmpty()) {
            pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny]) {
                    if (map[nx][ny] == c) {
                        checked[nx][ny] = true;
                        que.offer(new pos(nx, ny));
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }
}