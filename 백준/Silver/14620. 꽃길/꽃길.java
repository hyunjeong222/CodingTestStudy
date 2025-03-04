import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        checked = new boolean[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0, 0);

        System.out.println(ans);
    }

    private static void backTracking(int sum, int depth) {
        if (sum >= ans) return;

        if (depth == 3) { // 갖고 있는 씨앗을 모두 다 심었다면
            // 비용 갱신
            ans = Math.min(ans, sum);
        } else {
            for (int i = 1; i < n-1; i++) {
                for (int j = 1; j < n-1; j++) {
                    if (!checked[i][j] && checkVisit(i, j)) {
                        int cost = sumCost(i, j);
                        backTracking(sum+cost, depth+1);
                        clearVisit(i, j);
                    }
                }
            }
        }
    }

    private static void clearVisit(int x, int y) {
        checked[x][y] = false;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            checked[nx][ny] = false;
        }
    }

    private static int sumCost(int x, int y) {
        int sum = map[x][y];
        checked[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            sum += map[nx][ny];
            checked[nx][ny] = true;
        }

        return sum;
    }

    private static boolean checkVisit(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (checked[nx][ny]) return false;
        }
        return true;
    }
}