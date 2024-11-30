import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0}; // 상좌하우
    static int[] dy = {0, -1, 0, 1};
    static int sx, sy, ex, ey;
    static public class Pos implements Comparable<Pos> {
        int x; int y;
        int dir; int cnt; // 거울의 현재 방향, 사용한 거울의 개수
        public Pos (int x, int y, int dir, int cnt) {
            this.x = x; this.y = y; this.dir = dir; this.cnt = cnt;
        }
        @Override
        public int compareTo(Pos o) {
            return this.cnt - o.cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == '#') { // 문
                    if (idx == 0) {
                        sx = i; sy = j;
                        idx++;
                    } else {
                        ex = i; ey = j;
                    }
                }
            }
        }

        int ans = bfs();
        System.out.println(ans);
    }

    private static int bfs() {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        boolean[][][] checked = new boolean[n][n][4];

        // 처음 문 위치를 기준으로 4방향 넣어주기
        for (int i = 0; i < 4; i++) {
            pq.offer(new Pos(sx, sy, i, 0));
        }

        while (!pq.isEmpty()) {
            Pos now = pq.poll();
            int dir = now.dir;

            checked[now.x][now.y][dir] = true;

            if (now.x == ex && now.y == ey) return now.cnt;

            int nx = now.x + dx[dir];
            int ny = now.y + dy[dir];

            // * 빛이 통과할 수 없는 벽
            if (rangeCheck(nx, ny) || checked[nx][ny][dir] || map[nx][ny] == '*') continue;

            if (map[nx][ny] == '!') { // ! 거울 설치 가능한 곳
                // 오른쪽
                int nDir = (dir+3) % 4;
                pq.offer(new Pos(nx, ny, nDir, now.cnt+1));

                // 왼쪽
                nDir = (dir+1) % 4;
                pq.offer(new Pos(nx, ny, nDir, now.cnt+1));
            }

            // 거울을 설치하지 않는 경우
            pq.offer(new Pos(nx, ny, dir, now.cnt));
        }

        return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}