import java.io.*;
import java.util.*;

public class Main {
    static int n, l, r;
    static int[][] map;
    static boolean[][] checked;
    static ArrayList<Pos> list;
    static Queue<Pos> que;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        move();
        System.out.println(ans);
    }

    private static void move() {
        while (true) {
            boolean flag = false;
            checked = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!checked[i][j]) {
                        int sum = bfs(i, j);
                        if (list.size() > 1) {
                            countPeople(sum);
                            flag = true;
                        }
                    }
                }
            }
            if (!flag) break;
            else ans++;
        }
    }

    private static void countPeople(int sum) {
        int avg = sum / list.size();
        for (Pos now : list) {
            map[now.x][now.y] = avg;
        }
    }

    private static int bfs(int x, int y) {
        que = new LinkedList<>();
        list = new ArrayList<>();

        que.offer(new Pos(x, y));
        list.add(new Pos(x, y));
        checked[x][y] = true;

        int sum = map[x][y];

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || checked[nx][ny]) continue;

                int diff = Math.abs(map[now.x][now.y] - map[nx][ny]);
                if (diff >= l && diff <= r) {
                    que.offer(new Pos(nx, ny));
                    list.add(new Pos(nx, ny));
                    sum += map[nx][ny];
                    checked[nx][ny] = true;
                }
            }
        }
        return sum;
    }
}