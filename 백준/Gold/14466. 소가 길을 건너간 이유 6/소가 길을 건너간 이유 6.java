import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k, r;
    static int[][] cow;
    static boolean[][] checked;
    static ArrayList<Pos>[][] road;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // n*n
        k = Integer.parseInt(st.nextToken()); // 소 개수
        r = Integer.parseInt(st.nextToken());

        cow = new int[k][2];

        road = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                road[i][j] = new ArrayList<>();
            }
        }

        int a, b, c, d;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            d = Integer.parseInt(st.nextToken())-1;

            road[a][b].add(new Pos(c, d));
            road[c][d].add(new Pos(a, b));
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;

            cow[i][0] = a;
            cow[i][1] = b;
        }

        for (int i = 0; i < k; i++) {
            bfs(cow[i][0], cow[i][1], i);
        }

        System.out.println(ans);
    }

    private static void bfs(int x, int y, int idx) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));
        checked = new boolean[n][n];
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (rangeCheck(nx, ny)) continue;

                boolean flag = false;
                for (int j = 0; j < road[now.x][now.y].size(); j++) {
                    Pos r = road[now.x][now.y].get(j);
                    if (nx == r.x && ny == r.y) {
                        flag = true;
                        break;
                    }
                }

                if (flag || checked[nx][ny]) continue;

                que.offer(new Pos(nx, ny));
                checked[nx][ny] = true;
            }
        }

        for (int i = idx; i < cow.length; i++) {
            if (!checked[cow[i][0]][cow[i][1]]) ans++;
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}