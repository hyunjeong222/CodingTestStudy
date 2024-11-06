import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k, w, h;
    static int[][] map;
    static boolean[][][] checked;
    static public class Pos {
        int x; int y; int move; int chance; // 이동횟수, 이동찬스 사용횟수
        public Pos (int x, int y, int move, int chance) {
            this.x = x; this.y = y; this.move = move; this.chance = chance;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] horseDx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] horseDy = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = bfs(0, 0);
        System.out.println(ans);
    }

    private static int bfs(int x, int y) {
        Queue<Pos> que = new LinkedList<>();
        checked = new boolean[h][w][k+1];
        // 말 이동찬스 쓴 횟수
        checked[x][y][0] = true;
        que.offer(new Pos(x, y, 0, 0));

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int move = now.move;
            int chance = now.chance;

            if (now.x == h-1 && now.y == w-1) {
                return now.move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (rangeCheck(nx, ny) || checked[nx][ny][chance]) continue;
                if (map[nx][ny] != 1) {
                    que.offer(new Pos(nx, ny, move+1, chance));
                    checked[nx][ny][chance] = true;
                }
            }

            // 현재 위치에서 말의 이동 찬스가 남았다면 사용
            if (chance < k) {
                for (int i = 0; i < 8; i++) {
                    int nx = now.x + horseDx[i];
                    int ny = now.y + horseDy[i];

                    if (rangeCheck(nx, ny) || checked[nx][ny][chance+1]) continue;
                    if (map[nx][ny] != 1) {
                        que.offer(new Pos(nx, ny, move+1, chance+1));
                        checked[nx][ny][chance+1] = true;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= h || y < 0 || y >= w;
    }
}