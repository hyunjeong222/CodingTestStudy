import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 나무판 nxn
        m = Integer.parseInt(st.nextToken()); // 버섯 포자 개수
        k = Integer.parseInt(st.nextToken()); // 최대 k개 연결된 칸에 버섯이 자람

        map = new int[n][n];
        // 버섯이 자랄 수 있는 칸 0, 자랄 수 없는 칸 1
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checked = new boolean[n][n];
        int region; // 구역의 땅의 개수
        int cnt = 0; // 구역에 버섯을 자라게 하는데 필요한 포자의 개수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && !checked[i][j]) {
                    region = dfs(i, j);
                    // System.out.println(region);
                    cnt += (region+k-1)/k;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (cnt == 0) {
            sb.append("IMPOSSIBLE").append("\n");
        } else {
            if (cnt <= m) {
                sb.append("POSSIBLE").append("\n");
                sb.append(m-cnt);
            } else {
                sb.append("IMPOSSIBLE").append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static int dfs(int x, int y) {
        checked[x][y] = true;
        int size = 1;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (rangeCheck(nx, ny)) continue;

            if (map[nx][ny] == 0 && !checked[nx][ny]) {
                size += dfs(nx, ny);
            }
        }

        return size;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}