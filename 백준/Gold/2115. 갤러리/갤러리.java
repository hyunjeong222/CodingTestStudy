import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static char[][] map;
    static boolean[][][] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken()); // 행
        n = Integer.parseInt(st.nextToken()); // 열

        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 해당 방향으로 이동했는지까지 체크
        checked = new boolean[m][n][4];
        int ans = getCnt();

        // System.out.println(Arrays.deepToString(map));

        System.out.println(ans);
    }

    private static int getCnt() {
        int cnt = 0;
        checked = new boolean[m][n][4];

        // 가로 방향에서 그림을 걸기 위해서는 벽의 양쪽에 빈 공간이 붙어있어야 함
        // 벽 두 칸이 나란히 있을 때, 그 뒤에 빈 공간도 나란히 있어야 그림이 걸림
        // 세로 방향에서 그림을 걸기 위해서는 그 벽들의 왼쪽이나 오른쪽에 빈 공간이 있어야 함
        // 0 위 1 아래 2 왼 3 오

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 'X') continue;
                
                // 가로
                if (isWall(i, j+1)) {
                    // 위
                    if (!checked[i][j][0] && isEmpty(i-1, j) && isEmpty(i-1, j+1)) {
                        cnt++;
                        checked[i][j][0] = true;
                        checked[i][j+1][0] = true;
                    }
                    // 아래
                    if (!checked[i][j][1] && isEmpty(i+1, j) && isEmpty(i+1, j+1)) {
                        cnt++;
                        checked[i][j][1] = true;
                        checked[i][j+1][1] = true;
                    }
                }

                // 세로
                if (isWall(i+1, j)) {
                    // 왼쪽
                    if (!checked[i][j][2] && isEmpty(i, j-1) && isEmpty(i+1, j-1)) {
                        cnt++;
                        checked[i][j][2] = true;
                        checked[i+1][j][2] = true;
                    }
                    // 오른쪽
                    if (!checked[i][j][3] && isEmpty(i, j+1) && isEmpty(i+1, j+1)) {
                        cnt++;
                        checked[i][j][3] = true;
                        checked[i+1][j][3] = true;
                    }
                }
            }
        }

        return cnt;
    }

    private static boolean isEmpty(int x, int y) {
        return rangeCheck(x, y) && map[x][y] == '.';
    }

    private static boolean isWall(int x, int y) {
        return rangeCheck(x, y) && map[x][y] == 'X';
    }

    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}