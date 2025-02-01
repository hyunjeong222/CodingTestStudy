import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int n = 10, m = 9;
    static int r, c;
    static int r2, c2;
    static boolean[][] checked;
    static public class Pos {
        int x; int y; int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x; this.y = y; this.cnt = cnt;
        }
    }
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] zx = {-1, 1, 1, -1};
    static int[] zy = {1, 1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 상의 위치
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // 왕의 위치
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        checked = new boolean[n][m];

        int ans = bfs(r, c);

        System.out.println(ans);
    }

    private static int bfs(int r, int c) {
        Queue<Pos> que = new LinkedList<>();
        checked[r][c] = true;
        que.offer(new Pos(r, c, 0));

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.x == r2 && now.y == c2) { // 왕 마주치면 종료
                return now.cnt;
            }

            for (int d = 0; d < 4; d++) {
                // 먼저 상하좌우에 따라 한 칸 이동
                int nx = dx[d] + now.x;
                int ny = dy[d] + now.y;

                if (isPossible(nx, ny)) {
                    for (int cnt = 0; cnt < 2; cnt++) { // 두 개의 대각선 방향 탐색
                        int z = (d+cnt) % 4;
                        int nzx = nx + zx[z];
                        int nzy = ny + zy[z];

                        if (isPossible(nzx, nzy)) {
                            nzx += zx[z];
                            nzy += zy[z];

                            if (rangeCheck(nzx, nzy) && !checked[nzx][nzy]) {
                                que.offer(new Pos(nzx, nzy, now.cnt+1));
                                checked[nzx][nzy] = true;
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isPossible(int x, int y) {
        if (x == r2 && y == c2) return false; // 상이 이동하는 경로에 왕이 있으면 그 방향으로 이동 불가
        return rangeCheck(x, y); // 범위 아웃 확인
    }

    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}