import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int n = 3;
    static int[][] map;
    static boolean[] check; // 1-9까지 사용 숫자 체크
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        check = new boolean[10];

        back(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void back(int depth, int sum) {
        if (depth == 9 && isMagicSquare()) {
            ans = Math.min(ans, sum);
            return;
        }

        int x = depth/3;
        int y = depth%3;

        for (int i = 1; i <= 9; i++) {
            if (!check[i]) {
                int tmp = map[x][y];

                check[i] = true;
                map[x][y] = i;

                // 한 칸에 있는 수 a를 b로 변경하는 비용은 |a - b|
                back(depth+1, sum+Math.abs(tmp-i));

                check[i] = false;
                map[x][y] = tmp;
            }
        }
    }

    // 가로, 새로, 대각선의 합이 모두 15인지 확인
    private static boolean isMagicSquare() {
        int xSum0 = map[0][0] + map[0][1] + map[0][2];
        int xSum1 = map[1][0] + map[1][1] + map[1][2];
        int xSum2 = map[2][0] + map[2][1] + map[2][2];

        int ySum1 = map[0][0] + map[1][0] + map[2][0];
        int ySum2 = map[0][1] + map[1][1] + map[2][1];
        int ySum3 = map[0][2] + map[1][2] + map[2][2];

        int zSum1 = map[0][0] + map[1][1] + map[2][2];
        int zSum2 = map[0][2] + map[1][1] + map[2][0];

        if (xSum0 != 15 || xSum1 != 15 || xSum2 != 15) return false;
        if (ySum1 != 15 || ySum2 != 15 || ySum3 != 15) return false;
        if (zSum1 != 15 || zSum2 != 15) return false;

        return true;
    }
}