import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][][] checked; // 위치, 열쇠 소유 여부
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Pos start;
    static public class Pos {
        int x; int y; int cnt; int key;
        public Pos (int x, int y, int cnt, int key) {
            this.x = x; this.y = y;
            this.cnt = cnt; this.key = key;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 노드의 개수
        m = Integer.parseInt(st.nextToken()); // 최대 방문 노드 개수

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') start = new Pos(i, j, 0, 0);
            }
        }

        checked = new boolean[n][m][64]; // 열쇠는 a~f

        System.out.println(bfs());

        br.close();
    }

    private static int bfs() {
        Queue<Pos> que = new LinkedList<>();
        que.offer(start);
        checked[start.x][start.y][start.key] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (map[now.x][now.y] == '1') return now.cnt;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if (rangeCheck(nx, ny)) continue; // 범위 체크
                if (checked[nx][ny][now.key] || map[nx][ny] == '#') continue; // 이미 갔던 곳, 벽 -> 이동 X

                if ('a' <= map[nx][ny] && 'f' >= map[nx][ny]) { // 열쇠
                    int nextKey = now.key | (1 << (map[nx][ny]-'a')); // 갖고 있는 열쇠
                    que.offer(new Pos(nx, ny, now.cnt+1, nextKey));
                    checked[nx][ny][nextKey] = true;
                } else if ('A' <= map[nx][ny] && 'F' >= map[nx][ny]) { // 문
                    boolean flag = (now.key & (1 << map[nx][ny]-'A')) != 0; // 열쇠가 있는지
                    if (flag) { // 열쇠가 있다면
                        que.offer(new Pos(nx, ny, now.cnt+1, now.key));
                        checked[nx][ny][now.key] = true;
                    }
                } else { // 빈 칸
                    que.offer(new Pos(nx, ny, now.cnt+1, now.key));
                    checked[nx][ny][now.key] = true;
                }
            }
        }

        return -1; // 탈출 불가
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}