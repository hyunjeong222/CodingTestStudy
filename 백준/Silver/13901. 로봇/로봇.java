import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c, sx, sy;
    static int[][] map;
    static boolean[][] checked;
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int[] dir = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        checked = new boolean[r][c];

        int k = Integer.parseInt(br.readLine()); // 장애물의 개수
        int x, y;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            map[x][y] = 1; // 장애물 미리 방문 표시
        }

        st = new StringTokenizer(br.readLine()); // 로봇의 시작 방향
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()); // 이동 순서
        for (int i = 0; i < 4; i++) {
            dir[i] = Integer.parseInt(st.nextToken())-1;
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        sb.append(sx).append(" ").append(sy).append("\n");
        System.out.println(sb.toString());

        br.close();
    }

    private static void bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(sx, sy));
        checked[sx][sy] = true;

        int d = 0;
        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nd = dir[(d+i)%4];
                int nx = now.x + dx[nd];
                int ny = now.y + dy[nd];

                if (rangeCheck(nx, ny) || checked[nx][ny] || map[nx][ny] == 1) continue;

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny));
                d = (d+i)%4;
                sx = nx;
                sy = ny;
                break;
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= r || y < 0 || y >= c;
    }
}