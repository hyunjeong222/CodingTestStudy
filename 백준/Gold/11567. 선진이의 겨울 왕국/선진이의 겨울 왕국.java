import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static int startX, startY, endX, endY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = str.charAt(j);

                map[i][j] = c;
            }
        }

        // 시작점
        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken())-1;
        startY = Integer.parseInt(st.nextToken())-1;

        // 도착점
        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken())-1;
        endY = Integer.parseInt(st.nextToken())-1;

        checked = new boolean[n][m];
        bfs();
    }

    private static void bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(startX, startY));
        checked[startX][startY] = true;

        int x, y;
        while (!que.isEmpty()) {
            Pos now = que.poll();
            x = now.x; y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                // 범위 체크,
                if (rangeCheck(nx, ny)) continue;

                // 얼음을 손상 시키고, 손상된 얼음을 다시 밟아 추락해야지 탈출구를 이용
                // 이미 탈출구 위의 얼음이 손상되어 있을 수도 있음
                if (map[nx][ny] == 'X' || checked[nx][ny]) {
                    if (nx == endX && ny == endY) {
                        System.out.println("YES");
                        return;
                    }
                    continue;
                }

                checked[nx][ny] = true;
                que.offer(new Pos(nx, ny));
            }
        }

        System.out.println("NO");
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}