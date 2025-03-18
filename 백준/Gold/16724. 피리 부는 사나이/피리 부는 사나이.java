import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked, finished;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int safeZone;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = str.charAt(j);
                if (c == 'U') map[i][j] = 0;
                else if (c == 'D') map[i][j] = 1;
                else if (c == 'L') map[i][j] = 2;
                else map[i][j] = 3; // R
            }
        }

        checked = new boolean[n][m];
        finished = new boolean[n][m]; // 사이클인지 확인한 위치

        safeZone = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!checked[i][j]) dfs(i, j);
            }
        }

        System.out.println(safeZone);
    }

    private static void dfs(int x, int y) {
        checked[x][y] = true;

        int nx = dx[map[x][y]] + x;
        int ny = dy[map[x][y]] + y;

        if (!checked[nx][ny]) {
            dfs(nx, ny);
        } else {
            if (!finished[nx][ny]) safeZone++;
        }
        finished[x][y] = true;
    }
}