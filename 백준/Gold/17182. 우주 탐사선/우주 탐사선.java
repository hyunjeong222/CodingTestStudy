import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[][] map;
    static boolean[] checked;
    static int ans = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floyd();

        checked = new boolean[n];
        checked[k] = true;
        backtracking(1, 0, k);

        System.out.println(ans);
    }

    private static void backtracking(int depth, int time, int start) {
        if (depth == n) {
            ans = Math.min(ans, time);
        }

        for (int i = 0; i < n; i++) {
            if (checked[i]) continue;
            checked[i] = true;
            backtracking(depth+1, time+map[start][i], i);
            checked[i] = false;
        }
    }

    private static void floyd() {
        for (int k = 0; k < n; k++) { // 경유지
            for (int i = 0; i < n; i++) { // 출발지
                for (int j = 0; j < n; j++) { // 도착지
                    if (i == j) continue;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
    }
}