import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = new int[5][5];
    static boolean[][] checked = new boolean[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb  = new StringBuilder();

        StringTokenizer st;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        checked[r][c] = true;
        dfs(r, c, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

        br.close();
    }

    private static void dfs(int r, int c, int depth, int cnt) {
        if (cnt == 3) {
            ans = Math.min(ans, depth);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = dx[i]+r;
            int ny = dy[i]+c;

            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !checked[nx][ny] && map[nx][ny] != -1) {
                checked[nx][ny] = true;
                if (map[nx][ny] == 1) {
                    dfs(nx, ny, depth+1, cnt+1);
                } else {
                    dfs(nx, ny, depth+1, cnt);
                }
                checked[nx][ny] = false;
            }
        }
    }
}