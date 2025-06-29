import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0}; // 하우상좌
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n][n];

        makeMap(n);

        // System.out.println(Arrays.deepToString(map));

        int kx = 0, ky = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
                if (map[i][j] == k) {
                    kx = i; ky = j;
                }
            }
            sb.append("\n");
        }

        sb.append(kx+1).append(" ").append(ky+1).append("\n");

        System.out.println(sb.toString());

        br.close();
    }

    private static void makeMap(int n) {
        int x = 0;
        int y = 0;
        int cnt = n*n;
        int d = 0;

        while (true) {
            if (cnt == 0) {
                break;
            }

            map[x][y] = cnt--;
            int nx = x+dx[d];
            int ny = y+dy[d];
            int nd = setDirection(d, n, nx, ny);

            if (nd != d) {
                x = x + dx[nd];
                y = y + dy[nd];
                d = nd;
            } else {
                x = nx;
                y = ny;
            }
        }
    }

    private static int setDirection(int d, int n, int nx, int ny) {
        if (nx < 0 || nx >= n || ny < 0 || ny >= n) d = (d+1)%4;
        else if (map[nx][ny] != 0) d = (d+1)%4;

        return d;
    }
}