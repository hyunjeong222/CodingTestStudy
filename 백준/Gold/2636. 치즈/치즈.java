import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
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
    static int cheeseCnt = 0;
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

        int time = 0;
        int cheese = 0;
        while (cheeseCnt != 0) {
            time++;
            cheese = cheeseCnt;
            checked = new boolean[n][m];
            bfs();
        }
        
        bw.append(time + "\n" + cheese);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        que = new LinkedList<>();
        que.offer(new pos(0, 0));
        checked[0][0] = true;

        while (!que.isEmpty()) {
            pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny]) {
                    checked[nx][ny] = true;
                    if (map[nx][ny] == 0) {
                        que.offer(new pos(nx, ny));
                    } else {
                        cheeseCnt--;
                        map[nx][ny] = 0;
                    }
                }
            }
        }
    }
}