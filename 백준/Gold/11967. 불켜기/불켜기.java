import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Pos>[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] checked;
    static boolean[][] switched;

    static public class Pos {
        int x; int y;
        public Pos(int x, int y){
            this.x = x; this.y = y;
        }
    }
    static Queue<Pos> que;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        switched = new boolean[n+1][n+1];
        switched[1][1] = true;

        map = new ArrayList[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        int x, y, a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // (x, y)방에서
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            // (a, b)방의 불을 켜고 끌 수 있음
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[x][y].add(new Pos(a, b));
        }

        int ans = bfs() + 1;
        System.out.println(ans);
        br.close();
    }

    public static int bfs() {
        int cnt = 0;
        que = new LinkedList<>();
        que.offer(new Pos(1, 1));

        checked = new boolean[n+1][n+1];

        boolean isSwitchOn = false;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (0 <= nx && nx <= n && 0 <= ny && ny <= n && !checked[nx][ny] && switched[nx][ny]) {
                    que.offer(new Pos(nx, ny));
                    checked[nx][ny] = true;
                }
            }

            for (Pos turnOn : map[now.x][now.y]) {
                if (!switched[turnOn.x][turnOn.y]) {
                    isSwitchOn = true;
                    switched[turnOn.x][turnOn.y] = true;
                    cnt++;
                }
            }
        }

        if (isSwitchOn) {
            return cnt + bfs();
        }

        return cnt;
    }
}