import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n , m;
    static int[][] map;
    static Queue<pos> que;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, 0);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == 3) {
            bfs();
            return;
        }

        for (int i = start; i < n*m; i++) {
            int x = i / m;
            int y = i % m;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                dfs(i+1, depth+1);
                map[x][y] = 0;
            }
        }
    }

    private static void bfs() {
        Queue<pos> que = new LinkedList<>();
        int[][] tmp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = map[i][j];
                if (tmp[i][j] == 2) que.offer(new pos(i, j));
            }
        }

        while (!que.isEmpty()) {
            pos pq = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pq.x + dx[i];
                int ny = pq.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (tmp[nx][ny] == 0) {
                        tmp[nx][ny] = 2;
                        que.offer(new pos(nx, ny));
                    }
                }
            }
        }

        count(tmp);
    }

    private static void count(int[][] tmp) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmp[i][j] == 0) cnt++;
            }
        }
        ans = Math.max(ans, cnt);
    }
}