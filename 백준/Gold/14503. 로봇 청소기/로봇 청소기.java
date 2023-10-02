import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int r, c, d;
    static int[][] map;
    static int ans = 1;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 방의 크기
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 칸의 좌표
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        // 바라보는 방향
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d); // 청소기가 있는 좌표, 바라보는 방향
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int r, int c, int d) {
        map[r][c] = -1;

        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nx = r + dx[d];
            int ny = c + dy[d];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) {
                ans++;
                dfs(nx, ny, d);
                return;
            }
        }

        int back = (d + 2) % 4;
        int bx = r + dx[back];
        int by = c + dy[back];

        if (bx >= 0 && bx < n && by >= 0 && by < m && map[bx][by] != 1) {
            dfs(bx, by, d);
        }
    }
}