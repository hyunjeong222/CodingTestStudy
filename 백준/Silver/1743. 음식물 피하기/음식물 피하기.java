import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x;
        int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int cnt = 0;
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m];
        while (k --> 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken())-1;
            int j = Integer.parseInt(st.nextToken())-1;
            map[i][j] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1  && !checked[i][j]) {
                    bfs(i, j);
                    ans = Math.max(cnt, ans);
                    cnt = 0;
                }
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int x, int y) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(x, y));
        checked[x][y] = true;
        cnt = 1;
        
        while (!que.isEmpty()) {
            pos pq = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pq.x + dx[i];
                int ny = pq.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        checked[nx][ny] = true;
                        que.offer(new pos(nx, ny));
                        cnt++;
                    }
                }
            }
        }
    }
}