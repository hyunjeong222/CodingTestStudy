import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int[][] map;
    static Queue<Pos> que;
    static public class Pos {
        // 이동 횟수, 청소 체크
        int x; int y; int cnt; int bit;
        public Pos (int x, int y, int cnt, int bit) {
            this.x = x; this.y = y; this.cnt = cnt; this.bit = bit;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 가로
            h = Integer.parseInt(st.nextToken()); // 세로

            if (w == 0 && h == 0) break; // 종료 조건

            map = new int[h][w];

            int dirty = 0; // 더러운 칸 개수
            int sx = 0, sy = 0; // 시작 지점
            for (int i = 0; i < h; i++) {
                char[] tmp = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (tmp[j] == '*') map[i][j] = dirty++;
                    else if (tmp[j] == 'o') {
                        sx = i; sy = j;
                        map[i][j] = '.';
                    } else map[i][j] = tmp[j];
                }
            }

            int cnt = bfs(sx, sy, dirty); // 최소 이동 횟수
            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int sx, int sy, int dirty) {
        int total = (1 << dirty) - 1; // 더러운 칸
        boolean[][][] checked = new boolean[h][w][total+1];
        que = new LinkedList<>();
        que.offer(new Pos(sx, sy, 0, 0));
        checked[sx][sy][0] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();
            int cnt = now.cnt; // 이동 횟수
            int bit = now.bit; // 청소 체크

            // 모든 먼지 청소 완료
            if (bit == total) return cnt;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                int nextBit = bit;

                // 범위 체크, 가구, 방문 체크
                if (rangeCheck(nx, ny) || map[nx][ny] == 'x' || checked[nx][ny][bit]) continue;
                // 더러운 칸 체크
                if (isCleaning(map[nx][ny])) {
                    nextBit |= 1 << map[nx][ny];
                }

                checked[nx][ny][nextBit] = true;
                que.offer(new Pos(nx, ny, cnt+1, nextBit));
            }
        }


        // 모든 먼지 청소 불가
        return -1;
    }

    private static boolean isCleaning(int n) {
        // 더러운 칸의 개수는 10개를 넘지 않음
        return 0 <= n && n <= 10;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= h || y < 0 || y >= w;
    }
}
