import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int endX, endY;
    static int[][] map;
    static final int SIZE = 1001;
    static final int OFFSET = 500;
    static public class Pos {
        int x; int y; int dist;
        public Pos (int x, int y, int dist) {
            this.x = x; this.y = y; this.dist = dist;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 신아 집
        endX = Integer.parseInt(st.nextToken())+OFFSET;
        endY = Integer.parseInt(st.nextToken())+OFFSET;
        int n = Integer.parseInt(st.nextToken()); // 웅덩이 개수

        map = new int[SIZE][SIZE];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())+OFFSET;
            int b = Integer.parseInt(st.nextToken())+OFFSET;

            map[a][b] = 1; // 웅덩이
        }

        int ans = bfs(OFFSET, OFFSET);

        System.out.println(ans);

        br.close();
    }

    private static int bfs(int x, int y) {
        boolean[][] checked = new boolean[SIZE][SIZE];

        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y, 0));
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == endX && now.y == endY) {
                return now.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (rangeCheck(nx, ny)) continue;
                if (checked[nx][ny] || map[nx][ny] == 1) continue;

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny, now.dist+1));
            }
        }

        return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
    }
}