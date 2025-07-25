import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, itemCnt;
    static int[][] map;
    static boolean[][][] checked; // 위치, 챙긴 물건
    static Pos start;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y; int cnt; int time;
        public Pos (int x, int y, int cnt, int time) {
            this.x = x; this.y = y;
            this.cnt = cnt; this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로

        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
                // S : 경재 씨의 현재 위치
                // X : 챙겨야 하는 물건
                if (map[i][j] == 'S') {
                    start = new Pos(i, j, 0, 0);
                    map[i][j] = '.';
                } else if (map[i][j] == 'X') map[i][j] = itemCnt++;
            }
        }

        System.out.println(bfs());

        br.close();
    }

    private static int bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(start);

        int checkNum = (1 << itemCnt) - 1;
        checked = new boolean[m][n][checkNum+1];

        while (!que.isEmpty()) {
            Pos now = que.poll();

            // 나가는 문
            if (map[now.x][now.y] == 'E') {
                // 모든 아이템 획득
                if (checkNum == now.cnt) return now.time;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (rangeCheck(nx, ny)) continue; // 범위 체크
                if (checked[nx][ny][now.cnt] || map[nx][ny] == '#') continue; // 이미 챙긴 물건, 벽 -> 이동 X
                
                int nextItem = now.cnt;
                if (map[nx][ny] <= itemCnt) nextItem |= 1 << map[nx][ny];

                checked[nx][ny][nextItem] = true;
                que.offer(new Pos(nx, ny, nextItem, now.time+1));
            }
        }

        return -1;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}