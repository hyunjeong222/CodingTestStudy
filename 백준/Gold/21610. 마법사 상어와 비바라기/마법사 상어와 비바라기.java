import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int d, s;
    static Queue<Pos> que;
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static boolean[][] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        checked = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        que = new LinkedList<>();
        que.offer(new Pos(n-1, 0));
        que.offer(new Pos(n-1, 1));
        que.offer(new Pos(n-2, 0));
        que.offer(new Pos(n-2, 1));
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken())-1;
            s = Integer.parseInt(st.nextToken());
            move();
            waterbug();
            newcloud();
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    private static void newcloud() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!checked[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    que.offer(new Pos(i, j));
                }
            }
        }
        checked = new boolean[n][n];
    }

    private static void waterbug() {
        while (!que.isEmpty()) {
            Pos now = que.poll();
            checked[now.x][now.y] = true;
            int cnt = 0;
            for (int i = 1; i <= 7; i += 2) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[nx][ny] >= 1) cnt++;
                }
            }
            map[now.x][now.y] += cnt;
        }
    }

    private static void move() {
        for (Pos now : que) {
            now.x = (n + now.x + dx[d] * (s % n)) % n;
            now.y = (n + now.y + dy[d] * (s % n)) % n;
            map[now.x][now.y]++;
        }
    }
}