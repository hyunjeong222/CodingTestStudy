import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] checked;
    static int n, m;
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static public class pos {
        int x;
        int y;
        int cnt;
        public pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    max = Math.max(max, bfs(j, i));
                }
            }
        }
        bw.append(max + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(x, y, 0));
        checked = new boolean[n][m];
        checked[y][x] = true;

        while (!que.isEmpty()) {
            pos p_q = que.poll();
            for (int i = 0; i < 8; i++) {
                int nx = p_q.x + dx[i];
                int ny = p_q.y + dy[i];

                if (nx < m && nx >= 0 && ny < n && ny >= 0 && !checked[ny][nx]) {
                    if (map[ny][nx] == 0) {
                        checked[ny][nx] = true;
                        que.offer(new pos(nx, ny, p_q.cnt+1));
                    } else {
                        return p_q.cnt+1;
                    }
                }
            }
        }
        return -1;
    }
}