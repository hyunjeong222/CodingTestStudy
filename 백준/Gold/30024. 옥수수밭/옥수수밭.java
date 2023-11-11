import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<pos> pq;
    static public class pos implements Comparable<pos> {
        int x; int y; int value;
        public pos(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        // 가치 내림차순
        @Override
        public int compareTo(pos o) {
            return o.value - this.value;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // nxm
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m]; // 옥수수 밭
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 옥수수밭 바깥부터 수확
                if (i == 0 || i == n-1 || j == 0 || j == m-1) {
                    pq.offer(new pos(i, j, map[i][j])); // 위치, 가치
                    checked[i][j] = true;
                }
            }
        }

        k = Integer.parseInt(br.readLine()); // k그루의 옥수수만 수확

        System.out.println(bfs());
        br.close();
    }

    private static String bfs() {
        while (k --> 0) {
            pos now = pq.poll();

            sb.append(now.x + 1).append(' ').append(now.y + 1).append("\n");

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!checked[nx][ny]) {
                        checked[nx][ny] = true;
                        pq.offer(new pos(nx, ny, map[nx][ny]));
                    }
                }

            }
        }
        return sb.toString();
    }
}