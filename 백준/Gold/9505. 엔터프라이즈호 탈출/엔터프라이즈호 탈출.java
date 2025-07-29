import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k, w, h;
    static int[][] board;
    static long[][] dist;
    static public class Pos implements Comparable<Pos> {
        int x; int y; long w;
        public Pos (int x, int y, long w) {
            this.x = x; this.y = y; this.w = w;
        }
        @Override
        public int compareTo(Pos o) {
            return Long.compare(this.w, o.w);
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // 테스트케이스

        StringBuilder sb = new StringBuilder();
        int num, sx = 0, sy = 0;
        char type;
        StringTokenizer st;
        HashMap<Character, Integer> map;

        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken()); // 클링온 전투선의 클래스 개수
            w = Integer.parseInt(st.nextToken()); // 평면의 폭, 열
            h = Integer.parseInt(st.nextToken()); // 평면의 높이, 행

            map = new HashMap<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                type = st.nextToken().charAt(0); // 클링온 전투선의 클래스 이름
                num = Integer.parseInt(st.nextToken()); // 무력화시키는 데에 결리는 시간

                map.put(type, num);
            }

            board = new int[h][w];
            dist = new long[h][w];
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    char c = str.charAt(j);

                    if (c == 'E') { // 엔터프라이즈호의 위치
                        sx = i; sy = j;
                        board[i][j] = 0;
                        continue;
                    }

                    board[i][j] = map.get(c);
                }
            }
            // System.out.println(Arrays.deepToString(board));

            long ans = dijkstra(sx, sy);

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static long dijkstra(int sx, int sy) {
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE);

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(sx, sy, 0));
        dist[sx][sy] = 0;

        long min = Long.MAX_VALUE;

        while (!pq.isEmpty()) {
            Pos now = pq.poll();

            if (now.w > dist[now.x][now.y]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                // 범위 아웃되면 탈출
                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    min = Math.min(min, now.w);
                    continue;
                }

                if (dist[nx][ny] > dist[now.x][now.y]+board[nx][ny]) {
                    dist[nx][ny] = dist[now.x][now.y]+board[nx][ny];
                    pq.offer(new Pos(nx, ny, dist[nx][ny]));
                }
            }
        }

        return min;
    }
}