import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class pos {
        int x; int y; int cnt;
        public pos (int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 지도의 세로 가로
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m]; // 지도
        // 지도 입력
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L') {
                    checked = new boolean[n][m];
                    int val = bfs(i ,j);
                    ans = Math.max(ans, val);
                }
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<pos> que = new LinkedList<>();
        que.offer(new pos(x, y, 0));
        checked[x][y] = true;

        int val = Integer.MIN_VALUE;
        while (!que.isEmpty()) {
            pos pq = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pq.x + dx[i];
                int ny = pq.y + dy[i];

                if (nx >= 0 && nx <n && ny >= 0 && ny < m) {
                    if (!checked[nx][ny] && map[nx][ny] == 'L') {
                        checked[nx][ny] = true;
                        que.offer(new pos(nx, ny, pq.cnt+1));
                        val = Math.max(val, pq.cnt+1);
                    }
                }
            }
        }

        return val;
    }
}