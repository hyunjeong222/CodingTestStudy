import java.io.*;
import java.util.*;

public class Main {
    static int m, n;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos implements Comparable<pos>{
        int x; int y; int cnt;
        public pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public int compareTo(pos o) {
            return this.cnt - o.cnt;
        }
    }
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int ans = bfs(0, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x, int y) {
        checked = new boolean[n][m];
        PriorityQueue<pos> pq = new PriorityQueue<>();
        pq.offer(new pos(x, y, 0));
        checked[x][y] = true;

        while (!pq.isEmpty()) {
            pos now = pq.poll();

            if (now.x == n-1 && now.y == m-1) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        pq.offer(new pos(nx, ny, now.cnt+1));
                        checked[nx][ny] = true;
                    } else if (map[nx][ny] == 0) {
                        pq.offer(new pos(nx, ny, now.cnt));
                        checked[nx][ny] = true;
                    }
                }
            }
        }
        return 0;
    }
}