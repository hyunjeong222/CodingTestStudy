import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] map;
    static int[] dr = {-1, 0, 1};
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < r; i++) {
            dfs(0, i);
        }

        System.out.println(ans);

        br.close();
    }

    private static boolean dfs(int x, int y) {
        for (int i = 0; i < 3; i++) {
            int nx = x + 1;
            int ny = y + dr[i];

            if (rangeCheck(nx, ny)) continue;
            if (map[ny][nx] == '.') {
                if (nx == c-1) {
                    ans++;
                    return true;
                }

                map[ny][nx] = '-';
                if (dfs(nx, ny)) return true;
            }
        }

        return false;
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= c || y < 0 || y >= r;
    }
}