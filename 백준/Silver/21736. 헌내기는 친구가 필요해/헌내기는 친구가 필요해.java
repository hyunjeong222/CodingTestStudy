import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<pos> que;
    static public class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        checked = new boolean[n][m];
        que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'I') {
                    que.offer(new pos(i, j));
                    checked[i][j] = true;
                }
            }
        }
        bfs();

        if (ans == 0) bw.append("TT" + "\n");
        else bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {

        while (!que.isEmpty()) {
            pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !checked[nx][ny] && map[nx][ny] != 'X') {
                    checked[nx][ny] = true;
                    que.offer(new pos(nx, ny));

                    if (map[nx][ny] == 'P') ans++;
                }
            }
        }
    }
}