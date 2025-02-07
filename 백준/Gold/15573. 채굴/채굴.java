import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m ,k;
    static int[][] map;
    static int max, min;
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // nxm
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 원하는 광물의 수 k 이상 채굴
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        max = 0;
        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }

        int ans = binarySearch();
        System.out.println(ans);
    }

    private static int binarySearch() {
        int start = min;
        int end = max;
        while (start <= end) {
            int mid = start + (end-start) / 2; // 최소 채굴기 성능

            if (isPossible(mid)) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        return start;
    }

    private static boolean isPossible(int mid) {
        Queue<Pos> que = new LinkedList<>();
        boolean[][] checked = new boolean[n][m];

        // 초기에 공기에 닿아있는 부분을 현재 채굴기 성능으로 캘 수 있다면 큐에 넣어주기
        for (int i = 0; i < n; i++) {
            if (map[i][0] <= mid) {
                que.offer(new Pos(i, 0));
                checked[i][0] = true;
            }
            if (map[i][m-1] <= mid) {
                que.offer(new Pos(i, m-1));
                checked[i][m-1] = true;
            }
        }
        for (int i = 0; i < m-1; i++) {
            if (checked[0][i]) continue;
            if (map[0][i] <= mid) {
                que.offer(new Pos(0, i));
                checked[0][i] = true;
            }
        }

        int cnt = que.size();
        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int d = 0; d < 4; d++) {
                int nx = dx[d] + now.x;
                int ny = dy[d] + now.y;

                if (!rangeCheck(nx, ny) && !checked[nx][ny] && map[nx][ny] <= mid) {
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                    cnt++;
                }
            }
        }

        return cnt >= k;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}