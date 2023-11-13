import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int[][] air;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<pos> que;
    static public class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int cheeseCnt = 0;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheeseCnt++;
            }
        }

        while (cheeseCnt != 0) {
            bfs();
        }

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        air = new int[n][m];

        que = new LinkedList<>();
        que.offer(new pos(0, 0));
        air[0][0] = -1;

        while (!que.isEmpty()) {
            pos pq = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pq.x + dx[i];
                int ny = pq.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (map[nx][ny] == 1) air[nx][ny]++;
                    if (map[nx][ny] == 0 && air[nx][ny] == 0) {
                        air[nx][ny] = -1;
                        que.offer(new pos(nx, ny));
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (air[i][j] >= 2) {
                    cheeseCnt--;
                    map[i][j] = 0;
                }
            }
        }

        ans++;
    }
}