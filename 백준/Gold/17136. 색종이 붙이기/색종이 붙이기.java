import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] paperCnt = {0, 5, 5, 5, 5, 5};
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, 0);
        if (ans == Integer.MAX_VALUE) bw.append(-1 + "\n");
        else bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int cnt) {
        if (x == 9 && y == 10) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (y == 10) {
            dfs(x+1, 0, cnt);
            return;
        }

        if (cnt >= ans) return;

        if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (ischeck(x, y, i) && paperCnt[i] > 0) {
                    attach(x, y, i);
                    paperCnt[i]--;
                    dfs(x, y+1, cnt+1);
                    detach(x, y, i);
                    paperCnt[i]++;
                }
            }
        } else {
            dfs(x, y+1, cnt);
        }
    }

    private static void attach(int x, int y, int size) {
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                map[i][j] = 2;
            }
        }
    }

    private static void detach(int x, int y, int size) {
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                map[i][j] = 1;
            }
        }
    }

    private static boolean ischeck(int x, int y, int size) {
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (i >= 10 || j >= 10) return false;
                if (map[i][j] != 1) return false;
            }
        }
        return true;
    }
}