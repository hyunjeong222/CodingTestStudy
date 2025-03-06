import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int minPin = 0, minMove = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n = 5, m = 9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            map = new char[n][m];
            int pin = 0;
            for (int i = 0; i < 5; i++) {
                String str = br.readLine();
                for (int j = 0; j < 9; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'o') pin++;
                }
            }

            minPin = pin;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map[i][j] == 'o') dfs(i, j, pin, 0);
                }
            }

            br.readLine();

            sb.append(minPin).append(" ").append(minMove).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int x, int y, int pin, int move) {
        if (pin <= minPin) { // 최소 핀 개수보다 작다면 갱신
            minPin = pin;
            minMove = move;
        }

        for (int d = 0; d < 4; d++) {
            int nx = dx[d] + x;
            int ny = dy[d] + y;

            // 핀은 수평, 수직 방향으로 인접한 핀을 뛰어넘어서
            // 그 핀의 다음 칸으로 이동하는 것만 허용
            if (rangeCheck(nx, ny) && map[nx][ny] == 'o') {
                int nnx = dx[d] + nx;
                int nny = dy[d] + ny;

                if (rangeCheck(nnx, nny) && map[nnx][nny] == '.') {
                    // 그 인접한 핀은 제거
                    map[x][y] = map[nx][ny] = '.';
                    map[nnx][nny] = 'o';

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            if (map[i][j] == 'o') dfs(i, j, pin-1, move+1);
                        }
                    }

                    map[x][y] = map[nx][ny] = 'o';
                    map[nnx][nny] = '.';
                }
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}