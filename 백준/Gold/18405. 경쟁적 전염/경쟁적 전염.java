import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int s, r, c;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<pos> pq;
    static public class pos implements Comparable<pos> {
        int x; int y; int num; int time;
        public pos (int x, int y, int num, int time) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.time = time;
        }
        @Override
        public int compareTo(pos o) {
            if (this.time == o.time) {
                return this.num - o.num;
            }
            return this.time - o.time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) pq.offer(new pos(i, j, map[i][j], 0));
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        bfs();

        bw.append(map[r][c] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        while (!pq.isEmpty()) {
            pos p = pq.poll();

            if (p.time == s) return;

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx >= 0 && nx <= n && ny >= 0 && ny <= n) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = p.num;
                        pq.offer(new pos(nx, ny, map[nx][ny], p.time+1));
                    }
                }
            }
        }
    }
}