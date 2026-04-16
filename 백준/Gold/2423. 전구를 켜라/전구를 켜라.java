import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] arr, dp;
    static boolean[][] checked;
    static final int MAX = 987654321;
    static int[] dx = {-1, 1, 1, -1};
    static int[] dy = {1, 1, -1, -1};
    static public class Point implements Comparable<Point> {
        int x, y, t; // t : 회전수
        public Point(int x, int y, int t) {
            this.x = x; this.y = y;
            this.t = t;
        }
        @Override
        public int compareTo(Point o) {
            return this.t - o.t;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        // dp[i][j] : 해당 위치에 도달하기까지 최소 회전 수
        dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++){
            Arrays.fill(dp[i], MAX);
        }
        // '\' : 0, '/' : 1
        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                if (str.charAt(j) == '/') arr[i][j] = 1;
                else arr[i][j] = 0;
            }
        }

        solve();

        System.out.println(dp[n][m] == MAX ? "NO SOLUTION" : dp[n][m]);


        br.close();
    }

    private static void solve() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, 0));
        dp[0][0] = 0;

        while (!pq.isEmpty()) {
            Point now = pq.poll();

            if (now.x == n && now.y == m) break;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (nx < 0 || nx > n || ny < 0 || ny > m) continue;

                int nt = rotate(now.x, now.y, d) ? now.t+1 : now.t;

                if (dp[nx][ny] <= nt) continue;

                dp[nx][ny] = nt;
                pq.offer(new Point(nx, ny, nt));
            }
        }
    }

    private static boolean rotate(int x, int y, int d) {
        if (d == 0) return arr[x-1][y] == 0;
        else if (d == 1) return arr[x][y] == 1;
        else if (d == 2) return arr[x][y-1] == 0;
        else return arr[x-1][y-1] == 1;
    }
}